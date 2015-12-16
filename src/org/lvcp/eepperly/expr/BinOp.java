package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.simplify.AbstractSimplifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/16/15.
 */
public abstract class BinOp extends Expr{
	public BinOp(List<Expr> arguments) {
		super(arguments);
	}

	public BinOp(List<Expr> arguments, AbstractSimplifier simplifier) {
		super(arguments, simplifier);
	}

	public BinOp(Expr arg1,Expr arg2){
		super();
		arguments.add(arg1);
		arguments.add(arg2);
	}
}
