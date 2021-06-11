package fr.up.calculatrice.shell.command.types;

import fr.up.calculatrice.shell.Shell;

/**
 * Class who represent a type of command
 * 
 * @author fabio
 *
 */
public abstract class CommandsType {

	/**
	 * Represent the shell
	 * 
	 * @see Shell
	 */
	protected final Shell shell;

	/**
	 * CommandsType constructor
	 * 
	 * @param shell
	 */
	public CommandsType(Shell shell) {
		this.shell = shell;
	}

}
