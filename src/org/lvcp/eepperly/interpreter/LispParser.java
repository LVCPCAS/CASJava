package org.lvcp.eepperly.interpreter;

import java.util.Iterator;

import org.lvcp.eepperly.interpreter.UnsafeTree.UnsafeTreeBuilder;

/**
 * @author Dimitriye Danilovic
 * @since 2/1/16
 */
public enum LispParser implements Parser {
	INSTANCE;
	@Override
	public UnsafeTree parse(Iterator<String> tokens) {
		int ignoreLast = -1;
		UnsafeTreeBuilder builder = UnsafeTree.builder();
		while (tokens.hasNext()) {
			String token = tokens.next();

			if (!tokens.hasNext() && ignoreLast == 1) {
				break;
			}

			if (ignoreLast == -1) {
				if ("(".equals(token)) {
					ignoreLast = 1;
					continue;
				} else {
					ignoreLast = 0;
				}
			}

			if (")".equals(token)) {
				builder = builder.pop();
			} else if ("(".equals(token)) {
				builder = builder.addInternal();
			} else {
				builder = builder.addLeaf(token);
			}
		}

		return builder.build();
	}
}
