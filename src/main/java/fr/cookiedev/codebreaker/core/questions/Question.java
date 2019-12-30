package fr.cookiedev.codebreaker.core.questions;

import fr.cookiedev.codebreaker.core.Code;

@FunctionalInterface
public interface Question {
	String answer(Code code);

	default String ask() {
		return QuestionMessages.getString(getClass().getSimpleName() + ".ask"); //$NON-NLS-1$
	}

}
