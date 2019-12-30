package fr.cookiedev.codebreaker.core.questions;

import java.util.ArrayList;
import java.util.List;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile.Color;

public class QuestionUtils {
	static public String getPositionsOf(int value, Code code) {
		final List<String> result = new ArrayList<>(5);
		if (code.a().getValue() == value) {
			result.add("a");
		}
		if (code.b().getValue() == value) {
			result.add("b");
		}
		if (code.c().getValue() == value) {
			result.add("c");
		}
		if (code.d().getValue() == value) {
			result.add("d");
		}
		if (code.e().getValue() == value) {
			result.add("e");
		}
		return String.join(",", result);
	}

	public static String getPositionsOf(Color color, Code code) {
		final List<String> result = new ArrayList<>(5);
		if (code.a().getColor() == color) {
			result.add("a");
		}
		if (code.b().getColor() == color) {
			result.add("b");
		}
		if (code.c().getColor() == color) {
			result.add("c");
		}
		if (code.d().getColor() == color) {
			result.add("d");
		}
		if (code.e().getColor() == color) {
			result.add("e");
		}
		return String.join(",", result);
	}
}
