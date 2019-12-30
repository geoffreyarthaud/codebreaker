package fr.cookiedev.codebreaker.core.questions.impl;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.questions.Question;

public class CTileGreater4QuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		return Boolean.toString(code.c().getValue() > 4);
	}

}
