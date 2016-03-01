package org.lvcp.eepperly.expr;

/**
 * Created by dboyd16 on 12/16/15.
 */
import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class GeneralLog extends BinOp {
	public GeneralLog(Expr base, Expr yield) {
		super(base, yield);
	}
	public Expr differentiate(Variable withRespectTo) throws MultivariableException{
		if (arg1.hasVars() && arg2.hasVars()) {
			return new Sum(
					new Product(
							arg2.differentiate(withRespectTo),
							Power.unaryMultInv(new Product(
									arg2.differentiate(withRespectTo),
									new NaturalLog(arg1)
							))
					),
					Product.unaryNegation(new Product(
							arg1.differentiate(withRespectTo),
							new NaturalLog(arg2),
							Power.unaryMultInv(arg1),
							new Power(new NaturalLog(arg1), new NumConstant(-2))
					))
			);
		} else if (arg2.hasVars()){
			return new Product(
					arg2.differentiate(withRespectTo),
					Power.unaryMultInv(new NaturalLog(arg1)),
					Power.unaryMultInv(arg2)
			);
		} else if (arg1.hasVars()){
			return new Product(
					Expr.MINUS_ONE,
					new NaturalLog(arg2),
					arg1.differentiate(withRespectTo),
					Power.unaryMultInv(arg1),
					new Power(new NaturalLog(arg1),new NumConstant(-2))
			);
		} else{
			return Expr.ZERO;
		}
	}

	public String toString(){
		return String.format("log_{%s}(%s)", arg1.toString(), arg2.toString());
	}

	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new GeneralLog(getArguments().get(0).substitute(subMap),
				getArguments().get(1).substitute(subMap));
	}

	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {
		return Math.log(arg2.evaluate(evalMap)) /
				Math.log(arg1.evaluate(evalMap));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof GeneralLog)) return false;

		GeneralLog binOp = (GeneralLog) o;

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
