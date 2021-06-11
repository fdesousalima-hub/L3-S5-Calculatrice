package fr.up.calculatrice.server.request;

import java.util.HashMap;

import com.esotericsoftware.kryonet.Connection;

import fr.up.calculatrice.shell.Shell;

/**
 * Request for server to get variables of server
 * 
 * @author fabio
 *
 */
public final class GetVariablesRequest implements Request {

	/**
	 * Execute the request
	 * 
	 * @param shell      Shell used
	 * @param connection Who send the request
	 */
	@Override
	public void execute(Shell shell, Connection connection) {
		connection.sendTCP(new SendVariablesRequest(new HashMap<String, Integer>(shell.getExp().getVariables())));
	}

}
