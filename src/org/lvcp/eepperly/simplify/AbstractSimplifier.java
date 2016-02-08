package org.lvcp.eepperly.simplify;

import org.lvcp.eepperly.exception.ExprTypeException;
import org.lvcp.eepperly.expr.Expr;

/**
 * Created by eepperly16 on 12/14/15.
 */
public abstract class AbstractSimplifier {
	public static final AbstractSimplifier TRIVIAL_SIMPLIFIER = new TrivialSimplifier();
	public abstract Expr simplify(Expr expression) throws ExprTypeException;
}
