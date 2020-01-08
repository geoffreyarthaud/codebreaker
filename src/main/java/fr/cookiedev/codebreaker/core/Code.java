package fr.cookiedev.codebreaker.core;

import java.util.Arrays;
import java.util.EnumSet;

public class Code {

	private final Tile[] tiles;

	public Code(Tile t1, Tile t2, Tile t3, Tile t4, Tile t5) {
		tiles = new Tile[] { t1, t2, t3, t4, t5 };
		sortAndRequireUniqueTiles();
	}

	public Code(Tile[] tiles) {
		if (tiles.length != 5) {
			throw new IllegalArgumentException();
		}
		this.tiles = tiles;
		sortAndRequireUniqueTiles();
	}

	private void sortAndRequireUniqueTiles() {
		Arrays.sort(tiles);
		for (int i = 1; i < tiles.length; i++) {
			if (tiles[i - 1] == tiles[i]) {
				throw new IllegalArgumentException("A code cannot contain two same tiles");
			}
		}
	}

	public static boolean match(Code code1, Code code2) {
		for (int i = 0; i < code1.getTiles().length; i++) {
			if (!match(code1.getTiles()[i], code2.getTiles()[i])) {
				return false;
			}
		}
		return true;
	}

	private static boolean match(Tile tile1, Tile tile2) {
		return tile1 == tile2 || EnumSet.of(tile1, tile2).equals(EnumSet.of(Tile.G5_1, Tile.G5_2));
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
		return Arrays.copyOf(tiles, tiles.length);
	}
}
