package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Arcosine extends Expr {
	public Arcosine(List<Expr> arguments) {
		super(arguments);
	}
	public Expr differentiate(){
		List <Expr> arcosineList = new ArrayList<>();
		arcosineList.add(arguments.get(0));
		arcosineList.add(Expr.TWO);
		Expr argSquared= new Exponential(arcosineList);
		List <Expr> sumList = new ArrayList<>();
		sumList.add(Expr.ONE);
		sumList.add(argSquared);
		Expr sum = new Sum(sumList);
		List <Expr> toTheMinusOneHalfList = new ArrayList<>();
		toTheMinusOneHalfList.add(sum);
		toTheMinusOneHalfList.add(new NumConstant(-0.5));
		Expr toTheMinusOneHalf = new Exponential(toTheMinusOneHalfList);
		List <Expr> prodList = new ArrayList<>();
		prodList.add(toTheMinusOneHalf);
		prodList.add(arguments.get(0).differentiate());
		prodList.add(Expr.MINUS_ONE);
		return new Product(prodList);
	}

	public double evaluate(double value){
		return Math.acos(arguments.get(0).evaluate(value));
	}

}
