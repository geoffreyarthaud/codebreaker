package fr.cookiedev.codebreaker.game;

import java.util.Collections;
import java.util.List;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;

public class Game {

	private final Player player1;

	private final Player player2;

	private final GameStatus status;

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		final List<Tile> tiles = Tile.tilesList();
		Collections.shuffle(tiles);
		player1.provideCode(this, new Code(tiles.get(0), tiles.get(1), tiles.get(2), tiles.get(3), tiles.get(4)));
		player2.provideCode(this, new Code(tiles.get(5), tiles.get(6), tiles.get(7), tiles.get(8), tiles.get(9)));
		status = GameStatus.P1_ASK;
	}

}
