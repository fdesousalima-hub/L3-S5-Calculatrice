package fr.up.calculatrice.server.utils;

import com.esotericsoftware.kryonet.EndPoint;

/**
 * Class to use methods on server and client
 * 
 * @author fabio
 *
 */
public interface ServerUtils extends EndPoint {

	/**
	 * Register requests
	 * 
	 * @param allRequest Array of requests
	 *
	 */
	public default void registerAllRequests(Class<?>... allRequest) {
		for (Class<?> requestType : allRequest) {
			registerRequest(requestType);
		}
	}

	/**
	 * Register request
	 * 
	 * @param requestType Request
	 *
	 */
	public default void registerRequest(Class<?> requestType) {
		getKryo().register(requestType);
	}

}
