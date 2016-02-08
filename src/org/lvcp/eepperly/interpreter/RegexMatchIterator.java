package org.lvcp.eepperly.interpreter;

import java.util.Iterator;
import java.util.regex.Matcher;

/**
 * @author Dimitriye Danilovic
 * @since 2/1/16
 */
public class RegexMatchIterator implements Iterator<String> {
	private Matcher matcher;

	public RegexMatchIterator(Matcher matcher) {
		this.matcher = matcher;
	}

	private boolean findCache = false;
	@Override
	public boolean hasNext() {
		return findCache || (findCache = matcher.find());
	}

	@Override
	public String next() {
		hasNext();
		String ret = matcher.group();
		findCache = false;
		return ret;
	}
}
