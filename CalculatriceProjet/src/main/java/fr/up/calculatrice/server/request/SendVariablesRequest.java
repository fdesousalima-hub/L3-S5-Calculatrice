package fr.up.calculatrice.server.request;

import java.util.HashMap;
import java.util.Map;

import com.esotericsoftware.kryonet.Connection;

import fr.up.calculatrice.shell.Shell;

/**
 * Request for server or client to send variables
 * 
 * @author fabio
 *
 */
public final class SendVariablesRequest implements Request {

	/**
	 * Variable to send
	 */
	private final HashMap<String, Integer> variables;

	/**
	 * Constuctor empty (to send request) without don't work
	 * 
	 */
	public SendVariablesRequest() {
		variables = new HashMap<>();
	}

	/**
	 * Constuctor to add veriables
	 * 
	 */
	public SendVariablesRequest(HashMap<String, Integer> variables) {
		this.variables = variables;
	}

	/**
	 * Execute the request
	 * 
	 * @param shell      Shell used
	 * @param connection Who send the request
	 */
	@Override
	public void execute(Shell shell, Connection connection) {
		shell.getExp().resetVariables();
		for (Map.Entry<String, Integer> m : variables.entrySet()) {
			shell.getExp().addVariable(m.getKey(), m.getValue());
		}
		if (shell.isWeAreServer()) {
			shell.getServerShell().sendToAllExceptTCP(connection.getID(), this);
		}
	}

}
