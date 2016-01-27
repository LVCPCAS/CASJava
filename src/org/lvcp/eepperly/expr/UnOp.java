package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/16/15.
 */
public abstract class UnOp implements Expr {
	protected List<Expr> arguments;

	@Override
	public List<Expr> getArguments() {
		return arguments;
	}

	public UnOp(Expr arg1){
		arguments = new ArrayList<>();
		arguments.add(arg1);
	}
}
