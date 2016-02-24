package org.lvcp.eepperly.interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Dimitriye Danilovic
 * @since 2/1/16
 */
public abstract class UnsafeTree {
	public abstract List<UnsafeTree> getChildren();
	public abstract Optional<String> getTokenIfLeaf();
	public abstract boolean isLeaf();

	private static class Internal extends UnsafeTree {
		private List<UnsafeTree> children;

		private Internal() {
			this.children = new ArrayList<>();
		}

		public List<UnsafeTree> getChildren() {
			return Collections.unmodifiableList(children);
		}

		public Optional<String> getTokenIfLeaf() {
			return Optional.empty();
		}

		public boolean isLeaf() {
			return false;
		}
	}

	private static class Leaf extends UnsafeTree {
		private String token;

		private Leaf(String token) {
			this.token = token;
		}

		public List<UnsafeTree> getChildren() {
			return Collections.emptyList();
		}

		public Optional<String> getTokenIfLeaf() {
			return Optional.of(token);
		}

		public boolean isLeaf() {
			return true;
		}
	}

	public static class UnsafeTreeBuilder {
		protected Internal tree;
		private UnsafeTreeBuilder parent;
		private boolean disabled = false;

		protected void verifyEnabled() {
			if (disabled) throw new IllegalStateException("Attempt to use built `UnsafeTreeBuilder`");
		}

		private UnsafeTreeBuilder() {
			this.tree = new Internal();
		}

		private UnsafeTreeBuilder(UnsafeTreeBuilder parent) {
			this();
			this.parent = parent;
		}

		public UnsafeTreeBuilder addLeaf(String token) {
			verifyEnabled();
			tree.children.add(UnsafeTree.of(token));
			return this;
		}

		public UnsafeTreeBuilder addInternal() {
			verifyEnabled();
			UnsafeTreeBuilder ret = new UnsafeTreeBuilder(this);
			tree.children.add(ret.tree);
			return ret;
		}

		public UnsafeTree build() {
			if (parent != null) throw new UnsupportedOperationException("Cannot build internal `UnsafeTreeBuilder`");
			verifyEnabled();
			disabled = true;
			return tree;
		}

		public UnsafeTreeBuilder pop() {
			if (parent == null) throw new UnsupportedOperationException("Cannot pop root `UnsafeTreeBuilder`");
			verifyEnabled();
			disabled = true;
			return parent;
		}
	}

	public static UnsafeTree of(String token) {
		return new Leaf(token);
	}

	public static UnsafeTreeBuilder builder() {
		return new UnsafeTreeBuilder();
	}
}
