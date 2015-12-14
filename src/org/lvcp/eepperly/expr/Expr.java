package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.simplify.AbstractSimplifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/11/15.
 */
public abstract class Expr {
	protected List<Expr> arguments;
	private AbstractSimplifier simplifier;
	public final static Expr ZERO = new NumConstant(new ArrayList<Expr>(),0.0);
	public final static Expr ONE = new NumConstant(new ArrayList<Expr>(),1.0);
	public final static Expr MINUS_ONE = new NumConstant(new ArrayList<Expr>(),-1.0);
	public abstract Expr differentiate();

	public List<Expr> getArguments() {
		return arguments;
	}

	public abstract double evaluate(double value);

	public Expr(List<Expr> arguments) {
		this.arguments = arguments;
		this.simplifier = AbstractSimplifier.TRIVIAL_SIMPLIFIER;
	}

	public Expr(List<Expr> arguments, AbstractSimplifier simplifier) {
		this.arguments = arguments;
		this.simplifier = simplifier;
	}

	public double findZero(double guess){
		Expr iterTerm = Product.quotient(this,this.differentiate());
		while (Math.abs(evaluate(guess))>1e-8){
			guess -= iterTerm.evaluate(guess);
		}
		return guess;
	}

	public Expr simplify(){
		return simplifier.simplify(this);
	}
}
