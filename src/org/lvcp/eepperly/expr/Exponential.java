package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Exponential extends UnOp {
	public Exponential(Expr power) {
		super(power);
	}

	public Expr differentiate(Variable withRespectTo) throws MultivariableException {
		return new Product(argument.differentiate(withRespectTo), this);
	}

	public String toString(){
		return ("exp("+argument.toString()+")");
	}

	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new Exponential(argument.substitute(subMap));
	}

	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {return Math.exp(argument.evaluate(evalMap));}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Exponential)) return false;

		Exponential unOp = (Exponential) o;

		return !(argument != null ? !argument.equals(unOp.argument) : unOp.argument != null);

	}

	@Override
	public int hashCode() {
		return argument != null ? argument.hashCode() : 0;
	}
}
