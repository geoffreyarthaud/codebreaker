package fr.cookiedev.codebreaker.core.questions.impl;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.questions.Question;

public class DiffTilesQuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		return Integer.toString(code.e().getValue() - code.a().getValue());
	}

}
