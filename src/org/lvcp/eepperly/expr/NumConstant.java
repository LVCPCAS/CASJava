package org.lvcp.eepperly.expr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class NumConstant extends Expr {
	private double value;

	public NumConstant(List<Expr> arguments) {
		super(arguments);
		value=0;
	}

	public NumConstant(List<Expr> arguments, double value) {
		super(arguments);
		this.value = value;
	}

	public NumConstant(double value){
		super(new ArrayList<>());
		this.value = value;
	}

	public Expr differentiate(){
		return Expr.ZERO;
	}
	public double evaluate(double inputValue){ return value; };
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
		return (new NumConstant(new ArrayList<>(),prod));
	}

	public static NumConstant sum (List<NumConstant> numConstants){
		double sum = 0;
		Iterator<NumConstant> itr = numConstants.iterator();
		if (numConstants.size()==0) return (NumConstant) Expr.ZERO;
		while (itr.hasNext()){
			sum += itr.next().getValue();
		}
		return (new NumConstant(new ArrayList<>(),sum));
	}
}
