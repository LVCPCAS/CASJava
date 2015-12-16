package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Sum extends BinOp {

	public Expr differentiate(){
		List returnTerms = new ArrayList<>();
		Iterator<Expr> itr = arguments.iterator();
		while (itr.hasNext()) {
			returnTerms.add(itr.next().differentiate());
		}
		return (new Sum(returnTerms));
	}

	public Sum(List<Expr> arguments) {
		super(arguments);
	}

	public double evaluate(double value){
		double sum = 0;
		Iterator<Expr> itr = arguments.iterator();
		while (itr.hasNext()){
			sum += itr.next().evaluate(value);
		}
		return sum;
	}

	public String toString(){
		String str = "";
		Iterator<Expr> itr = arguments.iterator();
		str += "("+ itr.next().toString();
		while (itr.hasNext()){
			str += " + "+itr.next().toString();
		}
		return str+")";
	}
}
