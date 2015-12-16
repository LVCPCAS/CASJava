package org.lvcp.eepperly.expr;

import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Variable extends Value {
	private String varName;
	public Variable(List<Expr> arguments) {
		super(arguments);
		varName = "X";
	}
	public Variable(List<Expr> arguments,String varName) {
		super(arguments);
		this.varName = varName;
	}

	public Expr differentiate(){
		return Expr.ONE;
	}
	public double evaluate(double value){ return value; }
	public String toString(){
		return varName;
	}
	public boolean equals(Object obj){
		return obj instanceof Variable;
	}
}
