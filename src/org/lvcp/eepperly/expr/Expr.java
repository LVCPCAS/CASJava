package org.lvcp.eepperly.expr;

import com.sun.org.apache.xpath.internal.operations.Mult;
import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;
import org.lvcp.eepperly.simplify.AbstractSimplifier;

import java.util.*;

/**
 * @author Dimitriye Danilovic
 * @since 1/22/16
 */
public interface Expr extends Cloneable {
	NumConstant ZERO = new NumConstant(0.0);
	NumConstant ONE = new NumConstant(1.0);
	NumConstant MINUS_ONE = new NumConstant(-1.0);
	NumConstant TWO = new NumConstant(2.0);

	Expr differentiate(Variable withRespectTo) throws MultivariableException;

	static Map<Variable, Double> makeVarToDoubleMap(Variable var, Double x){
		Map<Variable, Double> myMap = new HashMap<>();
		myMap.put(var,x);
		return myMap;
	}

	static Variable getOneElementInSet(Set<Variable> set){
		return set.iterator().next();
	}

	default List<Expr> getArguments() {
		return Collections.EMPTY_LIST;
	}
	default Set<Variable> getVariables(){
		if (0==getArguments().size()){
			return Collections.EMPTY_SET;
		} else{
			Iterator<Expr> itr = getArguments().iterator();
			Set<Variable> variables = new HashSet<>();
			while (itr.hasNext()){
				variables.addAll(itr.next().getVariables());
			}
			return variables;
		}
	}

	double evaluate(Map<Variable, Double> map) throws VariableNoValueException;

	Expr substitute(Map<Variable, Expr> map);

	default double optimize(double guess) throws MultivariableException, VariableNoValueException{
		if (1== getVariables().size()){
			return differentiate(getOneElementInSet(getVariables())).findZero(guess);
		} else{
			throw new MultivariableException("Cannot optimize can expression with"+getVariables().size()+" arguments");
		}
	}

	default double findZero(double guess) throws MultivariableException, VariableNoValueException {
		if (1 == getVariables().size()) {
			Variable var = getOneElementInSet(getVariables());
			Expr iterTerm = Product.quotient(this, this.differentiate(var));
			while (Math.abs(evaluate(makeVarToDoubleMap(var, guess))) > 1e-8) {
				guess -= iterTerm.evaluate(makeVarToDoubleMap(var, guess));
			}
			return guess;
		} else{
			throw new MultivariableException("Cannot solve an expression with "+getVariables().size()+" arguments");
		}
	}

	default boolean hasVars(){
		return getVariables().size()!=0;
	}

	default Expr simplify(AbstractSimplifier simplifier){
		return simplifier.simplify(this);
	}

	default double defIntegral(double a, double b, double dx) throws MultivariableException, VariableNoValueException{
		if (1 == getVariables().size()) {
			Variable varX = getOneElementInSet(getVariables());
			double integral = this.evaluate(makeVarToDoubleMap(varX, a)) + this.evaluate(makeVarToDoubleMap(varX, b));
			boolean odd = true;
			for (double x = a; x < b; x += dx) {
				integral += (odd ? 4 : 2) * this.evaluate(makeVarToDoubleMap(varX, x));
				odd = !odd;
			}
			return integral * dx / 3;
		} else{
			throw new MultivariableException("Cannot integrate an expression with "+getVariables().size()+" arguments");
		}
	}
}
