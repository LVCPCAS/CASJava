package org.lvcp.eepperly.interpreter;

import org.lvcp.eepperly.expr.*;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * @author Dimitriye Danilovic
 * @since 2/8/16
 */
public enum ExprCompiler implements Compiler {
	INSTANCE;
	@Override
	public Expr compile(UnsafeTree unsafeTree) {
		if (unsafeTree.isLeaf()) {
			return unsafeTree.getTokenIfLeaf().map(token -> {
				try {
					return new NumConstant(Double.valueOf(token));
				} catch (NumberFormatException exception) {
					return new Variable(token);
				}
			}).get();
		}
		List<UnsafeTree> children = unsafeTree.getChildren();
		switch (children.get(0).getTokenIfLeaf().get()) {
			case "sum":
				return new Sum(compile(children.get(1)), compile(children.get(2)));
			case "prod":
				return new Product(compile(children.get(1)), compile(children.get(2)));
			case "pow":
				return new Power(compile(children.get(1)), compile(children.get(2)));
			case "exp":
				return new Exponential(compile(children.get(1)));
			case "sin":
				return new Sine(compile(children.get(1)));
			case "asin":
				return new Arcsine(compile(children.get(1)));
			case "cos":
				return new Cosine(compile(children.get(1)));
			case "acos":
				return new Arccosine(compile(children.get(1)));
			case "atan":
				return new Arctangent(compile(children.get(1)));
			case "ln":
				return new NaturalLog(compile(children.get(1)));
			case "log":
				return new GeneralLog(compile(children.get(1)), compile(children.get(2)));
			default:
				throw new InvalidParameterException("Invalid operation `"+children.get(0).getTokenIfLeaf().get()+"`");
		}
	}
}
