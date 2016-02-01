package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Exponential extends UnOp {
	public Exponential(Expr power) {
		super(power);
	}

	public Expr differentiate() {
		return new Product(argument, this);
	}

	public double evaluate(double value){
		return Math.exp(argument.evaluate(value));
	}

	public String toString(){
		return ("exp("+argument.toString()+")");
	}

	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new Exponential(argument.substitute(subMap));
	}
}
