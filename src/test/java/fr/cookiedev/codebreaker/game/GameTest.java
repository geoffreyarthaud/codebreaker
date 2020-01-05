package fr.cookiedev.codebreaker.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;
import fr.cookiedev.codebreaker.core.questions.QuestionCard;

class GameTest {

	private Game game;

	@Test
	void whenTwoRegistredPlayers_thenStartedEventWithCorrectCodeIsEmitted() {
		// GIVEN
		final String player1Id = "ID1";
		final String player2Id = "ID2";
		game = new Game(player1Id, player2Id);

		// WHEN
		final Code player1Code = game.getCode(player1Id);
		final Code player2Code = game.getCode(player2Id);

		// THEN
		final Set<Tile> tiles = EnumSet.of(player1Code.a(), player1Code.b(), player1Code.c(), player1Code.d(),
				player1Code.e());
		assertThat(tiles.removeAll(Arrays.asList(player2Code.getTiles()))).isFalse();
	}

	@Test
	void givenNewGame_whenGetBoard_ThenGet6DiferentQuestions() {
		// GIVEN
		final String player1Id = "ID1";
		final String player2Id = "ID2";
		game = new Game(player1Id, player2Id);
		// WHEN
		final List<QuestionCard> cards = game.getAvailableQuestions();

		// THEN
		final String[] ids = cards.stream().map(QuestionCard::getId).toArray(String[]::new);
		assertThat(cards).hasSize(6)
				.extracting(QuestionCard::getId).containsOnlyOnce(ids);
	}

}
