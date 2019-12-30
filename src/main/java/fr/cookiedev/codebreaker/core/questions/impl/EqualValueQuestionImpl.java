package fr.cookiedev.codebreaker.core.questions.impl;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;
import fr.cookiedev.codebreaker.core.questions.Question;

public class EqualValueQuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		int count = 0;
		int prev = -1;
		for (final Tile tile : code.getTiles()) {
			if (tile.getValue() == prev) {
				count += 2;
			}
			prev = tile.getValue();
		}
		return Integer.toString(count);
	}

}
