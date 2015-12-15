package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Arctangent extends Expr {
	public Arctangent(List<Expr> arguments) {
		super(arguments);
	}

	public Expr differentiate(){
		List<Expr> argSquaredList = new ArrayList<>();
		argSquaredList.add(arguments.get(0));
		argSquaredList.add(Expr.TWO);
		Expr argSquared = new Exponential(argSquaredList);
		List<Expr> sumList = new ArrayList<>();
		sumList.add(Expr.ONE);
		sumList.add(argSquared);
		Expr sum = new Sum(sumList);
		List<Expr> toTheMinusOneList = new ArrayList<>();
		toTheMinusOneList.add(sum);
		toTheMinusOneList.add(Expr.MINUS_ONE);
		Expr toTheMinusOne = new Exponential(toTheMinusOneList);
		List<Expr> prodList = new ArrayList<>();
		prodList.add(arguments.get(0).differentiate());
		prodList.add(toTheMinusOne);
		return (new Product(prodList));
	}

	public double evaluate(double value){
		return Math.atan(arguments.get(0).evaluate(value));
	}

	public String toString(){
		return ("arctan("+arguments.get(0).toString()+")");
	}

}
