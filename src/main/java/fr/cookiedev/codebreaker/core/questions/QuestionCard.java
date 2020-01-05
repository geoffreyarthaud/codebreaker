package fr.cookiedev.codebreaker.core.questions;

import fr.cookiedev.codebreaker.core.Code;

public interface QuestionCard {

	String getId();

	String getQuestionMessage();

	String answer(int choice, Code code);
}
