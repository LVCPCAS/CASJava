package org.lvcp.eepperly.simplify;

import org.lvcp.eepperly.expr.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class DefaultSimplifier extends AbstractSimplifier {
	public Expr simplify(Expr expression){ //function takes an expression argument and outputs a simplified version of the expression
		List<Expr> args = expression.getArguments();
		for (int i=0;i<args.size();i++){ //simplify all of the individual arguments
			args.set(i, simplify(args.get(i)));
		}
		if (expression instanceof Product){ //combines numerical terms, flattens products
			List<NumConstant> numTerms = new ArrayList<>(); //list of numerical terms
			Expr temp;
			for (int i=0;i<args.size();i++){
				temp = args.get(i);
				if (temp instanceof NumConstant){
					numTerms.add((NumConstant) temp); //add numerical term to list of numerical terms
					args.remove(i);
				}
				else if (temp instanceof Product){
					args.addAll(temp.getArguments()); //flattens product
					args.remove(i);
				}
			}
			NumConstant constantTerm = NumConstant.product(numTerms); //multiplies all numerical terms
			if (Expr.ZERO.equals(constantTerm)) return Expr.ZERO;
			else if (!Expr.ONE.equals(constantTerm)) args.add(constantTerm);
			if (args.size()==1) return args.get(0);
			return (new Product(args)); //returns simplified product
		}
		else if (expression instanceof Sum){ //combines numerical terms, flattens sums
			List<NumConstant> numTerms = new ArrayList<>();
			Expr temp;
			for (int i=0;i<args.size();i++){
				temp = args.get(i);
				if (temp instanceof NumConstant) {
					numTerms.add((NumConstant) temp);
					args.remove(i);
				}
				else if (temp instanceof Sum){
					args.addAll(temp.getArguments());
					args.remove(i);
				}
			}
			NumConstant constantTerm = NumConstant.sum(numTerms);
			if (!Expr.ZERO.equals(constantTerm)) args.add(constantTerm);
			if (args.size()==1) return args.get(0);
			return (new Sum(args));
		}
		else{ //if not sum or product return self
			return expression;
		}
	}
}

