package fr.cookiedev.codebreaker.core.questions.impl;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.questions.Question;
import fr.cookiedev.codebreaker.core.questions.QuestionUtils;

public class Tile5QuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		return QuestionUtils.getPositionsOf(5, code);
	}

}
