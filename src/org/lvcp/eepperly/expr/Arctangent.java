package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Arctangent extends UnOp {
	public Arctangent(Expr argument) {
		super(argument);
	}

	public Expr differentiate(){
		Expr argSquared = new Power(argument, Expr.TWO);
		Expr sum = new Sum(Expr.ONE, argSquared);
		Expr toTheMinusOne = new Power(argSquared, Expr.MINUS_ONE);
		return (new Product(argument.differentiate(), toTheMinusOne));
	}

	public double evaluate(double value){
		return Math.atan(argument.evaluate(value));
	}

	public String toString(){
		return ("arctan("+argument.toString()+")");
	}

}
