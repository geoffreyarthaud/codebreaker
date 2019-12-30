package fr.cookiedev.codebreaker.core;

import java.util.Arrays;

public class Code {

	private final Tile[] tiles;

	public Code(Tile t1, Tile t2, Tile t3, Tile t4, Tile t5) {
		tiles = new Tile[] { t1, t2, t3, t4, t5 };
		Arrays.sort(tiles);
		for (int i = 1; i < tiles.length; i++) {
			if (tiles[i - 1] == tiles[i]) {
				throw new IllegalArgumentException("A code cannot contain two same tiles");
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Code) {
			return Arrays.equals(tiles, ((Code) obj).tiles);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(tiles);
	}

	@Override
	public String toString() {
		return Arrays.toString(tiles);
	}

	public Tile a() {
		return tiles[0];
	}

	public Tile b() {
		return tiles[1];
	}

	public Tile c() {
		return tiles[2];
	}

	public Tile d() {
		return tiles[3];
	}

	public Tile e() {
		return tiles[4];
	}

	public Tile[] getTiles() {
		return tiles;
	}
}
