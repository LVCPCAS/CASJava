package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Arcsine extends UnOp {
	public Arcsine(List<Expr> arguments) {
		super(arguments);
	}

	public Expr differentiate(){
		List<Expr> argSquaredList = new ArrayList<>();
		argSquaredList.add(arguments.get(0));
		argSquaredList.add(Expr.TWO);
		Expr argSquared = new Exponential(argSquaredList);
		List<Expr> sumList = new ArrayList<>();
		sumList.add(Expr.ONE);
		sumList.add(Product.unitaryNegation(argSquared));
		Expr sum = new Sum(sumList);
		List<Expr> toTheMinusOneHalfList = new ArrayList<>();
		toTheMinusOneHalfList.add(sum);
		toTheMinusOneHalfList.add(new NumConstant(-0.5));
		Expr toTheMinusOneHalf = new Exponential(toTheMinusOneHalfList);
		List<Expr> prodList = new ArrayList<>();
		prodList.add(arguments.get(0).differentiate());
		prodList.add(toTheMinusOneHalf);
		return (new Product(prodList));
	}

	public double evaluate(double value){
		return Math.asin(arguments.get(0).evaluate(value));
	}

	public String toString(){
		return ("arcsin("+arguments.get(0).toString()+")");
	}

}
