package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Power extends BinOp {
	public Power(Expr base, Expr exponent) {
		super(base, exponent);
	}
	public Expr differentiate(){
		List<Expr> lnTerms = new ArrayList<>();
		lnTerms.add(arguments.get(0));
		Expr lnTerm = new NaturalLog(lnTerms);
		List<Expr> prodTerms = new ArrayList<>();
		prodTerms.add(arguments.get(1));
		prodTerms.add(lnTerm);
		Expr prodTerm = new Product(prodTerms);
		List<Expr> expTerms = new ArrayList<>();
		expTerms.add(prodTerm);
		Expr expTerm = new Exponential(expTerms);
		return expTerm.differentiate();
	}
	public double evaluate(double value){
		return Math.pow(arguments.get(0).evaluate(value),arguments.get(1).evaluate(value));
	}
	public String toString(){
		return (arguments.get(0).toString()+"^"+arguments.get(1).toString());
	}
	public static Power unitaryMultInv(Expr expression){
		return new Power(expression, Expr.MINUS_ONE);
	}
}
