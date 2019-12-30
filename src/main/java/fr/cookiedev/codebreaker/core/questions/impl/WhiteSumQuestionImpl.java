package fr.cookiedev.codebreaker.core.questions.impl;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;
import fr.cookiedev.codebreaker.core.Tile.Color;
import fr.cookiedev.codebreaker.core.questions.Question;

public class WhiteSumQuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		int sum = 0;
		for (final Tile tile : code.getTiles()) {
			if (tile.getColor() == Color.WHITE) {
				sum += tile.getValue();
			}
		}
		return Integer.toString(sum);
	}

}
