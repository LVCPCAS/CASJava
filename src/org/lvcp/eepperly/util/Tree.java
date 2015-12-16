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
	public Tree(T data){
		this.data = data;
		this.parent = null;
		this.children = null;
	}
	public void addChild(T child){
		if (children == null){
			children = new ArrayList<>();
		}
		children.add(new Tree(child));
	}
	public void addChild(Tree<T> childTree){
		if (children == null){
			children = new ArrayList<>();
		}
		children.add(childTree);
	}
}
