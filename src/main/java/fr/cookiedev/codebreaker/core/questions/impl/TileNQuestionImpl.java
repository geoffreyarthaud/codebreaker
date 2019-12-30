package fr.cookiedev.codebreaker.core.questions.impl;

import java.text.MessageFormat;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.questions.Question;
import fr.cookiedev.codebreaker.core.questions.QuestionUtils;

public class TileNQuestionImpl implements Question {

	private final int value;

	public TileNQuestionImpl(int value) {
		this.value = value;
	}

	@Override
	public String answer(Code code) {
		return QuestionUtils.getPositionsOf(value, code);
	}

	@Override
	public String ask() {
		return MessageFormat.format(Question.super.ask(), value);
	}

}
