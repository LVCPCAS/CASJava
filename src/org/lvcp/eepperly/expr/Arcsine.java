package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Arcsine extends UnOp {
	public Arcsine(Expr argument) {
		super(argument);
	}

	public Expr differentiate(Variable withRespectTo) throws MultivariableException {
		Expr argSquared = new Power(argument, Expr.TWO);
		Expr sum = new Sum(Expr.ONE, Product.unaryNegation(argSquared));
		Expr toTheMinusOneHalf = new Power(sum, new NumConstant(-0.5));
		return (new Product(argument.differentiate(withRespectTo), toTheMinusOneHalf));
	}

	public String toString(){
		return ("arcsin("+argument.toString()+")");
	}

	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new Arcsine(argument.substitute(subMap));
	}

	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException{
		return Math.asin(argument.evaluate(evalMap));
	}

}
