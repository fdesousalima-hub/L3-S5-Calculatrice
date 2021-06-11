package fr.up.calculatrice.shell.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import fr.up.calculatrice.shell.command.types.CommandsType;

/**
 * Class who stock all commands for any shell
 * 
 * @author fabio
 *
 */
public final class AllCommands {

	/**
	 * Array to stock commands
	 * 
	 * @see SimpleCommand
	 */
	private final ArrayList<SimpleCommand> commands;

	/**
	 * AllCommands constructor empty
	 * 
	 * @see AllCommands
	 * @see CommandsSystem*
	 */
	public AllCommands() {
		commands = new ArrayList<>();
	}

	/**
	 * Get all commands
	 * 
	 * @return All commands
	 */
	public final ArrayList<SimpleCommand> getCommands() {
		return commands;
	}

	/**
	 * Find a command by name
	 * 
	 * @param commandsName
	 * @return A SimpleCommand
	 * @see SimpleCommand
	 */
	private final SimpleCommand getCommandByName(String commandsName) {
		for (SimpleCommand simpleCommand : commands) {
			if (simpleCommand.getName().equals(commandsName)) {
				return simpleCommand;
			}
		}
		return null;
	}

	/**
	 * Split a input to commands and arguments
	 * 
	 * @param input
	 * @return a SimpleCommand and Arguments
	 */
	private final Object[] splitCommandAndArgs(String input) {
		String[] inputSplit = input.split(" ");
		String[] arguments = new String[0];

		if (inputSplit.length - 1 > 0) {
			arguments = new String[1];
			arguments[0] = "";
		}
		for (int i = 1; i < inputSplit.length; i++) {
			arguments[0] += inputSplit[i];
			if (i + 1 < inputSplit.length) {
				arguments[0] += " ";
			}
		}
		SimpleCommand simpleCommand = getCommandByName(inputSplit[0]);
		return new Object[] { simpleCommand, arguments };
	}

	/**
	 * Add commands to all commands
	 * 
	 * @param commands
	 */
	public final void addCommands(CommandsType... commands) {
		for (CommandsType command : commands) {
			addCommand(command);
		}
	}

	/**
	 * Add command to allCommands
	 * 
	 * @param command
	 */
	private final void addCommand(CommandsType command) {
		for (Method method : command.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(SimpleCommand.CommandForm.class)) {
				SimpleCommand.CommandForm commandAnnotation = method.getAnnotation(SimpleCommand.CommandForm.class);
				method.setAccessible(true);
				SimpleCommand simpleCommand = new SimpleCommand(commandAnnotation.name(),
						commandAnnotation.description(), command, method);
				commands.add(simpleCommand);
			}
		}
	}

	/**
	 * Execute a command
	 * 
	 * @param input
	 */
	public final void execute(String input) {
		Object[] commandAndArgs = splitCommandAndArgs(input);
		if (commandAndArgs[0] == null) {
			System.out.println("Unknow Command");
		} else {
			try {
				SimpleCommand simpleCommand = ((SimpleCommand) commandAndArgs[0]);
				if (((Object[]) commandAndArgs[1]).length != simpleCommand.getMethod().getParameterCount()) {
					System.out.println("Invalid number of arguments");
				} else {
					simpleCommand.getMethod().invoke(simpleCommand.getCommandsType(), (Object[]) commandAndArgs[1]);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

}
