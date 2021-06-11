package fr.up.calculatrice.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class Expressions which evaluates an expression
 *
 * @author pierre
 */
public abstract class Expressions {

	/**
	 * The ArrayList that stores the history ​​of the values
	 *
	 * @see ArrayList
	 */
	private final ArrayList<Integer> history;

	/**
	 * The HashMap which define all the operator with there function
	 *
	 * @see HashMap
	 * @see Fonction
	 */
	private final HashMap<String, Fonction> functions;

	/**
	 * The HashMap which define all the variables
	 *
	 * @see HashMap
	 */
	private final HashMap<String, Integer> variables;

	/**
	 * The boolean which indicate if the last avaluation contains an evaluation
	 */
	private boolean affect;

	/**
	 * Constructor which initialize all the attribut an primary function
	 */
	protected Expressions() {
		this.history = new ArrayList<Integer>();
		this.functions = new HashMap<String, Fonction>();
		this.variables = new HashMap<String, Integer>();
		this.affect = false;
		addFunction("hist", Fonction.fonctionBuilder(1, (arg) -> hist(arg[0])));
	}

	/**
	 * Fonction which return the element at an index of the history
	 *
	 * @param i History Index
	 *
	 * @return The result at the index of the history
	 */
	private final int hist(int i) {
		return history.get(i >= 0 ? i : history.size() + i);
	}

	/**
	 * Fonction which evaluate and expression and add the result in history
	 *
	 * @param s The String to evaluate
	 *
	 * @return The result of the evaluation
	 */
	public final int evaluate(String s) throws Exception {
		this.affect = false;
		int res = calculate(s);
		history.add(res);
		return res;
	}

	/**
	 * Calculates the result of the operation passed in parameter
	 *
	 * @param s The string operation to evaluate
	 *
	 * @return The result of the String operation
	 */
	protected abstract Integer calculate(String s) throws Exception;

	/**
	 * Checks if the operator passes in parameter is available in operation
	 *
	 * @param operator The string operator
	 * @param f        The function associated with the operator
	 *
	 */
	public final void addFunction(String operator, Fonction f) {
		this.functions.put(operator, f);
	}

	/**
	 * Add a variable to the HashMap of variables
	 *
	 * @param var   The variable name
	 * @param value The variable value
	 */
	public final void addVariable(String var, int value) {
		this.affect = true;
		this.variables.put(var, value);
	}

	/**
	 * Get the value of the affectation
	 *
	 * @return the value of the affectation
	 */
	public final boolean estAffectation() {
		return this.affect;
	}

	/**
	 * Get the HashMap of all Function
	 *
	 * @return All the function
	 *
	 * @see HashMap
	 */
	public final HashMap<String, Fonction> getFunctions() {
		return new HashMap<String, Fonction>(this.functions);
	}

	/**
	 * Get the value of a variable
	 *
	 * @param var The variable name
	 *
	 * @return The value of the variable
	 */
	public final Integer getVariable(String var) {
		return Integer.valueOf(this.variables.get(var).intValue());
	}

	/**
	 * Get the HashMap of all Function
	 *
	 * @return All the variables
	 *
	 * @see HashMap
	 */
	public final HashMap<String, Integer> getVariables() {
		return new HashMap<String, Integer>(this.variables);
	}

	/**
	 * Returns the evaluation of an operator and a List of Integer
	 *
	 * @param operateur The string operator to use
	 * @param list      The list of Integer to use with the operator
	 *
	 * @return The evaluation
	 *
	 * @throws IllegalArgumentException If the operator's arrite is different from
	 *                                  the length of the list
	 */
	protected final Integer calculate(String operateur, List<Integer> list) throws IllegalArgumentException {
		Fonction f = functions.get(operateur);
		if (f.getArrite() != list.size())
			throw new IllegalArgumentException();
		Integer[] tab = new Integer[list.size()];
		return f.apply(list.toArray(tab));
	}

	/**
	 * checks if the operator passes in parameter is available in operation
	 *
	 * @param operateur The string operator to check
	 *
	 * @return If the operator passes in parameter is available in operation
	 */
	protected final boolean estOperateur(String operateur) {
		return functions.containsKey(operateur);
	}

	/**
	 * Getteur for the arrite of the operator
	 *
	 * @param operateur The string operator to check
	 *
	 * @return Integer The Integer arrite of an operator
	 */
	protected final Integer getArrite(String operateur) {
		return Integer.valueOf(functions.get(operateur).getArrite());
	}

	/**
	 * Clear the variables
	 */
	public final void resetVariables() {
		this.variables.clear();
	}
}
