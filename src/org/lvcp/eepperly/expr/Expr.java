package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.simplify.AbstractSimplifier;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Dimitriye Danilovic
 * @since 1/22/16
 */
public interface Expr extends Cloneable {
	Expr ZERO = new NumConstant(0.0);
	Expr ONE = new NumConstant(1.0);
	Expr MINUS_ONE = new NumConstant(-1.0);
	Expr TWO = new NumConstant(2.0);

	Expr differentiate();

	default List<Expr> getArguments() {
		return Collections.EMPTY_LIST;
	}

	double evaluate(double value);

	Expr substitute(Map<Variable, Expr> map);

	default double optimize(double guess){
		return differentiate().findZero(guess);
	}

	default double findZero(double guess){
		Expr iterTerm = Product.quotient(this,this.differentiate());
		while (Math.abs(evaluate(guess))>1e-8){
			guess -= iterTerm.evaluate(guess);
		}
		return guess;
	}

	default Expr simplify(AbstractSimplifier simplifier){
		return simplifier.simplify(this);
	}

	default double defIntegral(double a, double b, double dx){
		double integral = this.evaluate(a) + this.evaluate(b);
		boolean odd = true;
		for (double x = a;x<b;x+=dx){
			integral += (odd ? 4 : 2)*this.evaluate(x);
			odd = !odd;
		}
		return integral*dx/3;
	}
}
