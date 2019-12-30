package fr.cookiedev.codebreaker.core.questions;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class QuestionMessages {
	private static final String BUNDLE_NAME = "questions"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private QuestionMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (final MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
