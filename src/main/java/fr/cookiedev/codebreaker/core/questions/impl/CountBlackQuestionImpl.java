package fr.cookiedev.codebreaker.core.questions.impl;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;
import fr.cookiedev.codebreaker.core.Tile.Color;
import fr.cookiedev.codebreaker.core.questions.Question;

public class CountBlackQuestionImpl implements Question {

	@Override
	public String answer(Code code) {
		int count = 0;
		for (final Tile tile : code.getTiles()) {
			if (tile.getColor() == Color.BLACK) {
				count++;
			}
		}
		return Integer.toString(count);
	}

}