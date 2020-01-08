package fr.cookiedev.codebreaker.core.questions;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import fr.cookiedev.codebreaker.core.Code;

public class SimpleQuestionCardImpl implements QuestionCard {

	private final Question question;

	public SimpleQuestionCardImpl(Question question) {
		this.question = Objects.requireNonNull(question);
	}

	@Override
	public String getQuestionMessage() {
		return question.ask();
	}

	@Override
	public String answer(int choice, Code code) {
		return question.answer(code);
	}

	@Override
	public String getId() {
		return question.getId();
	}

	@Override
	public List<Question> getQuestions() {
		return Arrays.asList(question);
	}

}
