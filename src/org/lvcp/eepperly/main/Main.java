package org.lvcp.eepperly.main;

import org.lvcp.eepperly.exception.ExprTypeException;
import org.lvcp.eepperly.expr.Expr;
import org.lvcp.eepperly.interpreter.LispParser;
import org.lvcp.eepperly.interpreter.LispTokenizer;
import org.lvcp.eepperly.interpreter.ExprCompiler;
import org.lvcp.eepperly.simplify.AbstractSimplifier;
import org.lvcp.eepperly.simplify.DefaultSimplifier;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Main {
	public static void main(String[] args) {
		Expr expr = Expr.ZERO;
		Random rand = new Random();
		AbstractSimplifier simp = new DefaultSimplifier();
		Scanner scan = new Scanner(System.in);
		while (true) {
			String line = scan.nextLine();
			String strippedLine = line.replaceAll("\\s+","").toLowerCase();
			switch (strippedLine){
				case "simplify":
					try {
						expr = expr.simplify(simp);
						System.out.println(expr);
					} catch (ExprTypeException ex){
						ex.printStackTrace();
					}
					break;
				case "zero":
					try {
						System.out.println(expr.findZero(3 * rand.nextDouble() + 0.05));
					} catch (Exception ex){
						ex.printStackTrace();
					}
					break;
				case "differentiate":
					try{
						expr = expr.differentiate(Expr.getOneElementInSet(expr.getVariables())).simplify(simp);
						System.out.println(expr);
					} catch (Exception ex){
						ex.printStackTrace();
					}
					break;
				default:
					try {
						expr = ExprCompiler.INSTANCE.compile(LispParser.INSTANCE.parse(LispTokenizer.INSTANCE.tokenize(line)));
						System.out.println(expr);
					} catch (Exception ex){
						ex.printStackTrace();
					}
					break;
			}

		}
	}
}
