package fr.cookiedev.codebreaker.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.questions.QuestionCard;

@ExtendWith(MockitoExtension.class)
class GameStatusTest {

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
	void whenNothing_thenP1Turn() {
		// WHEN

		// THEN
		assertThat(game.getStatus()).isEqualTo(GameStatus.P1_TURN);
	}

	@Test
	void whenPlayer1AskQuestion_thenP2Turn() {
		// GIVEN
		final String expectedAnswer = "QC1";
		when(qc1.answer(anyInt(), any(Code.class))).thenReturn(expectedAnswer);

		// WHEN
		game.ask(player1Id, qc1, 0);

		// THEN
		assertThat(game.getStatus()).isEqualTo(GameStatus.P2_TURN);
	}

	@Test
	void whenPlayer1TryBadCode_thenP2Turn() {
		// GIVEN
		final Code code1 = game.getCode(player1Id);

		// WHEN
		game.tryCode(player1Id, code1);

		// THEN
		assertThat(game.getStatus()).isEqualTo(GameStatus.P2_TURN);
	}

	@Test
	void whenPlayer1TryGoodCode_thenP1Wins() {
		// GIVEN
		final Code code2 = game.getCode(player2Id);

		// WHEN
		game.tryCode(player1Id, code2);

		// THEN
		assertThat(game.getStatus()).isEqualTo(GameStatus.P1_WIN);
	}

	@Test
	void givenP2Turn_whenPlayer2TryGoodCode_thenP2Wins() {
		// GIVEN
		final Code code1 = game.getCode(player1Id);
		game.tryCode(player1Id, code1);

		// WHEN
		game.tryCode(player2Id, code1);

		// THEN
		assertThat(game.getStatus()).isEqualTo(GameStatus.P2_WIN);
	}

}
