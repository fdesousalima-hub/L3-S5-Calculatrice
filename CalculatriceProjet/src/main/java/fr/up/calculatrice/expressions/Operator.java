package fr.up.calculatrice.expressions;

/**
 * Add operators "+*-/" to expression
 * 
 * @author pierre
 *
 */
public class Operator {

	/**
	 * Add operators "+*-/" to expression
	 * 
	 * @param exp expression
	 */
	public static void build(Expressions exp) {
		exp.addFunction("+", Fonction.fonctionBuilder(2, (arg) -> arg[0] + arg[1]));
		exp.addFunction("-", Fonction.fonctionBuilder(2, (arg) -> arg[0] - arg[1]));
		exp.addFunction("*", Fonction.fonctionBuilder(2, (arg) -> arg[0] * arg[1]));
		exp.addFunction("/", Fonction.fonctionBuilder(2, (arg) -> arg[0] / arg[1]));
		exp.addFunction("%", Fonction.fonctionBuilder(2, (arg) -> arg[0] % arg[1]));
		exp.addFunction("max", Fonction.fonctionBuilder(2, (arg) -> arg[0] < arg[1] ? arg[1] : arg[0]));
		exp.addFunction("min", Fonction.fonctionBuilder(2, (arg) -> arg[0] > arg[1] ? arg[1] : arg[0]));
	}
}
