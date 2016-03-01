package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class NaturalLog extends UnOp {
	public NaturalLog(Expr argument) {
		super(argument);
	}
	public Expr differentiate(Variable withRespectTo) throws MultivariableException{
		return (new Product(argument.differentiate(withRespectTo), new Power(argument, Expr.MINUS_ONE)));
	}
	public String toString(){
		return ("ln("+argument.toString()+")");
	}
	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new NaturalLog(argument.substitute(subMap));
	}

	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {return Math.log(argument.evaluate(evalMap));}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof NaturalLog)) return false;

		NaturalLog unOp = (NaturalLog) o;

		return !(argument != null ? !argument.equals(unOp.argument) : unOp.argument != null);

	}

	@Override
	public int hashCode() {
		return argument != null ? argument.hashCode() : 0;
	}
}
