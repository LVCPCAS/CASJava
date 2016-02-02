package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.*;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Sum implements Expr {

	protected List<Expr> arguments;

	@Override
	public List<Expr> getArguments() {
		return Collections.unmodifiableList(arguments);
	}

	public Sum(Expr...summands){
		arguments = Arrays.asList(summands);
	}

	public Sum(List<Expr> summands){
		arguments = summands;
	}

	@Override
	public Expr differentiate(Variable withRespectTo) throws MultivariableException{
		List<Expr> diffTerms = new ArrayList<>();
		for (int i=0;i<arguments.size();i++){
			diffTerms.add(arguments.get(i).differentiate(withRespectTo));
		}
		return new Sum(diffTerms);
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
	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		Iterator<Expr> itr = arguments.iterator();
		List<Expr> subTerms = new ArrayList<>();
		while (itr.hasNext()){
			subTerms.add(itr.next().substitute(subMap));
		}
		return new Sum(subTerms);
	}
	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {
		Iterator<Expr> itr = getArguments().iterator();
		double sum = 0;
		while (itr.hasNext()){
			sum += itr.next().evaluate(evalMap);
		}
		return sum;
	}
}
