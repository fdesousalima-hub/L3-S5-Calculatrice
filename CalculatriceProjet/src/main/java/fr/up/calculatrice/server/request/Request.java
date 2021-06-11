package fr.up.calculatrice.server.request;

import com.esotericsoftware.kryonet.Connection;

import fr.up.calculatrice.shell.Shell;

/**
 * Request for server and client (Warning if you want to send attributs you need
 * constructor with no args)
 * 
 * @author fabio
 *
 */
public interface Request {

	/**
	 * Execute the request
	 * 
	 * @param shell      Shell used
	 * @param connection Who send the request
	 */
	public abstract void execute(Shell shell, Connection connection);

}
