package fr.cookiedev.codebreaker.core.questions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile.Color;

public class QuestionUtils {

	static public String positionOfSequence(int sequence) {
		return Character.toString('a' + sequence);
	}

	static public String getPositionsOf(int value, Code code) {
		final List<String> result = new ArrayList<>(5);
		if (code.a().getValue() == value) {
			result.add("a");
		}
		if (code.b().getValue() == value) {
			result.add("b");
		}
		if (code.c().getValue() == value) {
			result.add("c");
		}
		if (code.d().getValue() == value) {
			result.add("d");
		}
		if (code.e().getValue() == value) {
			result.add("e");
		}
		return String.join(",", result);
	}

	public static String getPositionsOf(Color color, Code code) {
		final List<String> result = new ArrayList<>(5);
		if (code.a().getColor() == color) {
			result.add("a");
		}
		if (code.b().getColor() == color) {
			result.add("b");
		}
		if (code.c().getColor() == color) {
			result.add("c");
		}
		if (code.d().getColor() == color) {
			result.add("d");
		}
		if (code.e().getColor() == color) {
			result.add("e");
		}
		return String.join(",", result);
	}

	public static QuestionCard cardOf(Question question) {
		return new SimpleQuestionCardImpl(question);
	}

	public static QuestionCard cardOf(String label, Question... questions) {
		return new ChoiceQuestionCardImpl(label, questions);
	}

	public static QuestionCard cardOf(Class<? extends Question> questionClass) {
		try {
			return new SimpleQuestionCardImpl(questionClass.getConstructor().newInstance());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("Cannot instantiate card of " + questionClass.getSimpleName(), e);
		}
	}

	@SafeVarargs
	public static QuestionCard cardOf(String label, Class<? extends Question>... questions) {
		return new ChoiceQuestionCardImpl(label,
				Stream.of(questions).map(qc -> {
					try {
						return qc.getConstructor().newInstance();
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						throw new RuntimeException("Cannot instantiate card of " + qc.getSimpleName(), e);
					}
				}).toArray(Question[]::new));
	}
}
