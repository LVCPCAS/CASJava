package org.lvcp.eepperly.simplify;

import org.lvcp.eepperly.exception.ExprTypeException;
import org.lvcp.eepperly.expr.Expr;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class TrivialSimplifier extends AbstractSimplifier {
	public Expr simplify(Expr expression) throws ExprTypeException {return expression;}
}
