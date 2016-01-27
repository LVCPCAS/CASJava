package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class NaturalLog extends UnOp {
	public NaturalLog(Expr argument) {
		super(argument);
	}
	public Expr differentiate(){
		return (new Product(arguments.get(0).differentiate(), new Power(arguments.get(0), Expr.MINUS_ONE)));
	}
	public double evaluate(double value){
		return Math.log(arguments.get(0).evaluate(value));
	}
	public String toString(){
		return ("ln("+arguments.get(0).toString()+")");
	}
}
