package fr.cookiedev.codebreaker.core.questions;

import java.util.List;

import fr.cookiedev.codebreaker.core.Code;

public interface QuestionCard {

	String getId();

	String getQuestionMessage();

	String answer(int choice, Code code);

	List<Question> getQuestions();
}
