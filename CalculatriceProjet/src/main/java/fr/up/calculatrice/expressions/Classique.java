package fr.up.calculatrice.expressions;

import java.util.Map;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

/**
 * Class Classique which evaluates an expression in traditional algebraic syntax
 *
 * @see Expressions
 *
 * @author pierre
 */
public final class Classique extends Expressions {

	/**
	 * Calculates the result of the operation passed in parameter
	 *
	 * @param s The string operation to evaluate
	 *
	 * @return The result of the String operation
	 * @throws IllegalArgumentException Syntax error
	 * @see Expressions
	 */
	@Override
	protected final Integer calculate(String s) throws IllegalArgumentException {
		String[] affectation = s.split("=");
		if (affectation.length > 2)
			throw new IllegalArgumentException("Invalide expression '" + s + "'");
		s = affectation.length == 2 ? affectation[1] : affectation[0];
		Expression e = buildExpression(s);
		Integer res = (int) e.evaluate();
		if (affectation.length == 2) {
			affectation[0] = affectation[0].trim();
			addVariable(affectation[0], res);
		}
		return res;
	}

	/**
	 * Build an Expression object based on the current object and in the parameter
	 *
	 * @param s The String
	 * 
	 * @return The Expression object
	 * 
	 * @see Expression
	 */
	private final Expression buildExpression(String s) {
		ExpressionBuilder expb = new ExpressionBuilder(s);
		addVariableExpressionBuilder(expb);
		addFunctionExpressionBuilder(expb);
		Expression e = expb.build();
		addVariableExpression(e);
		return e;
	}

	/**
	 * Add all the current variables to an ExpressionBuilder
	 *
	 * @param exp The Expression which received all the variable
	 *
	 * @see Expressions
	 * @see ExpressionBuilder
	 */
	private final void addVariableExpressionBuilder(ExpressionBuilder exp) {
		for (Map.Entry<String, Integer> mapentry : getVariables().entrySet()) {
			exp.variable(mapentry.getKey());
		}
	}

	/**
	 * Add all the current variables to an Expression
	 *
	 * @param exp The Expression which received all the variable
	 *
	 * @see Expressions
	 * @see Expression
	 */
	private final void addVariableExpression(Expression exp) {
		for (Map.Entry<String, Integer> mapentry : getVariables().entrySet()) {
			exp.setVariable(mapentry.getKey(), mapentry.getValue());
		}
	}

	/**
	 * Add all the current function to an ExpressionBuilder
	 *
	 * @param exp The ExpressionBuilder which received all the function
	 *
	 * @see Expressions
	 * @see ExpressionBuilder
	 */
	private final void addFunctionExpressionBuilder(ExpressionBuilder exp) {
		for (Map.Entry<String, Fonction> mapentry : getFunctions().entrySet()) {
			exp.function(new Function(mapentry.getKey(), mapentry.getValue().getArrite()) {
				@Override
				public double apply(double... args) {
					return mapentry.getValue().apply(convert(args));
				}
			});
		}
	}

	/**
	 * Convert an array of double to an Integer[] array
	 *
	 * @param args The double array
	 *
	 * @return The result is the conversion of the parameter
	 * 
	 */
	private final static Integer[] convert(double... args) {
		Integer[] ret = new Integer[args.length];
		int x = 0;
		for (double d : args) {
			ret[x++] = (int) d;
		}
		return ret;
	}

}
