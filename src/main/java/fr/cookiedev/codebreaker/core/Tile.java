package fr.cookiedev.codebreaker.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Tile implements Comparable<Tile> {
	B0(0, Color.BLACK),
	W0(0, Color.WHITE),
	B1(1, Color.BLACK),
	W1(1, Color.WHITE),
	B2(2, Color.BLACK),
	W2(2, Color.WHITE),
	B3(3, Color.BLACK),
	W3(3, Color.WHITE),
	B4(4, Color.BLACK),
	W4(4, Color.WHITE),
	G5_1(5, Color.GREEN),
	G5_2(5, Color.GREEN),
	B6(6, Color.BLACK),
	W6(6, Color.WHITE),
	B7(7, Color.BLACK),
	W7(7, Color.WHITE),
	B8(8, Color.BLACK),
	W8(8, Color.WHITE),
	B9(9, Color.BLACK),
	W9(9, Color.WHITE);

	private final int value;
	private final Color color;

	Tile(int value, Color color) {
		this.value = value;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public int getValue() {
		return value;
	}

	public enum Color {
		BLACK,
		WHITE,
		GREEN
	}

	public static List<Tile> tilesList() {
		final List<Tile> tiles = new ArrayList<>(Arrays.asList(Tile.values()));
		return tiles;
	}

}
