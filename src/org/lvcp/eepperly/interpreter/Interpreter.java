package org.lvcp.eepperly.interpreter;

import org.lvcp.eepperly.util.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Interpreter {
	private static final String[] BIN_OPS_ARRAY = {"+","*","/","-","^"};
	private static final String[] UN_OPS_ARRAY = {"sin","cos","tan","ln","exp"};
	private static List<String> binOps = new ArrayList<String>(Arrays.asList(BIN_OPS_ARRAY));
	private static List<String> unOps = new ArrayList<String>(Arrays.asList(UN_OPS_ARRAY));

	private String text;


}
