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
		return new Product(
			new Sum(
				new Product(
					arguments.get(1).differentiate(),
					new NaturalLog(arguments.get(0))
				),
				new Product(
					new Product(
						arguments.get(1),
						Power.unaryMultInv(arguments.get(0))
					),
					arguments.get(0).differentiate()
				)
			),
			this
		);
	}
	public double evaluate(double value){
		return Math.pow(arguments.get(0).evaluate(value),arguments.get(1).evaluate(value));
	}
	public String toString(){
		return (arguments.get(0).toString()+"^"+arguments.get(1).toString());
	}
	public static Power unaryMultInv(Expr expression){
		return new Power(expression, Expr.MINUS_ONE);
	}
}
