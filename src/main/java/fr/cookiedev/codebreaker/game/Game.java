package fr.cookiedev.codebreaker.game;

import static fr.cookiedev.codebreaker.core.questions.QuestionUtils.cardOf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;
import fr.cookiedev.codebreaker.core.questions.QuestionCard;
import fr.cookiedev.codebreaker.core.questions.QuestionMessages;
import fr.cookiedev.codebreaker.core.questions.impl.BlackSumQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CTileGreater4QuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CenterSumQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CountBlackQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CountEvenQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CountOddQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.CountWhiteQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.DiffTilesQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.EqualValueQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.FollowValueQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.LeftSumQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.RightSumQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.SameColorQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.TileNQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.TotalSumQuestionImpl;
import fr.cookiedev.codebreaker.core.questions.impl.WhiteSumQuestionImpl;

public class Game {

	private final String player1Id;

	private final String player2Id;

	private final GameStatus status;

	private final QuestionCardDeck deck;

	private final Code codePlayer1;

	private final Code codePlayer2;

	public Game(String player1Id, String player2Id) {
		this.player1Id = Objects.requireNonNull(player1Id);
		this.player2Id = Objects.requireNonNull(player2Id);
		final List<Tile> tiles = Tile.tilesList();
		Collections.shuffle(tiles);
		codePlayer1 = new Code(tiles.get(0), tiles.get(1), tiles.get(2), tiles.get(3), tiles.get(4));
		codePlayer2 = new Code(tiles.get(5), tiles.get(6), tiles.get(7), tiles.get(8), tiles.get(9));
		status = GameStatus.P1_ASK;
		deck = new QuestionCardDeck(fillDeck());
		deck.initDeck(6);
	}

	public List<QuestionCard> getAvailableQuestions() {
		return deck.getRevealed();
	}

	private List<QuestionCard> fillDeck() {
		final List<QuestionCard> cards = new ArrayList<>();
		cards.add(cardOf(new TileNQuestionImpl(0)));
		cards.add(cardOf(new TileNQuestionImpl(5)));
		cards.add(cardOf(QuestionMessages.getString("Tile1Or2Choice.ask"),
				new TileNQuestionImpl(1),
				new TileNQuestionImpl(2)));
		cards.add(cardOf(QuestionMessages.getString("Tile3Or4Choice.ask"),
				new TileNQuestionImpl(3),
				new TileNQuestionImpl(4)));
		cards.add(cardOf(QuestionMessages.getString("Tile6Or7Choice.ask"),
				new TileNQuestionImpl(6),
				new TileNQuestionImpl(7)));
		cards.add(cardOf(QuestionMessages.getString("Tile8Or9Choice.ask"),
				new TileNQuestionImpl(8),
				new TileNQuestionImpl(9)));
		cards.add(cardOf(CTileGreater4QuestionImpl.class));
		cards.add(cardOf(BlackSumQuestionImpl.class));
		cards.add(cardOf(WhiteSumQuestionImpl.class));
		cards.add(cardOf(CountBlackQuestionImpl.class));
		cards.add(cardOf(CountWhiteQuestionImpl.class));
		cards.add(cardOf(CountEvenQuestionImpl.class));
		cards.add(cardOf(CountOddQuestionImpl.class));
		cards.add(cardOf(LeftSumQuestionImpl.class));
		cards.add(cardOf(CenterSumQuestionImpl.class));
		cards.add(cardOf(RightSumQuestionImpl.class));
		cards.add(cardOf(DiffTilesQuestionImpl.class));
		cards.add(cardOf(SameColorQuestionImpl.class));
		cards.add(cardOf(EqualValueQuestionImpl.class));
		cards.add(cardOf(TotalSumQuestionImpl.class));
		cards.add(cardOf(FollowValueQuestionImpl.class));
		return cards;
	}

	public Code getCode(String playerId) {
		if (player1Id.equals(playerId)) {
			return codePlayer1;
		} else if (player2Id.equals(playerId)) {
			return codePlayer2;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public GameStatus getStatus() {
		return status;
	}
}
