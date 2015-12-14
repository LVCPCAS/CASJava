package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Product extends Expr {
	public Product(List<Expr> arguments) {
		super(arguments);
	}
	public Expr differentiate(){
		List<Expr> addTerms = new ArrayList<>();
		List<Expr> prodTerms = new ArrayList<>();
		for (int i=0;i<arguments.size();i++) {
			for (int j=0;j<arguments.size();j++) {
				if (i==j){
					prodTerms.add(arguments.get(j).differentiate());
				}
				else{
					prodTerms.add(arguments.get(j));
				}
			}
			addTerms.add(new Product(prodTerms));
			prodTerms = new ArrayList<>();
		}
		return (new Sum(addTerms));
	}
	public double evaluate(double value){
		double prod = 1;
		Iterator<Expr> itr = arguments.iterator();
		while (itr.hasNext()){
			prod *= itr.next().evaluate(value);
		}
		return prod;
	}
	public static Product quotient(Expr numerator, Expr denominator){
		List<Expr> prodTerms = new ArrayList<>();
		prodTerms.add(numerator);
		prodTerms.add(Exponential.unitaryMultInv(denominator));
		return (new Product(prodTerms));
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
