package fr.cookiedev.codebreaker.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.cookiedev.codebreaker.core.questions.QuestionCard;

@ExtendWith(MockitoExtension.class)
class QuestionCardDeckTest {

	@Mock
	private QuestionCard qc1;

	@Mock
	private QuestionCard qc2;

	@Mock
	private QuestionCard qc3;

	@Mock
	private QuestionCard qc4;

	@Mock
	private QuestionCard qc5;

	private QuestionCardDeck qcd;

	@BeforeEach
	public void setup() {
		qcd = new QuestionCardDeck(Arrays.asList(qc1, qc2, qc3, qc4, qc5));
	}

	@Test
	void whenInitDeckWith3RevealedCards_thenGetDeckObtainRemainingCards() {
		// WHEN
		qcd.initDeck(3);
		final List<QuestionCard> revealed = qcd.getRevealed();

		// THEN
		assertThat(qcd.getStack()).hasSize(2).doesNotContainAnyElementsOf(revealed);
	}

	@Test
	void whenInitDeckWithMoreRevealedThanTotalCards_thenThrowIAE() {
		// WHEN
		assertThrows(IllegalArgumentException.class, () -> qcd.initDeck(6));
	}

	@Test
	void given3Deck_whenDrawACard_thenRevealedIsCorrectlyUpdated() {
		// GIVEN
		qcd.initDeck(3);
		final List<QuestionCard> revealed = qcd.getRevealed();

		// WHEN
		final QuestionCard drawCard = qcd.draw(revealed.get(1));

		// THEN
		assertThat(qcd.getRevealed()).hasSize(3).doesNotContain(drawCard).contains(revealed.get(0), revealed.get(2));
	}

	@Test
	void given3Deck_whenDrawACard_thenDiscardIsCorrectlyUpdated() {
		// GIVEN
		qcd.initDeck(3);
		final List<QuestionCard> revealed = qcd.getRevealed();

		// WHEN
		final QuestionCard drawCard = qcd.draw(revealed.get(1));

		// THEN
		assertThat(qcd.getDiscard()).hasSize(1).contains(drawCard);
	}

	@Test
	void given3Deck_whenDrawACard_thenStackIsCorrectlyUpdated() {
		// GIVEN
		qcd.initDeck(3);
		final List<QuestionCard> revealed = qcd.getRevealed();
		final List<QuestionCard> stack = qcd.getStack();

		// WHEN
		qcd.draw(revealed.get(1));
		final QuestionCard replacedCard = qcd.getRevealed().stream().filter(qc -> !revealed.contains(qc)).findFirst()
				.get();

		// THEN
		assertThat(qcd.getStack())
				.containsExactlyInAnyOrder(stack.stream().filter(qc -> !qc.equals(replacedCard)).findFirst().get());
	}

	@Test
	void given3Deck_whenDrawNotRevealedCard_thenThrowIAE() {
		// GIVEN
		qcd.initDeck(3);
		final QuestionCard badCard = qcd.getStack().get(0);

		// WHEN
		assertThrows(IllegalArgumentException.class, () -> qcd.draw(badCard));
	}

	@Test
	void given3Deck_whenDrawDiscardCard_thenThrowIAE() {
		// GIVEN
		qcd.initDeck(3);
		qcd.draw(qcd.getRevealed().get(0));
		final QuestionCard badCard = qcd.getDiscard().get(0);

		// WHEN
		assertThrows(IllegalArgumentException.class, () -> qcd.draw(badCard));
	}

}
