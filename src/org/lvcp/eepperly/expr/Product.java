package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Product implements Expr {
	protected List<Expr> arguments;

	public List<Expr> getArguments() {
		return Collections.unmodifiableList(arguments);
	}

	public Product(Expr factor1, Expr factor2){
		arguments = new ArrayList<>();
		arguments.add(factor1);
		arguments.add(factor2);
	}

	public Product(List<Expr> args){
		arguments = args;
	}

	public Expr differentiate(){
		List<Expr> sumTerms = new ArrayList<>();
		List<Expr> prodTerms;
		for (int i=0;i<arguments.size();i++){
			prodTerms = new ArrayList<>(arguments);
			prodTerms.remove(arguments.get(i));
			prodTerms.add(arguments.get(i).differentiate());
			sumTerms.add(new Product(prodTerms));
		}
		return new Sum(sumTerms);
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
