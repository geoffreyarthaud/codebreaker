package fr.cookiedev.codebreaker.game;

import static fr.cookiedev.codebreaker.core.questions.QuestionUtils.cardOf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class QuestionCardDeck {

	private final List<QuestionCard> discard;

	private final List<QuestionCard> deck;

	public QuestionCardDeck() {
		deck = new ArrayList<>(21);
		discard = new ArrayList<>();
		fillDeck();
	}

	private void fillDeck() {
		deck.add(cardOf(new TileNQuestionImpl(0)));
		deck.add(cardOf(new TileNQuestionImpl(5)));
		deck.add(cardOf(QuestionMessages.getString("Tile1Or2Choice.ask"),
				new TileNQuestionImpl(1),
				new TileNQuestionImpl(2)));
		deck.add(cardOf(QuestionMessages.getString("Tile3Or4Choice.ask"),
				new TileNQuestionImpl(3),
				new TileNQuestionImpl(4)));
		deck.add(cardOf(QuestionMessages.getString("Tile6Or7Choice.ask"),
				new TileNQuestionImpl(6),
				new TileNQuestionImpl(7)));
		deck.add(cardOf(QuestionMessages.getString("Tile8Or9Choice.ask"),
				new TileNQuestionImpl(8),
				new TileNQuestionImpl(9)));
		deck.add(cardOf(CTileGreater4QuestionImpl.class));
		deck.add(cardOf(BlackSumQuestionImpl.class));
		deck.add(cardOf(WhiteSumQuestionImpl.class));
		deck.add(cardOf(CountBlackQuestionImpl.class));
		deck.add(cardOf(CountWhiteQuestionImpl.class));
		deck.add(cardOf(CountEvenQuestionImpl.class));
		deck.add(cardOf(CountOddQuestionImpl.class));
		deck.add(cardOf(LeftSumQuestionImpl.class));
		deck.add(cardOf(CenterSumQuestionImpl.class));
		deck.add(cardOf(RightSumQuestionImpl.class));
		deck.add(cardOf(DiffTilesQuestionImpl.class));
		deck.add(cardOf(SameColorQuestionImpl.class));
		deck.add(cardOf(EqualValueQuestionImpl.class));
		deck.add(cardOf(TotalSumQuestionImpl.class));
		deck.add(cardOf(FollowValueQuestionImpl.class));
		Collections.shuffle(deck);
	}

	// TODO Get cards and fill discards
}
