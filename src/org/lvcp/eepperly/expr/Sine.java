package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Sine extends UnOp {
	public Sine(List<Expr> arguments) {
		super(arguments);
	}
	public Expr differentiate(){
		List<Expr> prodTerms = new ArrayList<>();
		prodTerms.add(arguments.get(0).differentiate());
		prodTerms.add(new Cosine(arguments));
		return (new Product(prodTerms));
	}
	public double evaluate(double value){
		return Math.sin(arguments.get(0).evaluate(value));
	}

	public String toString(){
		return ("sin("+arguments.get(0).toString()+")");
	}
}
