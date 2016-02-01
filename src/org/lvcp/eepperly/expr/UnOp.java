package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by eepperly16 on 12/16/15.
 */
public abstract class UnOp implements Expr {
	protected Expr argument;

	@Override
	public List<Expr> getArguments() {
		return Arrays.asList(argument);
	}

	public UnOp(Expr arg1){
		argument = arg1;
	}
}
