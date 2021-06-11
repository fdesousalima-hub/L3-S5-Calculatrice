package fr.up.calculatrice.expressions;

/**
 * Class which defines the operators having an edge greater than or equal to 0
 * and the function associated with them
 *
 *
 * @author pierre
 */
public final class Fonction {

	/**
	 * Functional interface which defines a function taking as parameter an
	 * indefinite number of parameters
	 */
	public static interface FunctionN_aire {
		public Integer apply(Integer... a);
	}

	/**
	 * Attribut Integer which defines the operator's arrest
	 */
	private final int arrite;

	/**
	 * Attribut which defines the function of the operator
	 *
	 * @see FunctionN_aire
	 */
	private final FunctionN_aire function;

	/**
	 * Function constructor
	 *
	 * @param a Number of arguments
	 * @param f Function
	 *
	 */
	private Fonction(int a, FunctionN_aire f) {
		this.arrite = a;
		this.function = f;
	}

	/**
	 * Builder for fonction
	 * 
	 * @param a Number of arguments
	 * @param f Function
	 *
	 * @return new Fonction
	 */
	public final static Fonction fonctionBuilder(int a, FunctionN_aire f) {
		return new Fonction(a, f);
	}

	/**
	 * Apply the function of Operation to the tab parameter corresponding to a
	 * certain operator
	 *
	 * @param tab Arguments
	 *
	 * @return Integer The result of the function applied
	 */
	public final Integer apply(Integer... tab) {
		return this.function.apply(tab);
	}

	/**
	 * Getteur for the arrite of the operator
	 *
	 * @return Integer The Integer arrite of an operator
	 */
	public final int getArrite() {
		return this.arrite;
	}
}
