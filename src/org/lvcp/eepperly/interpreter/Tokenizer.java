package org.lvcp.eepperly.interpreter;

import java.util.Iterator;

/**
 * @author Dimitriye Danilovic
 * @since 2/8/16
 */
public interface Tokenizer {
	Iterator<String> tokenize(String string);
}
