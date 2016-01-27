package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Exponential extends UnOp {
	public Exponential(List<Expr> arguments) {
		super(arguments);
	}

	public Expr differentiate() {
		List<Expr> prodTerms = new ArrayList<>();
		prodTerms.add(arguments.get(0).differentiate());
		prodTerms.add(new Exponential(arguments));
		return (new Product(prodTerms));
	}

	public double evaluate(double value){
		return Math.exp(arguments.get(0).evaluate(value));
	}

	public String toString(){
		return ("exp("+arguments.get(0).toString()+")");
	}
}
