package fr.cookiedev.codebreaker.core.questions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;

public class QuestionImplTest {

	@Test
	public void testTile5() {
		// GIVEN
		final Question q = new Tile5QuestionImpl();
		final Code code = new Code(Tile.B0, Tile.B2, Tile.G5, Tile.W8, Tile.W9);

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("c");
	}

	@Test
	public void testTotalSum() {
		// GIVEN
		final Question q = new TotalSumQuestionImpl();
		final Code code = new Code(Tile.B0, Tile.B2, Tile.G5, Tile.W8, Tile.W9);

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("24");
	}

	@Test
	public void testLeftSum() {
		// GIVEN
		final Question q = new LeftSumQuestionImpl();
		final Code code = new Code(Tile.B0, Tile.B2, Tile.G5, Tile.W8, Tile.W9);

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("7");
	}

	@Test
	public void testRightSum() {
		// GIVEN
		final Question q = new RightSumQuestionImpl();
		final Code code = new Code(Tile.B0, Tile.B2, Tile.G5, Tile.W8, Tile.W9);

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("22");
	}

	@ParameterizedTest(name = "Question {0} should have a configured message.")
	@MethodSource("getQuestions")
	public void testQuestionMessages(Class<? extends Question> questionClass) throws Exception {
		// WHEN
		final Question q = questionClass.getDeclaredConstructor().newInstance();
		final String actual = q.ask();

		// THEN
		assertThat(actual).doesNotMatch("!.+!");
	}

	private static Stream<Class<? extends Question>> getQuestions() {
		final Reflections reflections = new Reflections("fr.cookiedev.codebreaker.core.questions");
		return reflections.getSubTypesOf(Question.class).stream();
	}

}
