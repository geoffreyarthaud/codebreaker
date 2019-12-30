package fr.cookiedev.codebreaker.core.questions;

import fr.cookiedev.codebreaker.core.Code;

public class RightSumQuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		return Integer.toString(code.c().getValue() + code.d().getValue() + code.e().getValue());
	}

}
