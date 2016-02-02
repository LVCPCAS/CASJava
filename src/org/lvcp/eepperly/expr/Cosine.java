package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Cosine extends UnOp {
	public Cosine(Expr argument) {
		super(argument);
	}
	public Expr differentiate(Variable withRespectTo) throws MultivariableException{
		return (new Product(Expr.MINUS_ONE, argument.differentiate(withRespectTo), new Sine(argument)));
	}

	public String toString(){
		return ("cos("+argument.toString()+")");
	}

	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new Cosine(argument.substitute(subMap));
	}
	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {return Math.cos(argument.evaluate(evalMap));}
}
