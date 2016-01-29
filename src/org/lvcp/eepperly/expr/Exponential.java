package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Exponential extends UnOp {
	public Exponential(Expr power) {
		super(power);
	}

	public Expr differentiate() {
		return new Product(arguments.get(0), this);
	}

	public double evaluate(double value){
		return Math.exp(arguments.get(0).evaluate(value));
	}

	public String toString(){
		return ("exp("+arguments.get(0).toString()+")");
	}
}
