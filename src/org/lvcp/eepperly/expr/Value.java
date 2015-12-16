package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.simplify.AbstractSimplifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/16/15.
 */
public abstract class Value extends Expr {
	public Value(List<Expr> arguments) {
		super(arguments);
	}

	public Value(List<Expr> arguments, AbstractSimplifier simplifier) {
		super(arguments, simplifier);
	}

	public Value(){
		super();
	}
}
