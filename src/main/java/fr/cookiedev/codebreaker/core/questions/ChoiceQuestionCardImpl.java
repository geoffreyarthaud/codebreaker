package fr.cookiedev.codebreaker.core.questions;

import java.util.Objects;

import fr.cookiedev.codebreaker.core.Code;

public class ChoiceQuestionCardImpl implements QuestionCard {

	private final Question[] questions;
	private final String globalMessage;

	public ChoiceQuestionCardImpl(String globalMessage, Question... questions) {
		this.questions = Objects.requireNonNull(questions);
		this.globalMessage = Objects.requireNonNull(globalMessage);
		if (questions.length < 2) {
			throw new IllegalArgumentException();
		}
		for (final Question q : questions) {
			if (q == null) {
				throw new NullPointerException();
			}
		}
	}

	@Override
	public String getQuestionMessage() {
		return globalMessage;
	}

	@Override
	public String answer(int choice, Code code) {
		return questions[choice].answer(code);
	}

	public String getChoiceMessage(int choice) {
		return questions[choice].ask();
	}

}
