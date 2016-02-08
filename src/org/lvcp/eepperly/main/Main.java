package org.lvcp.eepperly.main;

import org.lvcp.eepperly.interpreter.LispParser;
import org.lvcp.eepperly.interpreter.LispTokenizer;
import org.lvcp.eepperly.interpreter.ExprCompiler;

import java.util.Scanner;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		System.out.println(ExprCompiler.INSTANCE.compile(LispParser.INSTANCE.parse(LispTokenizer.INSTANCE.tokenize(line))));
	}
}
