package fr.cookiedev.codebreaker.core.questions;

import fr.cookiedev.codebreaker.core.Code;

public class Tile5QuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		return QuestionUtils.getPositionsOf(5, code);
	}

}
