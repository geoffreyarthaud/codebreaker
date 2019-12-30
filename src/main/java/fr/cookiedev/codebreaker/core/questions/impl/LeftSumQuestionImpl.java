package fr.cookiedev.codebreaker.core.questions.impl;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.questions.Question;

public class LeftSumQuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		return Integer.toString(code.a().getValue() + code.b().getValue() + code.c().getValue());
	}

}
