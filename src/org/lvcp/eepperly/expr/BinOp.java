package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by eepperly16 on 12/16/15.
 */
public abstract class BinOp implements Expr {
	protected List<Expr> arguments;

	@Override
	public List<Expr> getArguments() {
		return Collections.unmodifiableList(arguments);
	}

	public BinOp(Expr arg1, Expr arg2){
		arguments = new ArrayList<>();
		arguments.add(arg1);
		arguments.add(arg2);
	}
}
