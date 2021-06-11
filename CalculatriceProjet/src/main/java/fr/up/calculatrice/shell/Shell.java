package fr.up.calculatrice.shell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fr.up.calculatrice.expressions.Classique;
import fr.up.calculatrice.expressions.Expressions;
import fr.up.calculatrice.server.ClientShell;
import fr.up.calculatrice.server.ServerShell;
import fr.up.calculatrice.server.request.GetVariablesRequest;
import fr.up.calculatrice.server.request.SendMessageRequest;
import fr.up.calculatrice.server.request.SendVariablesRequest;
import fr.up.calculatrice.shell.command.AllCommands;
import fr.up.calculatrice.shell.command.SimpleCommand;
import fr.up.calculatrice.shell.command.types.CommandsSystem;

/**
 * Class Shell to read the terminal
 *
 * @see Runnable
 *
 */
public class Shell implements Runnable {

	/**
	 * Attribut boolean if the shell is running
	 */
	private boolean running = true;

	/**
	 * Attribut to stock all commands
	 */
	private final AllCommands allCommands;

	/**
	 * Tag for commands
	 */
	private final String commandsTag = "=";

	/**
	 * Attribut to read the user inputs
	 */
	private final Scanner userInput = new Scanner(System.in);

	/**
	 * Attribut who calculate an expression
	 */
	private Expressions exp = new Classique();

	/**
	 * Attribut to receive user
	 */
	private final ServerShell serverShell;

	/**
	 * Attribut to know if we are server
	 */
	private boolean weAreServer = false;

	/**
	 * Attribut to connect to server=
	 */
	private final ClientShell clientShell;

	/**
	 * Attribut to know if we are client
	 */
	private boolean weAreClient = false;

	/**
	 * Shell constructor who setup the default commands
	 *
	 * @see AllCommands
	 * @see CommandsSystem
	 */
	public Shell() {
		serverShell = new ServerShell(this);
		clientShell = new ClientShell(this);
		this.allCommands = new AllCommands();
		this.allCommands.addCommands(new CommandsSystem(this));
	}

	/**
	 * Read the inputs on shell while is running
	 *
	 * @see Runnable
	 */
	@Override
	public void run() {
		while (running) {
			System.out.print("> ");
			String input = userInput.nextLine().trim();
			if (input.startsWith(commandsTag)) {
				input = input.replaceFirst(commandsTag, "");
				allCommands.execute(input);
			} else {
				try {
					System.out.println(exp.evaluate(input));
					if (exp.estAffectation()) {
						if (weAreServer) {
							serverShell.sendToAllTCP(new SendVariablesRequest(exp.getVariables()));
						} else if (weAreClient) {
							clientShell.sendTCP(new SendVariablesRequest(exp.getVariables()));
						}
					}
				} catch (Exception e) {
					if (e.getMessage() != null)
						System.out.println("Error : " + e.getMessage());
				}
			}
		}
		userInput.close();
		System.out.println("Shell stopped.");
		System.exit(0);
	}

	/**
	 * Check if we are client
	 * 
	 * @return if we are client
	 */
	public final boolean isWeAreClient() {
		return weAreClient;
	}

	/**
	 * Get server shell
	 * 
	 * @return server shell
	 */
	public final ServerShell getServerShell() {
		return serverShell;
	}

	/**
	 * Get expression
	 * 
	 * @return expression
	 */
	public final Expressions getExp() {
		return exp;
	}

	/**
	 * Check if we are client
	 * 
	 * @return if we are client
	 */
	public final boolean isWeAreServer() {
		return weAreServer;
	}

	/**
	 * Stop the shell
	 */
	public final void kill() {
		running = false;
		closeServer();
	}

	/**
	 * Get commands from this shell
	 *
	 * @return All commands
	 */
	public final ArrayList<SimpleCommand> getAllCommands() {
		return allCommands.getCommands();
	}

	/**
	 * Set the Expression
	 */
	public final void setExpression(Expressions e) {
		this.exp = e;
	}

	/**
	 * Open server
	 */
	public final void openServer() {
		if (weAreClient) {
			System.out.println("You can't open server beacause you are already connected to a server.");
		} else {
			try {
				serverShell.open();
				weAreServer = true;
			} catch (IOException e) {
				System.out.println("Can't open server");
			}
		}
	}

	/**
	 * Close server
	 */
	public final void closeServer() {
		serverShell.stop();
		weAreServer = false;
	}

	/**
	 * Connect to server
	 *
	 * @param ip Ip of server to connect
	 */
	public final void connect(String ip) {
		if (weAreServer) {
			System.out.println("You can't connect to a server beacause your server is open");
		} else {
			try {
				clientShell.connect(ip);
				weAreClient = true;
				clientShell.sendTCP(new GetVariablesRequest());
			} catch (IOException e) {
				System.out.println("Adress: " + ip + " unkwon");
			}

		}
	}

	/**
	 * Disconnect from server
	 */
	public final void disconnect() {
		clientShell.close();
		weAreClient = false;
	}

	/**
	 * Send message to server or to client
	 * 
	 * @param message Message to send
	 */
	public final void sendMessage(String message) {
		if (weAreServer) {
			serverShell.sendToAllTCP(new SendMessageRequest(message));
		} else if (weAreClient) {
			clientShell.sendTCP(new SendMessageRequest(message));
		} else {
			System.out.println(
					"You can't send message because your server is not open and you are not connected to other server");
		}

	}
}
