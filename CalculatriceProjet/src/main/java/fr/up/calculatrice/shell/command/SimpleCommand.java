package fr.up.calculatrice.shell.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import fr.up.calculatrice.shell.command.types.CommandsType;

/**
 * Class who represent a Command
 * 
 * @author fabio
 *
 */
public final class SimpleCommand {
	/**
	 * Represent name of command$
	 */
	private final String name;
	/**
	 * Represent name of description
	 */
	private final String description;
	/**
	 * Represent method of command
	 * 
	 * @see Method
	 */
	private final Method method;
	/**
	 * Represent type of command
	 * 
	 * @see CommandsType
	 */
	private final CommandsType commandsType;

	/**
	 * SimpleCommand constructor
	 * 
	 * @param name
	 * @param description
	 * @param commandsType
	 * @param method
	 * 
	 * @see Method
	 * @see CommandsType
	 */
	public SimpleCommand(String name, String description, CommandsType commandsType, Method method) {
		super();
		this.name = name;
		this.description = description;
		this.commandsType = commandsType;
		this.method = method;
	}

	/**
	 * Get name of command
	 * 
	 * @return name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Get description of command
	 * 
	 * @return description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * Get Method of command
	 * 
	 * @return method
	 * @see Method
	 */
	public final Method getMethod() {
		return method;
	}

	/**
	 * Get CommandType of command
	 * 
	 * @see CommandsType
	 * @return CommandType
	 */
	public final CommandsType getCommandsType() {
		return commandsType;
	}

	/**
	 * Annotation for command
	 */
	@Target(value = ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public abstract @interface CommandForm {
		/**
		 * Set name of command
		 * 
		 * @return name
		 */
		public abstract String name();

		/**
		 * Set description of command
		 * 
		 * @return description
		 */
		public abstract String description() default "Without description.";
	}
}
