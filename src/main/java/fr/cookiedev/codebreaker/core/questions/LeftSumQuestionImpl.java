package fr.cookiedev.codebreaker.core.questions;

import fr.cookiedev.codebreaker.core.Code;

public class LeftSumQuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		return Integer.toString(code.a().getValue() + code.b().getValue() + code.c().getValue());
	}

}
