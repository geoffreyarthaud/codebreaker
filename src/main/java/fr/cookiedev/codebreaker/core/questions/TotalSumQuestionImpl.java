package fr.cookiedev.codebreaker.core.questions;

import fr.cookiedev.codebreaker.core.Code;

public class TotalSumQuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		return Integer.toString(code.a().getValue()
				+ code.b().getValue()
				+ code.c().getValue()
				+ code.d().getValue()
				+ code.e().getValue());
	}

}
