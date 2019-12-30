package fr.cookiedev.codebreaker.core.questions.impl;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;
import fr.cookiedev.codebreaker.core.questions.Question;
import fr.cookiedev.codebreaker.core.questions.QuestionUtils;

public class SameColorQuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		String result = "";
		final Tile[] tiles = code.getTiles();
		boolean following = false;
		for (int seq = 1; seq < tiles.length; seq++) {
			if (tiles[seq].getColor() == tiles[seq - 1].getColor()) {
				if (following) {
					result += "," + QuestionUtils.positionOfSequence(seq);
				} else {
					if (result.length() > 0) {
						result += ";";
					}
					result += QuestionUtils.positionOfSequence(seq - 1) + "," + QuestionUtils.positionOfSequence(seq);
					following = true;
				}
			} else {
				following = false;
			}
		}
		return result;
	}

}
