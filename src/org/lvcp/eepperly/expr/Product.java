package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Product extends BinOp {
	public Product(Expr factor1, Expr factor2) {
		super(factor1, factor2);
	}
	public Expr differentiate(){
		return new Sum(new Product(arguments.get(0).differentiate(), arguments.get(1)),
		               new Product(arguments.get(0), arguments.get(1).differentiate()));
	}
	public double evaluate(double value){
		return arguments.get(0).evaluate(value) * arguments.get(1).evaluate(value);
	}
	public static Product quotient(Expr numerator, Expr denominator){
		return new Product(numerator, Power.unaryMultInv(denominator));
	}
	public static Product unaryNegation(Expr expression){
		return new Product(expression, Expr.MINUS_ONE);
	}
	public String toString(){
		String str = "";
		Iterator<Expr> itr = arguments.iterator();
		str += "("+ itr.next().toString();
		while (itr.hasNext()){
			str += " * "+itr.next().toString();
		}
		return str+")";
	}
}
