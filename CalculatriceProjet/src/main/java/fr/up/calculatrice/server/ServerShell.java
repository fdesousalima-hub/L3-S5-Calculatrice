package fr.up.calculatrice.server;

import java.io.IOException;
import java.util.HashMap;

import com.esotericsoftware.kryonet.Server;

import fr.up.calculatrice.server.listener.ServerListener;
import fr.up.calculatrice.server.request.GetVariablesRequest;
import fr.up.calculatrice.server.request.Request;
import fr.up.calculatrice.server.request.SendMessageRequest;
import fr.up.calculatrice.server.request.SendVariablesRequest;
import fr.up.calculatrice.server.utils.ServerUtils;
import fr.up.calculatrice.shell.Shell;

/**
 * Class use to receive a other users
 * 
 * @see ServerUtils
 * 
 * @author fabio
 *
 */
public final class ServerShell extends Server implements ServerUtils {

	/**
	 * ServerShell constructor to add listener ans all request accepeted
	 * 
	 * @see ServerListener
	 * @see Request
	 */
	public ServerShell(Shell shell) {
		addListener(new ServerListener(shell));
		registerAllRequests(SendMessageRequest.class, GetVariablesRequest.class, SendVariablesRequest.class);
		getKryo().register(HashMap.class);
	}

	/**
	 * Open the shell for other users
	 * @throws IOException  Can't open server
	 */
	public final void open() throws IOException {
		bind(25560, 25560);
		start();
	}

}
