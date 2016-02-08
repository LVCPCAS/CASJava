package org.lvcp.eepperly.interpreter;

import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * @author Dimitriye Danilovic
 * @since 2/1/16
 */
public enum LispTokenizer implements Tokenizer {
	INSTANCE;
	@Override
	public Iterator<String> tokenize(String string) {
		return new RegexMatchIterator(Pattern.compile("\\(|\\)|(?:\\.|[^\\p{Z}\\(\\)])+").matcher(string));
	}
}
