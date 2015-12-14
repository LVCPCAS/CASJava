package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class NaturalLog extends Expr {
	public NaturalLog(List<Expr> arguments) {
		super(arguments);
	}
	public Expr differentiate(){
		List<Expr> expTerms = new ArrayList<>();
		expTerms.add(arguments.get(0));
		expTerms.add(Expr.MINUS_ONE);
		Expr expTerm = new Exponential(expTerms);
		List<Expr> prodTerms = new ArrayList<>();
		prodTerms.add(arguments.get(0).differentiate());
		prodTerms.add(expTerm);
		return (new Product(prodTerms));
	}
	public double evaluate(double value){
		return Math.log(arguments.get(0).evaluate(value));
	}
	public String toString(){
		return ("ln("+arguments.get(0).toString()+")");
	}
}
