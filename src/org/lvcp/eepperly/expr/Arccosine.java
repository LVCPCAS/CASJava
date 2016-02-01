package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Arccosine extends UnOp {
	public Arccosine(Expr argument) {
		super(argument);
	}
	public Expr differentiate(){
		List <Expr> arccosineList = new ArrayList<>();
		Expr argSquared= new Power(argument, Expr.TWO);
		Expr sum = new Sum(Expr.ONE, Product.unaryNegation(argSquared));
		Expr toTheMinusOneHalf = new Power(sum, new NumConstant(-0.5));
		return new Product(toTheMinusOneHalf, argument.differentiate(), Expr.MINUS_ONE);
	}

	public double evaluate(double value){
		return Math.acos(argument.evaluate(value));
	}

	public String toString(){
		return ("arccos("+argument.toString()+")");
	}

}
