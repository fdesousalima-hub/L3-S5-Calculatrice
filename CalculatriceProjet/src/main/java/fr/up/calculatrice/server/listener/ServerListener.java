package fr.up.calculatrice.server.listener;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import fr.up.calculatrice.server.request.Request;
import fr.up.calculatrice.shell.Shell;

/**
 * Listener went server receive a request
 * 
 * @author fabio
 */
public final class ServerListener extends Listener {

	/**
	 * Shell for this listener
	 *
	 * @see ArrayList
	 */
	private final Shell shell;

	/**
	 * Constructor which initialize the attribut shell
	 *
	 * @param shell Shell
	 * @see Shell
	 */
	public ServerListener(Shell shell) {
		this.shell = shell;
	}

	/**
	 * Message when user is connected to server
	 * 
	 * @param connection Connection
	 */
	@Override
	public void connected(Connection connection) {
		super.connected(connection);
		System.out.print("\n" + connection.getRemoteAddressUDP() + " is connected! \n>");
	}

	/**
	 * Execute the request on server
	 * 
	 * 
	 * @param connection Connection
	 * @param object     Request
	 * @see Request
	 */
	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof Request) {
			((Request) object).execute(shell, connection);
		}
	}

	/**
	 * Message when user is disconnected from server
	 * 
	 * @param connection Connection
	 */
	@Override
	public void disconnected(Connection connection) {
		super.disconnected(connection);
		System.out.print("\n" + connection.getRemoteAddressUDP() + " is disconnected!\n>");
	}
}
