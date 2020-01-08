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

	private GameStatus status;

	private final QuestionCardDeck deck;

	private final Code codePlayer1;

	private final Code codePlayer2;

	public Game(String player1Id, String player2Id) {
		this(player1Id, player2Id, defaultDeck(), 6);
	}

	public Game(String player1Id, String player2Id, List<QuestionCard> deckCards, int nbCards) {
		this.player1Id = Objects.requireNonNull(player1Id);
		this.player2Id = Objects.requireNonNull(player2Id);

		if (player1Id.isBlank() || player2Id.isBlank()) {
			throw new IllegalArgumentException();
		}

		final List<Tile> tiles = Tile.tilesList();
		Collections.shuffle(tiles);
		codePlayer1 = new Code(tiles.get(0), tiles.get(1), tiles.get(2), tiles.get(3), tiles.get(4));
		codePlayer2 = new Code(tiles.get(5), tiles.get(6), tiles.get(7), tiles.get(8), tiles.get(9));

		status = GameStatus.P1_TURN;
		// FIXME : DIP !!!
		deck = new QuestionCardDeck(deckCards);
		deck.initDeck(nbCards);

	}

	public List<QuestionCard> getAvailableQuestions() {
		return deck.getRevealed();
	}

	private static List<QuestionCard> defaultDeck() {
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

	public String ask(String playerId, QuestionCard qc, int idQuestion) {
		requireCanAsk(playerId);
		status = status == GameStatus.P1_TURN ? GameStatus.P2_TURN : GameStatus.P1_TURN;
		return qc.answer(idQuestion, getCode(playerId));
	}

	private void requireCanAsk(String playerId) {
		if (!player1Id.equals(playerId) && !player2Id.equals(playerId)) {
			throw new IllegalArgumentException();
		}
		if (player1Id.equals(playerId) && status != GameStatus.P1_TURN
				|| player2Id.equals(playerId) && status != GameStatus.P2_TURN) {
			throw new IllegalStateException();
		}
	}

	public boolean tryCode(String playerId, Code tryCode) {
		requireCanAsk(playerId);
		final Code actualCode = status == GameStatus.P1_TURN ? codePlayer2 : codePlayer1;
		final boolean codeMatch = Code.match(tryCode, actualCode);
		if (codeMatch) {
			status = status == GameStatus.P1_TURN ? GameStatus.P1_WIN : GameStatus.P2_WIN;
		} else {
			status = status == GameStatus.P1_TURN ? GameStatus.P2_TURN : GameStatus.P1_TURN;
		}
		return codeMatch;
	}
}
