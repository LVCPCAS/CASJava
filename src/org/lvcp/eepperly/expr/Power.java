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
					arg2.differentiate(withRespectTo),
					new NaturalLog(arg1)
				),
				new Product(
					new Product(
						arg2,
						Power.unaryMultInv(arg1)
					),
					arg1.differentiate(withRespectTo)
				)
			),
			this
		);
	}
	public String toString(){
		return (arg1.toString()+"^"+arg2.toString());
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



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Power)) return false;

		Power binOp = (Power) o;

		if (arg1 != null ? !arg1.equals(binOp.arg1) : binOp.arg1 != null) return false;
		if (arg2 != null ? !arg2.equals(binOp.arg2) : binOp.arg2 != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = arg1 != null ? arg1.hashCode() : 0;
		result = 31 * result + (arg2 != null ? arg2.hashCode() : 0);
		return result;
	}
}
