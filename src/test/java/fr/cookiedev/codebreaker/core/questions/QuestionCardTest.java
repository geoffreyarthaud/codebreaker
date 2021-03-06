package fr.cookiedev.codebreaker.core.questions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.cookiedev.codebreaker.core.Code;

@ExtendWith(MockitoExtension.class)
class QuestionCardTest {

	@Mock
	Question q1;

	@Mock
	Question q2;

	@Mock
	Question q3;

	@Mock
	Code code;

	@Test
	void givenSimpleQuestion_whenGetQuestionMessage_thenGetQuestionClassMsg() {
		// GIVEN
		when(q1.ask()).thenReturn("ASK1");
		final QuestionCard simpleCard = new SimpleQuestionCardImpl(q1);

		// WHEN
		final String actual = simpleCard.getQuestionMessage();

		// THEN
		assertThat(actual).isEqualTo("ASK1");
	}

	@Test
	void givenSimpleQuestion_whenAnswer_thenAnswerQuestionClassMsg() {
		// GIVEN
		when(q1.answer(code)).thenReturn("ANSWER1");
		final QuestionCard simpleCard = new SimpleQuestionCardImpl(q1);

		// WHEN
		final String actual = simpleCard.answer(0, code);

		// THEN
		assertThat(actual).isEqualTo("ANSWER1");
	}

	@Test
	void whenCreateSimpleQuestionWithNull_thenThrowNPE() {
		// WHEN
		assertThrows(NullPointerException.class, () -> new SimpleQuestionCardImpl(null));
	}

	@Test
	void givenChoiceQuestion_whenGetQuestionMessage_thenGetQuestionClassMsg() {
		// GIVEN
		final QuestionCard choiceCard = new ChoiceQuestionCardImpl("ASKGLOBAL", q2, q3);

		// WHEN
		final String actual = choiceCard.getQuestionMessage();

		// THEN
		assertThat(actual).isEqualTo("ASKGLOBAL");
	}

	@Test
	void givenChoiceQuestion_whenAnswer_thenAnswerQuestionClassMsg() {
		// GIVEN
		when(q3.answer(code)).thenReturn("ANSWER1");
		final QuestionCard choiceCard = new ChoiceQuestionCardImpl("ASKGLOBAL", q2, q3);

		// WHEN
		final String actual = choiceCard.answer(1, code);

		// THEN
		assertThat(actual).isEqualTo("ANSWER1");
	}

	@Test
	void givenSimpleQuestion_whenGetQuestions_thenGetCorrectQuestions() {
		// GIVEN
		final QuestionCard simpleCard = new SimpleQuestionCardImpl(q1);

		// WHEN
		final List<Question> actualQuestions = simpleCard.getQuestions();

		// THEN
		assertThat(actualQuestions).containsExactlyInAnyOrder(q1);
	}

	@Test
	void givenChoiceQuestion_whenGetQuestions_thenGetCorrectQuestions() {
		// GIVEN
		final QuestionCard choiceCard = new ChoiceQuestionCardImpl("ASKGLOBAL", q2, q3);

		// WHEN
		final List<Question> actualQuestions = choiceCard.getQuestions();

		// THEN
		assertThat(actualQuestions).containsExactlyInAnyOrder(q2, q3);
	}

	@Test
	void whenCreateChoiceQuestionWithNoQuestion_thenThrowIAE() {
		// WHEN
		assertThrows(IllegalArgumentException.class, () -> new ChoiceQuestionCardImpl("ASKGLOBAL"));
	}

	@Test
	void whenCreateChoiceQuestionWithOneQuestion_thenThrowIAE() {
		// WHEN
		assertThrows(IllegalArgumentException.class, () -> new ChoiceQuestionCardImpl("ASKGLOBAL", q1));
	}

	@Test
	void whenCreateChoiceQuestionWithNull_thenThrowNPE() {
		// WHEN
		assertThrows(NullPointerException.class, () -> new ChoiceQuestionCardImpl("ASKGLOBAL", (Question[]) null));
	}

	@Test
	void whenCreateChoiceQuestionWithOneNull_thenThrowNPE() {
		// WHEN
		assertThrows(NullPointerException.class, () -> new ChoiceQuestionCardImpl("ASKGLOBAL", q2, null));
	}

	@Test
	void whenCreateChoiceWithNullMsg_thenThrowNPE() {
		// WHEN
		assertThrows(NullPointerException.class, () -> new ChoiceQuestionCardImpl(null, q2, q3));
	}

}
