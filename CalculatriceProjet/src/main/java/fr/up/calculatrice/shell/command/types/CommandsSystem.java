package fr.up.calculatrice.shell.command.types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import fr.up.calculatrice.expressions.Classique;
import fr.up.calculatrice.expressions.Rpn;
import fr.up.calculatrice.shell.Shell;
import fr.up.calculatrice.shell.command.SimpleCommand;

/**
 * Commands for a shell
 *
 * @see CommandsType
 * @author fabio
 *
 */
public final class CommandsSystem extends CommandsType {
	/**
	 * CommandsSystem constructor
	 *
	 * @param shell
	 */
	public CommandsSystem(Shell shell) {
		super(shell);
	}

	/**
	 * Command to stop shell
	 */
	@SimpleCommand.CommandForm(name = "stop", description = "Stop the shell.")
	private final void stop() {
		shell.kill();
	}

	/**
	 * Command to see manual
	 */
	@SimpleCommand.CommandForm(name = "help", description = "Shell manual")
	private final void help() {
		for (SimpleCommand command : shell.getAllCommands()) {
			System.out.println("Name: =" + command.getName());
			System.out.println("   " + command.getDescription());
		}
	}

	/**
	 * Command to switch type of expression to Reverse Polish notation (RPN)
	 */
	@SimpleCommand.CommandForm(name = "rpn", description = "Swith to Reverse Polish notation")
	private final void rpn() {
		shell.setExpression(new Rpn());
	}

	/**
	 * Command to switch type of expression to traditional algebraic syntax
	 */
	@SimpleCommand.CommandForm(name = "cla", description = "Swith to traditional algebraic syntax")
	private final void cla() {
		shell.setExpression(new Classique());
	}

	/**
	 * Command to get ip for server
	 */
	@SimpleCommand.CommandForm(name = "ip", description = "Get your ip")
	private final void ip() {
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();
			System.out.println(ip);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Command to open the server
	 */
	@SimpleCommand.CommandForm(name = "open", description = "Open server")
	private final void open() {
		shell.openServer();
	}

	/**
	 * Command to close the server
	 */
	@SimpleCommand.CommandForm(name = "close", description = "Close server")
	private final void close() {
		shell.closeServer();
	}

	/**
	 * Command to connect to other shell (Warning: You lose all variables)
	 *
	 * @param ip Ip of server to connect
	 */
	@SimpleCommand.CommandForm(name = "connect", description = "Connect to other shell \n (Warning: You lose all variables)")
	private final void connect(String ip) {
		shell.connect(ip);
	}

	/**
	 * Command to disconnect from shell
	 */
	@SimpleCommand.CommandForm(name = "disconnect", description = "Connect to other shell")
	private final void disconnect() {
		shell.disconnect();
	}

	/**
	 * Command to send message
	 * 
	 * @param message Message to send
	 */
	@SimpleCommand.CommandForm(name = "send", description = "Send message to othershells")
	private final void send(String message) {
		shell.sendMessage(message);
	}
}
