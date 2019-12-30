package fr.cookiedev.codebreaker.core.questions.impl;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.questions.Question;

public class RightSumQuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		return Integer.toString(code.c().getValue() + code.d().getValue() + code.e().getValue());
	}

}
