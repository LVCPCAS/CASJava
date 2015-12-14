package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Cosine extends Expr {
	public Cosine(List<Expr> arguments) {
		super(arguments);
	}
	public Expr differentiate(){
		List<Expr> prodTerms = new ArrayList<>();
		prodTerms.add(Expr.MINUS_ONE);
		prodTerms.add(arguments.get(0).differentiate());
		prodTerms.add(new Sine(arguments));
		return (new Product(prodTerms));
	}
	public double evaluate(double value){
		return Math.cos(arguments.get(0).evaluate(value));
	}

	public String toString(){
		return ("cos("+arguments.get(0).toString()+")");
	}
}
