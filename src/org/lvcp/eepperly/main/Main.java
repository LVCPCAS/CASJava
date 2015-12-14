package org.lvcp.eepperly.main;

import org.lvcp.eepperly.expr.*;
import org.lvcp.eepperly.simplify.DefaultSimplifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Main {
	public static final Expr X = new Variable(new ArrayList<Expr>(),"X");
	public static void main(String[] args) {
		DefaultSimplifier simplifier = new DefaultSimplifier();
		List<Expr> listContainingX = new ArrayList<>();
		listContainingX.add(X);
		List<Expr> prodTerms = new ArrayList<>();
		prodTerms.add(X);
		prodTerms.add(new EToThePowerOf(listContainingX));
		Expr prodExpr = new Product(prodTerms);
		List<Expr> sumTerms = new ArrayList<>();
		sumTerms.add(prodExpr);
		sumTerms.add(Expr.MINUS_ONE);
		Expr myExpr = new Sum(sumTerms);
		System.out.println(myExpr);
		System.out.println(myExpr.differentiate());
		System.out.println(simplifier.simplify(myExpr.differentiate()));
		System.out.println(myExpr.findZero(1));
		//System.out.println(myExpr.differentiate().differentiate());
		/*Expr myExpr = new EToThePowerOf(listContainingX);
		System.out.println(myExpr);
		System.out.println(myExpr.differentiate());*/
	}
}
