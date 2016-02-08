package org.lvcp.eepperly.simplify;

import org.lvcp.eepperly.exception.ExprTypeException;
import org.lvcp.eepperly.expr.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class DefaultSimplifier extends AbstractSimplifier {
	@Override
	public Expr simplify(Expr expression) throws ExprTypeException { //function takes an expression argument and outputs a simplified version of the expression
		if (expression instanceof UnOp){ //simplifies the argument, then returns a new expression
			Expr simplified = ((UnOp) expression).getArg().simplify(this);
			if (expression instanceof Sine){
				return new Sine(simplified);
			} else if (expression instanceof  Cosine){
				return new Cosine(simplified);
			} else if (expression instanceof Arccosine){
				return new Arccosine(simplified);
			} else if (expression instanceof Arcsine){
				return new Arcsine(simplified);
			} else if (expression instanceof Arctangent){
				return new Arctangent(simplified);
			} else if (expression instanceof NaturalLog){
				return new NaturalLog(simplified);
			} else if (expression instanceof Exponential){
				return new Exponential(simplified);
			} else{
				throw new ExprTypeException("UnOp expression not recognized!");
			}
		} else if (expression instanceof BinOp){ //simplifies arguments 1 and 2, then returns a new expression
			Expr simplified1 = ((BinOp) expression).getArg1().simplify(this);
			Expr simplified2 = ((BinOp) expression).getArg2().simplify(this);
			if (expression instanceof GeneralLog){
				return new GeneralLog(simplified1, simplified2);
			} else if (expression instanceof  Power){
				return new Power(simplified1, simplified2);
			} else{
				throw new ExprTypeException("BinOp expression not recognized!");
			}
		} else if (expression instanceof Sum) {
			List<Expr> simplifiedArgs = new ArrayList<>();
			List<NumConstant> numConstants = new ArrayList<>(); //list of all numeric constants that are arguments
			Iterator<Expr> itr = expression.getArguments().iterator();
			Expr addTerm;
			while (itr.hasNext()){
				addTerm = itr.next().simplify(this); //simplify each argument
				if (addTerm instanceof NumConstant){ //if argument is numeric constant, add to list of numeric constants
					numConstants.add((NumConstant) addTerm);
				} else { //if not numeric constant, add simplied argument to list
					simplifiedArgs.add(addTerm);
				}
			}
			NumConstant numConstantsCombined = addNumConstant(numConstants); //add togethor all numeric constants
			if (!numConstantsCombined.equals(Expr.ZERO)) { //if numeric constants add to nonzero number, add to list of args
				simplifiedArgs.add(numConstantsCombined);
			}
			if (simplifiedArgs.size()==1){
				return simplifiedArgs.get(0);
			} else if (simplifiedArgs.size() == 0){
				return Expr.ZERO;
			}
			return new Sum(simplifiedArgs);
		} else if (expression instanceof Product) {
			List<Expr> simplifiedArgs = new ArrayList<>();
			List<NumConstant> numConstants = new ArrayList<>(); //list of all numeric constants that are arguments
			Iterator<Expr> itr = expression.getArguments().iterator();
			Expr prodTerm;
			while (itr.hasNext()){
				prodTerm = itr.next().simplify(this);
				if (prodTerm instanceof NumConstant){
					numConstants.add((NumConstant) prodTerm);
				} else{
					simplifiedArgs.add(prodTerm);
				}
			}
			NumConstant numConstantsCombined = multiplyNumConstant(numConstants); //multiply togethor all numeric constants
			if (numConstantsCombined.equals(Expr.ZERO)) { //if product by 0, return 0
				return Expr.ZERO;
			} else if (!numConstantsCombined.equals(Expr.ONE)) { //if numeric constants multoply to nonone number, add to list of args
				simplifiedArgs.add(numConstantsCombined);
			}
			if (simplifiedArgs.size()==1){
				return simplifiedArgs.get(0);
			} else if (simplifiedArgs.size() == 0){
				return Expr.ONE;
			}
			return new Product(simplifiedArgs);
		} else if (expression instanceof NumConstant || expression instanceof Variable) {
			return expression;
		} else{
			throw new ExprTypeException("Expression not recognized!");
		}
	}
	private NumConstant addNumConstant(List<NumConstant> numConstants){
		if (numConstants.size()>0) {
			double value = 0;
			Iterator<NumConstant> itr = numConstants.iterator();
			while (itr.hasNext()) {
				value += itr.next().getValue();
			}
			return new NumConstant(value);
		} else{
			return Expr.ZERO;
		}
	}
	private NumConstant multiplyNumConstant(List<NumConstant> numConstants){
		if (numConstants.size()>0) {
			double value = 1;
			Iterator<NumConstant> itr = numConstants.iterator();
			while (itr.hasNext()) {
				value *= itr.next().getValue();
			}
			return new NumConstant(value);
		} else{
			return Expr.ONE;
		}
	}
}

