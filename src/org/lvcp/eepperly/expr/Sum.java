package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Sum implements Expr {

	protected List<Expr> arguments;

	@Override
	public List<Expr> getArguments() {
		return Collections.unmodifiableList(arguments);
	}

	public Sum(Expr addend, Expr augend){
		arguments = new ArrayList<>();
		arguments.add(addend);
		arguments.add(augend);
	}

	public Sum(List<Expr> summands){
		arguments = summands;
	}

	public Expr differentiate(){
		List<Expr> diffTerms = new ArrayList<>();
		for (int i=0;i<arguments.size();i++){
			diffTerms.add(arguments.get(i).differentiate());
		}
		return new Sum(diffTerms);
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
