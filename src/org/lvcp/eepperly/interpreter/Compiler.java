package org.lvcp.eepperly.interpreter;

import org.lvcp.eepperly.expr.Expr;

/**
 * @author Dimitriye Danilovic
 * @since 2/8/16
 */
public interface Compiler {
	Expr compile(UnsafeTree unsafeTree);
}
