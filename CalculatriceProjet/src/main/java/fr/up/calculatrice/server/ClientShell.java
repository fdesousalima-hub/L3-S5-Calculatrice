package fr.up.calculatrice.server;

import java.io.IOException;
import java.util.HashMap;

import com.esotericsoftware.kryonet.Client;

import fr.up.calculatrice.server.listener.ClientListener;
import fr.up.calculatrice.server.request.GetVariablesRequest;
import fr.up.calculatrice.server.request.Request;
import fr.up.calculatrice.server.request.SendMessageRequest;
import fr.up.calculatrice.server.request.SendVariablesRequest;
import fr.up.calculatrice.server.utils.ServerUtils;
import fr.up.calculatrice.shell.Shell;

/**
 * Class use to connect to a other user
 * 
 * @see ServerUtils
 * 
 * @author fabio
 *
 */
public final class ClientShell extends Client implements ServerUtils {

	/**
	 * ClientShell constructor to add listener ans all request accepeted
	 * 
	 * @see ClientListener
	 * @see Request
	 */
	public ClientShell(Shell shell) {
		addListener(new ClientListener(shell));
		registerAllRequests(SendMessageRequest.class, GetVariablesRequest.class, SendVariablesRequest.class,
				HashMap.class);
	}

	/**
	 * Connect to other user shell and get variable
	 * 
	 * @param ip Ip of other user
	 * @throws IOException Can't connect to server
	 */
	public final void connect(String ip) throws IOException {
		start();
		connect(5000, ip, 25560, 25560);
	}

}
