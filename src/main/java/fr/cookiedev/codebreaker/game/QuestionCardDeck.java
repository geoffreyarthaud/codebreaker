package fr.cookiedev.codebreaker.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.cookiedev.codebreaker.core.questions.QuestionCard;

public class QuestionCardDeck {

	private final List<QuestionCard> discard;

	private final List<QuestionCard> revealed;

	private int nextIndex;

	private final List<QuestionCard> deck;

	public QuestionCardDeck(List<QuestionCard> deck) {
		this.deck = new ArrayList<QuestionCard>(deck);
		discard = new ArrayList<>();
		revealed = new ArrayList<>();
		nextIndex = 0;
	}

	public void initDeck(int nbRevealed) {
		if (nbRevealed > deck.size()) {
			throw new IllegalArgumentException();
		}
		discard.clear();
		revealed.clear();
		Collections.shuffle(deck);
		revealed.addAll(deck.subList(0, nbRevealed));
		nextIndex = nbRevealed;
	}

	public List<QuestionCard> getStack() {
		return deck.subList(nextIndex, deck.size());
	}

	public List<QuestionCard> getRevealed() {
		return new ArrayList<>(revealed);
	}

	public QuestionCard draw(QuestionCard qc) {
		if (!revealed.contains(qc)) {
			throw new IllegalArgumentException();
		}
		revealed.remove(qc);
		revealed.add(deck.get(nextIndex++));
		discard.add(qc);
		return qc;
	}

	public List<QuestionCard> getDiscard() {
		return new ArrayList<>(discard);
	}
}
