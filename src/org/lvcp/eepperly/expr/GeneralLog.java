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
		if (arguments.get(0).hasVars() && arguments.get(1).hasVars()) {
			return new Sum(
					new Product(
							arguments.get(1).differentiate(withRespectTo),
							Power.unaryMultInv(new Product(
									arguments.get(1).differentiate(withRespectTo),
									new NaturalLog(arguments.get(0))
							))
					),
					Product.unaryNegation(new Product(
							arguments.get(0).differentiate(withRespectTo),
							new NaturalLog(arguments.get(1)),
							Power.unaryMultInv(arguments.get(0)),
							new Power(new NaturalLog(arguments.get(0)), new NumConstant(-2))
					))
			);
		} else if (arguments.get(1).hasVars()){
			return new Product(
					arguments.get(1).differentiate(withRespectTo),
					Power.unaryMultInv(new NaturalLog(arguments.get(0))),
					Power.unaryMultInv(arguments.get(1))
			);
		} else if (arguments.get(0).hasVars()){
			return new Product(
					Expr.MINUS_ONE,
					new NaturalLog(arguments.get(1)),
					arguments.get(0).differentiate(withRespectTo),
					Power.unaryMultInv(arguments.get(0)),
					new Power(new NaturalLog(arguments.get(0)),new NumConstant(-2))
			);
		} else{
			return Expr.ZERO;
		}
	}

	public String toString(){
		return String.format("log_{%s}(%s)", arguments.get(0).toString(), arguments.get(1).toString());
	}

	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new GeneralLog(getArguments().get(0).substitute(subMap),
				getArguments().get(1).substitute(subMap));
	}

	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {
		return Math.log(getArguments().get(1).evaluate(evalMap)) /
				Math.log(getArguments().get(0).evaluate(evalMap));
	}
}
