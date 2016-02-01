package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Arcsine extends UnOp {
	public Arcsine(Expr argument) {
		super(argument);
	}

	public Expr differentiate(){
		Expr argSquared = new Power(argument, Expr.TWO);
		Expr sum = new Sum(Expr.ONE, Product.unaryNegation(argSquared));
		Expr toTheMinusOneHalf = new Power(sum, new NumConstant(-0.5));
		return (new Product(argument.differentiate(), toTheMinusOneHalf));
	}

	public double evaluate(double value){
		return Math.asin(argument.evaluate(value));
	}

	public String toString(){
		return ("arcsin("+argument.toString()+")");
	}

}
