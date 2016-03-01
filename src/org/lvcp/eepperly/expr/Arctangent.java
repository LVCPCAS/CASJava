package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Arctangent extends UnOp {
	public Arctangent(Expr argument) {
		super(argument);
	}

	public Expr differentiate(Variable withRespectTo) throws MultivariableException{
		Expr argSquared = new Power(argument, Expr.TWO);
		Expr sum = new Sum(Expr.ONE, argSquared);
		Expr toTheMinusOne = new Power(sum, Expr.MINUS_ONE);
		return (new Product(argument.differentiate(withRespectTo), toTheMinusOne));
	}

	public String toString(){
		return ("arctan("+argument.toString()+")");
	}

	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new Arctangent(argument.substitute(subMap));
	}

	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {
		return Math.atan(argument.evaluate(evalMap));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Arctangent)) return false;

		Arctangent unOp = (Arctangent) o;

		return !(argument != null ? !argument.equals(unOp.argument) : unOp.argument != null);

	}

	@Override
	public int hashCode() {
		return argument != null ? argument.hashCode() : 0;
	}

}
