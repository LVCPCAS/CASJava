package org.lvcp.eepperly.expr;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.exception.VariableNoValueException;

import java.util.*;

/**
 * Created by eepperly16 on 12/11/15.
 */
public class Variable implements Expr {
	private String varName;
	public Variable() {
		varName = "X";
	}
	public Variable(String varName){
		this.varName = varName;
	}

	@Override
	public Expr differentiate(Variable withRespectTo) throws MultivariableException {
		if (this.equals(withRespectTo)){
			return Expr.ONE;
		} else{
			throw new MultivariableException(this+"cannot be differentiated with respect to"+withRespectTo);
		}
	}

	@Override
	public String toString(){
		return varName;
	}

	public String getVarName() {
		return varName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Variable variable = (Variable) o;

		return !(varName != null ? !varName.equals(variable.varName) : variable.varName != null);

	}

	@Override
	public int hashCode() {
		return varName != null ? varName.hashCode() : 0;
	}

	@Override
	public Expr substitute(Map<Variable, Expr> subMap){
		return subMap.getOrDefault(this, this);
	}
	@Override
	public double evaluate(Map<Variable, Double> evalMap) throws VariableNoValueException {
		if (evalMap.keySet().contains(this)){
			return evalMap.get(this);
		}
		else{
			throw new VariableNoValueException(this+" is not in the evaluation map");
		}
	}

	@Override
	public Set<Variable> getVariables(){
		return new HashSet<>(Arrays.asList(this));
	}
}
