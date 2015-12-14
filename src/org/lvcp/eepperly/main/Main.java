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
		listContainingX.add(X); //define a simple list containing the variable x

		List<Expr> myList = new ArrayList<>();
		myList.add(Expr.MINUS_ONE);
		myList.add(X);
		myList.add(new Arcosine(listContainingX));
		Expr myExpr = new Product(myList);

		System.out.println("EXPRESSION: "+myExpr);
		System.out.println("EXPRESSION SIMPLIFIED: "+simplifier.simplify(myExpr));
		System.out.println("EXPRESSION DIFFERENTIATED: "+myExpr.differentiate());
		System.out.println("EXPRESSION DIFFERENTIATED THEN SIMPLIFIED: "+simplifier.simplify(myExpr.differentiate()));
		System.out.println("ZEROS: "+myExpr.findZero(1.0));
	}
}
