package org.lvcp.eepperly.expr;

import java.util.List;
import java.util.Map;

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
	public Expr differentiate(){
		return Expr.ONE;
	}

	@Override
	public double evaluate(double value){ return value; }

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
}
