package org.lvcp.eepperly.expr;

/**
 * Created by dboyd16 on 12/16/15.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class GeneralLog extends BinOp {
	public GeneralLog(Expr base, Expr yield) {
		super(yield, base);
	}
	public Expr differentiate(){
		return new Sum(
			new Product(
				arguments.get(1).differentiate(),
				Power.unaryMultInv(new Product(
					arguments.get(1).differentiate(),
					new NaturalLog(arguments.get(0))
				))
			),
			Product.unaryNegation(new Product(
				new Product(
					arguments.get(0).differentiate(),
					new NaturalLog(arguments.get(1))
				),
				Power.unaryMultInv(new Product(
					arguments.get(0),
					new Power(
						new NaturalLog(arguments.get(0)),
						Expr.TWO
					)
				))
			))
		);
	}
	public double evaluate(double value) {
		return   Math.log(arguments.get(0).evaluate(value))
		       / Math.log(arguments.get(1).evaluate(value));
	}
	public String toString(){
		return String.format("log_{%s}(%s)", arguments.get(1).toString(), arguments.get(0).toString());
	}

	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new GeneralLog(getArguments().get(0).substitute(subMap),
				getArguments().get(1).substitute(subMap));
	}
}
