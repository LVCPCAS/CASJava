package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Cosine extends UnOp {
	public Cosine(Expr argument) {
		super(argument);
	}
	public Expr differentiate(){
		return (new Product(Expr.MINUS_ONE, argument.differentiate(), new Sine(argument)));
	}
	public double evaluate(double value){
		return Math.cos(argument.evaluate(value));
	}

	public String toString(){
		return ("cos("+argument.toString()+")");
	}
}
