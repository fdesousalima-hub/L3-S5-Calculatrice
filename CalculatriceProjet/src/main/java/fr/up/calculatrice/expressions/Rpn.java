package fr.up.calculatrice.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Class Rpn which evaluates an expression in rpn format
 *
 * @see Expressions
 * @see Operator
 *
 * @author pierre
 */
public final class Rpn extends Expressions {

	/**
	 * The stack that stores the values ​​of the rpn
	 *
	 * @see Stack
	 */
	private final static Stack<Integer> stack = new Stack<Integer>();

	/**
	 * Rpn constructor, resets the stack
	 *
	 * @see Expressions
	 * @see Stack
	 */
	public Rpn() {
		Operator.build(this);
		addFunction("pile", Fonction.fonctionBuilder(1, (arg) -> pile(arg[0])));
	}

	/**
	 * Fonction which return the element at an index of the Stack
	 *
	 * @param i Stack Index
	 *
	 * @return The result at the index of the stack
	 */
	private final Integer pile(Integer i) {
		ArrayList<Integer> list = new ArrayList<Integer>(stack);
		return list.get(i >= 0 ? i : list.size() + i);
	}

	/**
	 * Calculates the result of the operation passed in parameter
	 *
	 * @param s The string operation to evaluate
	 *
	 * @return The result of the String operation
	 *
	 * @see Expressions
	 */
	protected final Integer calculate(String s) throws Exception {
		List<String> rpn = parse(s);
		List<Integer> list = new ArrayList<Integer>();
		for (String exp : rpn) {
			if (affectation(exp))
				addVariable(exp.substring(1), stack.pop());
			else if (estOperateur(exp)) {
				int arrite = getArrite(exp);
				for (int k = 0; k < arrite; k++) {
					if (stack.empty()) {
						for (int i = 0; i < list.size(); i++) {
							stack.push(list.get(i));
						}
						throw new Exception("empty stack");
					}
					list.add(0, stack.pop());
				}
				stack.push(calculate(exp, list));
				list.clear();
			} else
				try {
					stack.push(Integer.valueOf(exp));
				} catch (Exception e) {
					if (getVariable(exp) != null)
						stack.push(getVariable(exp));
					else
						throw new Exception("Unknown function or variable '" + exp + "' in expression '" + s + "'");
				}
		}
		return stack.peek();
	}

	/**
	 * Verify if a String design a variable name for an affectation
	 *
	 * @param s The possible variable
	 *
	 * @return Indiquate if the String design an affectation
	 */
	private final boolean affectation(String s) {
		if (s != null && s.length() > 1 && s.charAt(0) == '!') {
			return true;
		}
		return false;
	}

	/**
	 * Decompose a String to use it more easily by a rpn
	 *
	 * @param s The String to decompose
	 *
	 * @return The list of the decomposition
	 *
	 * @see List
	 */
	private final List<String> parse(String s) {
		List<String> list = new ArrayList<String>();
		String nb = "";
		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c >= '0' && c <= '9')
				nb += c;
			else if (c == ' ') {
				if (nb != "")
					list.add(nb);
				nb = "";
			} else if (c == '(') {
				if (nb != "")
					list.add(nb);
				nb = "";
			} else if (c == ')') {
				if (nb != "")
					list.add(nb);
				nb = "";
			} else {
				nb += c;
			}
		}
		if (nb != "")
			list.add(nb);
		return list;
	}
}
