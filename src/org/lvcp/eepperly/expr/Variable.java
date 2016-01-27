package org.lvcp.eepperly.expr;

import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Variable implements Expr {
	private String varName;
	public Variable(List<Expr> arguments) {
		varName = "X";
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
