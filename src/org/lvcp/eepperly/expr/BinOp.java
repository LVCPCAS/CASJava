package org.lvcp.eepperly.expr;

import java.util.*;

/**
 * Created by eepperly16 on 12/16/15.
 */
public abstract class BinOp implements Expr {
	protected Expr arg1, arg2;

	@Override
	public List<Expr> getArguments() {
		return Arrays.asList(arg1, arg2);
	}

	public Expr getArg1(){
		return arg1;
	}

	public Expr getArg2(){
		return arg2;
	}

	public BinOp(Expr arg1, Expr arg2){
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
}
