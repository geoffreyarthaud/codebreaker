package fr.cookiedev.codebreaker.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;
import fr.cookiedev.codebreaker.core.questions.QuestionCard;

@ExtendWith(MockitoExtension.class)
class GameTest {

	@Mock
	private QuestionCard qc1;

	@Mock
	private QuestionCard qc2;

	@Mock
	private QuestionCard qc3;

	private Game game;

	private final static String player1Id = "ID1";

	private final static String player2Id = "ID2";

	@BeforeEach
	void initGame() {
		game = new Game(player1Id, player2Id, Arrays.asList(qc1, qc2, qc3), 3);
	}

	@Test
	void whenGetCodes_thenNoCommonTileIsUsedd() {

		// WHEN
		final Code player1Code = game.getCode(player1Id);
		final Code player2Code = game.getCode(player2Id);

		// THEN
		final Set<Tile> tiles = EnumSet.of(player1Code.a(), player1Code.b(), player1Code.c(), player1Code.d(),
				player1Code.e());
		assertThat(tiles.removeAll(Arrays.asList(player2Code.getTiles()))).isFalse();
	}

	@Test
	void whenRegisterPlayer1Blank_ThenThrowsRuntimeException() {
		// WHEN
		assertThrows(IllegalArgumentException.class, () -> new Game("", "ID2"));
	}

	@Test
	void whenRegisterPlayer2Blank_ThenThrowsRuntimeException() {
		// WHEN
		assertThrows(IllegalArgumentException.class, () -> new Game("ID1", ""));
	}

	@Test
	void givenNewDefaultGame_whenGetBoard_thenGet6DifferentQuestions() {
		// GIVEN
		game = new Game(player1Id, player2Id);

		// WHEN
		final List<QuestionCard> cards = game.getAvailableQuestions();

		// THEN
		final String[] ids = cards.stream().map(QuestionCard::getId).toArray(String[]::new);
		assertThat(cards).hasSize(6)
				.extracting(QuestionCard::getId).containsOnlyOnce(ids);
	}

	@Test
	void whenGetBoard_thenGetCorrectQuestions() {
		// WHEN
		final List<QuestionCard> cards = game.getAvailableQuestions();

		// THEN
		assertThat(cards).containsExactlyInAnyOrder(qc1, qc2, qc3);
	}

	@Test
	void whenPlayer1AskQuestion_thenGetCorrectAnswer() {
		// GIVEN
		final String expectedAnswer = "QC1";
		when(qc1.answer(anyInt(), any(Code.class))).thenReturn(expectedAnswer);

		// WHEN
		final String actualAnswer = game.ask(player1Id, qc1, 0);

		// THEN
		assertThat(actualAnswer).isEqualTo(expectedAnswer);
	}

	@Test
	void whenPlayer2AskQuestion_thenThrowsISE() {
		// WHEN
		assertThrows(IllegalStateException.class, () -> game.ask(player2Id, qc1, 0));
	}

	@Test
	void whenUnknownIdAskQuestion_thenThrowsIAE() {
		// WHEN
		assertThrows(IllegalArgumentException.class, () -> game.ask("ID3", qc1, 0));
	}

	@Test
	void whenPlayer1TryBadCode_thenReturnsFalse() {
		// GIVEN
		final Code code1 = game.getCode(player1Id);

		// WHEN
		final boolean actualResult = game.tryCode(player1Id, code1);

		// THEN
		assertThat(actualResult).isFalse();
	}

	@Test
	void givenNewGame_whenPlayer1TryGoodCode_thenReturnsTrue() {
		// GIVEN
		final Code code2 = game.getCode(player2Id);

		// WHEN
		final boolean actualResult = game.tryCode(player1Id, code2);

		// THEN
		assertThat(actualResult).isTrue();
	}

	@Test
	void givenNewGame_whenPlayer2TryCode_thenThrowsISE() {
		// GIVEN
		final Code code1 = game.getCode(player1Id);

		// WHEN
		assertThrows(IllegalStateException.class, () -> game.tryCode(player2Id, code1));
	}

	@Test
	void givenNewGame_whenUnknownPlayerTryCode_thenThrowsIAE() {
		// GIVEN
		final Code code1 = game.getCode(player1Id);

		// WHEN
		assertThrows(IllegalArgumentException.class, () -> game.tryCode("ID3", code1));
	}

	@ParameterizedTest(name = "Given new Game with {0}, when try good code with other G5, then return true")
	@EnumSource(value = Tile.class, names = { "G5_1", "G5_2" })
	void givenNewGameWithG5_1Code_whenPlayer1TryGoodCodeWithOther5_thenReturnsTrue(Tile g5) {
		// GIVEN
		final Tile otherG5 = g5 == Tile.G5_1 ? Tile.G5_2 : Tile.G5_1;
		while (!Arrays.asList(game.getCode(player2Id).getTiles()).contains(g5)
				|| Arrays.asList(game.getCode(player2Id).getTiles()).contains(otherG5)) {
			game = new Game(player1Id, player2Id, Arrays.asList(qc1, qc2, qc3), 3);
		}

		// WHEN
		final Tile[] tryTiles = game.getCode(player2Id).getTiles();
		tryTiles[Arrays.binarySearch(tryTiles, g5)] = otherG5;
		final boolean actualResult = game.tryCode(player1Id, new Code(tryTiles));

		// THEN
		assertThat(actualResult).isTrue();
	}

}
