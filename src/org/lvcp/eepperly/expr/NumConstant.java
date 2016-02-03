package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.*;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class NumConstant implements Expr {
	private double value;

	public NumConstant(double value){
		this.value = value;
	}

	public Expr differentiate(Variable withRespectTo) throws MultivariableException {
		return Expr.ZERO;
	}
	public String toString(){
		return (""+value);
	}

	public double getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NumConstant))
			return false;
		if (obj == this)
			return true;
		return ((NumConstant) obj).getValue() == value;

	}

	public static NumConstant product (List<NumConstant> numConstants){
		double prod = 1;
		Iterator<NumConstant> itr = numConstants.iterator();
		if (numConstants.size()==0) return (NumConstant) Expr.ONE;
		while (itr.hasNext()){
			prod *= itr.next().getValue();
		}
		return (new NumConstant(prod));
	}

	public static NumConstant sum (List<NumConstant> numConstants){
		double sum = 0;
		Iterator<NumConstant> itr = numConstants.iterator();
		if (numConstants.size()==0) return (NumConstant) Expr.ZERO;
		while (itr.hasNext()){
			sum += itr.next().getValue();
		}
		return (new NumConstant(sum));
	}
	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return this;
	}

	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {return value;}

	@Override
	public Set<Variable> getVariables(){
		return Collections.EMPTY_SET;
	}
}
