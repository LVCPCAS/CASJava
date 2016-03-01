package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Sine extends UnOp {
	public Sine(Expr argument) {
		super(argument);
	}
	public Expr differentiate(Variable withRespectTo) throws MultivariableException{
		return (new Product(argument.differentiate(withRespectTo), new Cosine(argument)));
	}

	public String toString(){
		return ("sin("+argument.toString()+")");
	}
	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new Sine(argument.substitute(subMap));
	}
	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {return Math.sin(argument.evaluate(evalMap));}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Sine)) return false;

		Sine unOp = (Sine) o;

		return !(argument != null ? !argument.equals(unOp.argument) : unOp.argument != null);

	}

	@Override
	public int hashCode() {
		return argument != null ? argument.hashCode() : 0;
	}
}
