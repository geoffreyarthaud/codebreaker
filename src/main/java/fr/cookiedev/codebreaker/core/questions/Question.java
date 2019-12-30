package fr.cookiedev.codebreaker.core.questions;

import fr.cookiedev.codebreaker.core.Code;

public interface Question {
	String answer(Code code);

	String ask();
}
