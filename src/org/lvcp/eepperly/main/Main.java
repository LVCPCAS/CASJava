package org.lvcp.eepperly.main;

import org.lvcp.eepperly.exception.ExprTypeException;
import org.lvcp.eepperly.expr.Expr;
import org.lvcp.eepperly.interpreter.LispParser;
import org.lvcp.eepperly.interpreter.LispTokenizer;
import org.lvcp.eepperly.interpreter.ExprCompiler;
import org.lvcp.eepperly.simplify.AbstractSimplifier;
import org.lvcp.eepperly.simplify.DefaultSimplifier;

import java.util.HashMap;
import java.util.Map;
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
		Map<String, Expr> memory = new HashMap<>();
		while (true) {
			String line = scan.nextLine();
			String[] splitLine = line.toLowerCase().split("\\s+");
			double guess = 3 * rand.nextDouble() + 0.05;
			switch (splitLine[0]){
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
						if(splitLine.length==1){
							System.out.println(expr.findZero(guess));
						}else if(splitLine.length==2){
							if(isDouble(splitLine[1])){
								double n = Double.parseDouble(splitLine[1]);
								System.out.println(expr.findZero(n));
							} else {
								expr = memory.get(splitLine[1]);
								System.out.println(expr.findZero(guess));
							}
						} else if (splitLine.length==3){
							if(isDouble(splitLine[1])){
								memory.get(splitLine[2]);
								double n = Double.parseDouble(splitLine[1]);
								System.out.println(expr.findZero(n));
							} else {
								expr = memory.get(splitLine[1]);
								double n = Double.parseDouble(splitLine[2]);
								System.out.println(expr.findZero(n));
							}
						}

					} catch (Exception ex){
						ex.printStackTrace();

					}
					break;
				case "differentiate":
					try{
						if(splitLine.length==1){
							expr = expr.differentiate(Expr.getOneElementInSet(expr.getVariables())).simplify(simp);
						}else if (splitLine.length==2){
							if (isInteger(splitLine[1])){
								int n = Integer.parseInt(splitLine[1], 10);
								while(n>0){
									expr = memory.get("curr");
									expr = expr.differentiate(Expr.getOneElementInSet(expr.getVariables())).simplify(simp);
									memory.put("curr", expr);
									n--;
								}
							} else{
								expr = memory.get(splitLine[1]);
								expr = expr.differentiate(Expr.getOneElementInSet(expr.getVariables())).simplify(simp);
								memory.put("curr",expr);
							}
						} else if(splitLine.length==3){
							if(isInteger(splitLine[1])){
								int n = Integer.parseInt(splitLine[1], 10);
								expr = memory.get(splitLine[2]);
								while(n>0){
									expr = expr.differentiate(Expr.getOneElementInSet(expr.getVariables())).simplify(simp);
									n--;
								}
								memory.put("curr", expr);
							}else{
								expr = memory.get(splitLine[1]);
								int n = Integer.parseInt(splitLine[2], 10);
								while(n>0){
									expr = expr.differentiate(Expr.getOneElementInSet(expr.getVariables())).simplify(simp);

									n--;
								}
								memory.put("curr", expr);
							}

						}
						System.out.println(expr);
					} catch (Exception ex){
						ex.printStackTrace();
					}
					break;
				case "memory":
					for (String key: memory.keySet()){
						System.out.println(key + ": "+memory.get(key));
					}
					break;
				default:
					try {
						if (line.contains("=")){
							String[] splitBySpace = line.split("\\s*=\\s*");
							String identifier = splitBySpace[0];
							expr = ExprCompiler.INSTANCE.compile(LispParser.INSTANCE.parse(LispTokenizer.INSTANCE.tokenize(splitBySpace[1])));
							memory.put(identifier, expr);
							memory.put("curr", expr);
							System.out.println(identifier + " = " + expr);
						} else {
							expr = ExprCompiler.INSTANCE.compile(LispParser.INSTANCE.parse(LispTokenizer.INSTANCE.tokenize(line)));
							memory.put("curr", expr);
							System.out.println(expr);
						}
					} catch (Exception ex){
						ex.printStackTrace();
					}
					break;
			}

		}
	}

	public static boolean isInteger(String s) {
		return isInteger(s,10);
	}
	public static boolean isDouble(String s){
		boolean b;
		try{
			Double.parseDouble(s);
			b = true;
		}catch (Exception ex){
			b = false;
		}
		if(b==false) return false;
		else return true;
	}

	public static boolean isInteger(String s, int radix) {
		if(s.isEmpty()) return false;
		for(int i = 0; i < s.length(); i++) {
			if(i == 0 && s.charAt(i) == '-') {
				if(s.length() == 1) return false;
				else continue;
			}
			if(Character.digit(s.charAt(i),radix) < 0) return false;
		}
		return true;
	}

}
