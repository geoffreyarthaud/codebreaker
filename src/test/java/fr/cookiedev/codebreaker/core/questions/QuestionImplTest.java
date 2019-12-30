package fr.cookiedev.codebreaker.core.questions;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;
import fr.cookiedev.codebreaker.core.questions.impl.BlackSumQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CTileGreater4QuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CenterSumQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CountBlackQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CountEvenQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CountOddQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CountWhiteQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.DiffTilesQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.EqualValueQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.FollowValueQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.LeftSumQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.RightSumQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.SameColorQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.TileNQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.TotalSumQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.WhiteSumQuestionImpl;

public class QuestionImplTest {

	private final Code code = new Code(Tile.B0, Tile.B2, Tile.G5, Tile.W8, Tile.W9);
	private final Code code2 = new Code(Tile.B1, Tile.B2, Tile.B3, Tile.W8, Tile.W9);

	@Test
	public void testTileN() {
		// GIVEN
		final Question q = new TileNQuestionImpl(5);

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("c");
	}

	@Test
	public void testTotalSum() {
		// GIVEN
		final Question q = new TotalSumQuestionImpl();

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("24");
	}

	@Test
	public void testLeftSum() {
		// GIVEN
		final Question q = new LeftSumQuestionImpl();

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("7");
	}

	@Test
	public void testCenterSum() {
		// GIVEN
		final Question q = new CenterSumQuestionImpl();

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("15");
	}

	@Test
	public void testRightSum() {
		// GIVEN
		final Question q = new RightSumQuestionImpl();

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("22");
	}

	@Test
	public void testBlackSum() {
		// GIVEN
		final Question q = new BlackSumQuestionImpl();

		// WHEN
		final String actual = q.answer(code2);

		// THEN
		assertThat(actual).isEqualTo("6");
	}

	@Test
	public void testWhiteSum() {
		// GIVEN
		final Question q = new WhiteSumQuestionImpl();

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("17");
	}

	@Test
	public void testCountBlack() {
		// GIVEN
		final Question q = new CountBlackQuestionImpl();

		// WHEN
		final String actual = q.answer(code2);

		// THEN
		assertThat(actual).isEqualTo("3");
	}

	@Test
	public void testCountEven() {
		// GIVEN
		final Question q = new CountEvenQuestionImpl();

		// WHEN
		final String actual = q.answer(code2);

		// THEN
		assertThat(actual).isEqualTo("2");
	}

	@Test
	public void testCountOdd() {
		// GIVEN
		final Question q = new CountOddQuestionImpl();

		// WHEN
		final String actual = q.answer(code2);

		// THEN
		assertThat(actual).isEqualTo("3");
	}

	@Test
	public void testCountWhite() {
		// GIVEN
		final Question q = new CountWhiteQuestionImpl();

		// WHEN
		final String actual = q.answer(code2);

		// THEN
		assertThat(actual).isEqualTo("2");
	}

	@Test
	public void testCTileGreater4() {
		// GIVEN
		final Question q = new CTileGreater4QuestionImpl();

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("true");
	}

	@Test
	public void testCTileGreater4False() {
		// GIVEN
		final Question q = new CTileGreater4QuestionImpl();

		// WHEN
		final String actual = q.answer(code2);

		// THEN
		assertThat(actual).isEqualTo("false");
	}

	@Test
	public void testEqualValue2() {
		// GIVEN
		final Question q = new EqualValueQuestionImpl();
		final Code localCode = new Code(Tile.B0, Tile.W2, Tile.B1, Tile.B2, Tile.B9);

		// WHEN
		final String actual = q.answer(localCode);

		// THEN
		assertThat(actual).isEqualTo("2");
	}

	@Test
	public void testEqualValue0() {
		// GIVEN
		final Question q = new EqualValueQuestionImpl();

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("0");
	}

	@Test
	public void testEqualValue4() {
		// GIVEN
		final Question q = new EqualValueQuestionImpl();
		final Code localCode = new Code(Tile.W1, Tile.W2, Tile.B1, Tile.B2, Tile.B9);

		// WHEN
		final String actual = q.answer(localCode);

		// THEN
		assertThat(actual).isEqualTo("4");
	}

	@Test
	public void testDiffTiles() {
		// GIVEN
		final Question q = new DiffTilesQuestionImpl();

		// WHEN
		final String actual = q.answer(code2);

		// THEN
		assertThat(actual).isEqualTo("8");
	}

	@Test
	public void testFollowValues1Group() {
		// GIVEN
		final Question q = new FollowValueQuestionImpl();

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("d,e");
	}

	@Test
	public void testFollowValues2Groups() {
		// GIVEN
		final Question q = new FollowValueQuestionImpl();

		// WHEN
		final String actual = q.answer(code2);

		// THEN
		assertThat(actual).isEqualTo("a,b,c;d,e");
	}

	@Test
	public void testSameColor() {
		// GIVEN
		final Question q = new SameColorQuestionImpl();

		// WHEN
		final String actual = q.answer(code);

		// THEN
		assertThat(actual).isEqualTo("a,b;d,e");
	}

	@Test
	public void testSameColor2() {
		// GIVEN
		final Question q = new SameColorQuestionImpl();

		// WHEN
		final String actual = q.answer(code2);

		// THEN
		assertThat(actual).isEqualTo("a,b,c;d,e");
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

	// Returns only questions which have a zero-parameter constructor
	private static Stream<Class<? extends Question>> getQuestions() {
		final Reflections reflections = new Reflections("fr.cookiedev.codebreaker.core.questions");
		return reflections.getSubTypesOf(Question.class).stream().filter(qc -> {
			for (final Constructor<?> qcc : qc.getConstructors()) {
				if (qcc.getParameterCount() == 0) {
					return true;
				}
			}
			return false;
		});
	}

}
