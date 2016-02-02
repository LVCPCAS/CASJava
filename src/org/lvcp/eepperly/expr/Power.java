package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Power extends BinOp {
	public Power(Expr base, Expr exponent) {
		super(base, exponent);
	}
	public Expr differentiate(Variable withRespectTo) throws MultivariableException{
		return new Product(
			new Sum(
				new Product(
					arguments.get(1).differentiate(withRespectTo),
					new NaturalLog(arguments.get(0))
				),
				new Product(
					new Product(
						arguments.get(1),
						Power.unaryMultInv(arguments.get(0))
					),
					arguments.get(0).differentiate(withRespectTo)
				)
			),
			this
		);
	}
	public String toString(){
		return (arguments.get(0).toString()+"^"+arguments.get(1).toString());
	}
	public static Power unaryMultInv(Expr expression){
		return new Power(expression, Expr.MINUS_ONE);
	}
	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new Power(getArguments().get(0).substitute(subMap),
				getArguments().get(1).substitute(subMap));
	}

	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {
		return Math.pow(getArguments().get(0).evaluate(evalMap),
				getArguments().get(1).evaluate(evalMap));
	}
}
