package fr.cookiedev.codebreaker.core.questions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;

class QuestionUtilsTest {

	private final Code code = new Code(Tile.B1, Tile.W2, Tile.G5_1, Tile.B7, Tile.W7);

	@Test
	void givenACodeWithOne2_whenGetPositions_thenGetCorrectAnswer() {
		// WHEN
		final String positions = QuestionUtils.getPositionsOf(2, code);

		// THEN
		assertThat(positions).isEqualTo("b");
	}

	@Test
	void givenACodeWithTwo7_whenGetPositions_thenGetCorrectAnswer() {
		// WHEN
		final String positions = QuestionUtils.getPositionsOf(7, code);

		// THEN
		assertThat(positions).isEqualTo("d,e");
	}

	@Test
	void givenACodeWithNo3_whenGetPositions_thenGetCorrectAnswer() {
		// WHEN
		final String positions = QuestionUtils.getPositionsOf(3, code);

		// THEN
		assertThat(positions).isEqualTo("");
	}

	@Test
	void givenACodeWithTwoW_whenGetPositions_thenGetCorrectAnswer() {
		// WHEN
		final String positions = QuestionUtils.getPositionsOf(Tile.Color.WHITE, code);

		// THEN
		assertThat(positions).isEqualTo("b,e");
	}

	@Test
	void givenACodeWithOneG_whenGetPositions_thenGetCorrectAnswer() {
		// WHEN
		final String positions = QuestionUtils.getPositionsOf(Tile.Color.GREEN, code);

		// THEN
		assertThat(positions).isEqualTo("c");
	}

}
