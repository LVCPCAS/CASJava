package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Sine extends UnOp {
	public Sine(Expr argument) {
		super(argument);
	}
	public Expr differentiate(){
		return (new Product(argument.differentiate(), new Cosine(argument)));
	}
	public double evaluate(double value){
		return Math.sin(argument.evaluate(value));
	}

	public String toString(){
		return ("sin("+argument.toString()+")");
	}
}
