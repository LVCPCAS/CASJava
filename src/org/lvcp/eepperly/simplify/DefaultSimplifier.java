package org.lvcp.eepperly.simplify;

import org.lvcp.eepperly.exception.ExprTypeException;
import org.lvcp.eepperly.expr.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class DefaultSimplifier extends AbstractSimplifier {
	@Override
	public Expr simplify(Expr expression) throws ExprTypeException { //function takes an expression argument and outputs a simplified version of the expression
		//To-do
		//Distribute multiplication over addition
		//Simplify numeric expressions to num constants

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
			//Variables for simplifying
			List<Expr> simplifiedArgs = new ArrayList<>();
			List<NumConstant> numConstants = new ArrayList<>(); //list of all numeric constants that are arguments
			Iterator<Expr> itr = expression.getArguments().iterator();
			Expr addTerm;

			//Variables for flattening
			Iterator<Expr> subItr;
			Expr subAddTerm;

			while (itr.hasNext()){
				addTerm = itr.next().simplify(this); //simplify each argument
				if (addTerm instanceof NumConstant){ //if argument is numeric constant, add to list of numeric constants
					numConstants.add((NumConstant) addTerm);
				} else if (addTerm instanceof Sum){
					subItr = addTerm.getArguments().iterator();
					while (subItr.hasNext()){
						subAddTerm = subItr.next();
						if (subAddTerm instanceof NumConstant){
							numConstants.add((NumConstant) subAddTerm);
						} else{
							simplifiedArgs.add(subAddTerm);
						}
					}
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
			//Variables for simplifying
			List<Expr> simplifiedArgs = new ArrayList<>();
			List<NumConstant> numConstants = new ArrayList<>(); //list of all numeric constants that are arguments
			Iterator<Expr> itr = expression.getArguments().iterator();
			Expr prodTerm;

			//Variables for flattening
			Iterator<Expr> subItr;
			Expr subProdTerm;

			while (itr.hasNext()){
				prodTerm = itr.next().simplify(this);
				if (prodTerm instanceof NumConstant){
					numConstants.add((NumConstant) prodTerm);
				} else if (prodTerm instanceof Product){
					subItr = prodTerm.getArguments().iterator();
					while (subItr.hasNext()){
						subProdTerm = subItr.next();
						if (subProdTerm instanceof NumConstant){
							numConstants.add((NumConstant) subProdTerm);
						} else{
							simplifiedArgs.add(subProdTerm);
						}
					}
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

			//simplifiedArgs = combinePowers(simplifiedArgs);

			//Distribution
			List<Sum> sumTerms = new ArrayList<>();
			boolean isSum = false;
			Expr distTerm;

			Iterator<Expr> distItr = simplifiedArgs.iterator();
			while (distItr.hasNext()){
				distTerm = distItr.next();
				if (distTerm instanceof Sum){
					sumTerms.add((Sum) distTerm);
					isSum = true;
				} else{
					sumTerms.add(new Sum(distTerm));
				}
			}

			if (!isSum){
				return new Product(simplifiedArgs);
			} else{
				//for (Sum sum: sumTerms) {
					//System.out.println(sum);
				//}
				return multiplyPolynomials(sumTerms).simplify(this);
			}
		} else if (expression instanceof NumConstant || expression instanceof Variable) {
			return expression;
		} else{
			throw new ExprTypeException("Expression not recognized!");
		}
	}
	private List<Expr> combinePowers(List<Expr> exprs) throws ExprTypeException{
		Expr base;
		for (Expr expr: exprs){
			if (expr instanceof Power){
				base = ((Power) expr).getArg1();
			} else {
				base = expr;
			}
			List<Expr> exponents = new ArrayList<>();
			for (Expr expr2: exprs){
				System.out.println("Expr:   "+expr2);
				printList(exprs);
				if (expr2 instanceof Power){
					Power exprPower = (Power) expr2;
					if (hasBase(exprPower, base)){
						exponents.add(exprPower.getArg2());
						exprs.remove(expr2);
					}
				} else {
					if (base.equals(expr2)){
						exponents.add(Expr.ONE);
						exprs.remove(expr2);
					}
				}
			}
			if (1!=exponents.size()) {
				exprs.add(new Power(base, (new Sum(exponents)).simplify(this)));
				return combinePowers(exprs);
			}
		}
		return exprs;
	}

	private boolean hasBase(Power power, Expr base){
		return base.equals(power.getArg1());
	}
	/**
	 * Returns the product of polynomials
	 * @param polys an iterable of polynomials
	 * @return a Sum consisting of the multiplied out values
	 */
	private Sum multiplyPolynomials(Collection<Sum> polys) {
		return new Sum(polys.parallelStream().map(Sum::getArguments).reduce((A, B) -> {
			List<Expr> ret = new ArrayList<>();
			for (Expr a : A) {
				for (Expr b : B) {
					ret.add(new Product(a, b));
				}
			}
			return ret;
		}).orElse(Arrays.asList(Expr.ZERO)));
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
	private void printList(List list){
		for (Object o: list){
			System.out.println(o);
		}
	}
}

