package org.lvcp.eepperly.expr;

import java.util.*;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Product implements Expr {
	protected List<Expr> arguments;

	public List<Expr> getArguments() {
		return Collections.unmodifiableList(arguments);
	}

	public Product(Expr...factor){
		arguments = Arrays.asList(factor);
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
	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		Iterator<Expr> itr = arguments.iterator();
		List<Expr> subTerms = new ArrayList<>();
		while (itr.hasNext()){
			subTerms.add(itr.next().substitute(subMap));
		}
		return new Product(subTerms);
	}
}
