package org.lvcp.eepperly.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eepperly16 on 12/14/15.
 */
public class Tree<T> {
	private T data;
	private Tree<T> parent;
	private List<Tree<T>> children;
}
