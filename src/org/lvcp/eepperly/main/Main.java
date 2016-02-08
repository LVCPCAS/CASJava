package org.lvcp.eepperly.main;

import org.lvcp.eepperly.exception.MultivariableException;
import org.lvcp.eepperly.expr.*;
import org.lvcp.eepperly.simplify.DefaultSimplifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Main {
	public static void main(String[] args) {
		Variable x = new Variable("X");
		Expr testExpr = new GeneralLog(Expr.TWO, x);
		System.out.println(testExpr);
		try{
			System.out.println(testExpr.differentiate(x));
		} catch (MultivariableException ex){
			ex.printStackTrace();
		}
	}
}
