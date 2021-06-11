package fr.up.calculatrice.server.request;

import com.esotericsoftware.kryonet.Connection;

import fr.up.calculatrice.shell.Shell;

/**
 * Request to send message from server or client
 * 
 * @see Request
 * @author fabio
 *
 */
public final class SendMessageRequest implements Request {

	/**
	 * Message to send
	 * 
	 */
	private String message;

	/**
	 * Constuctor empty (to send request) without don't work
	 * 
	 */
	public SendMessageRequest() {
		this.message = "";
	}

	/**
	 * Constuctor to add message
	 * 
	 */
	public SendMessageRequest(String message) {
		this.message = message;
	}

	/**
	 * Execute the request
	 * 
	 * @param shell      Shell used
	 * @param connection Who send the request
	 */
	@Override
	public void execute(Shell shell, Connection connection) {
		if (shell.isWeAreServer()) {
			this.message = "\n[" + connection.getRemoteAddressUDP() + "] " + message;
			shell.getServerShell().sendToAllExceptTCP(connection.getID(), this);
		}
		System.out.print(message + "\n>");
	}

}
