package org.lvcp.eepperly.expr;

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
	public Expr differentiate(){
		return (new Product(argument.differentiate(), new Power(argument, Expr.MINUS_ONE)));
	}
	public double evaluate(double value){
		return Math.log(argument.evaluate(value));
	}
	public String toString(){
		return ("ln("+argument.toString()+")");
	}
	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return new NaturalLog(argument.substitute(subMap));
	}
}
