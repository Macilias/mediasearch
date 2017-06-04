package com.macilias.mediasearch.client.model.enumerations;

import static com.macilias.mediasearch.client.model.enumerations.TargetSystem.FB;

import com.macilias.mediasearch.client.model.Connection;
import com.macilias.mediasearch.client.model.facebook.FBConnection;
import org.apache.log4j.Logger;

public enum Environment {

	DEV_CON(FB, "DEV", "www.google.de", "technical-user", "technical", 443, true),
	STAGE_CON(FB, "STAGE", "www.google.de", "test-user", "test", 443, true);

	private static final Logger LOG = Logger.getLogger(Environment.class);

	private String name;
	private Connection connection;


	Environment(final TargetSystem targetSystem, final String name, final String serverName, final String user, final String password, final int port, final boolean standAlone) {
		this.name = name;
		switch (targetSystem) {
			default:
			case FB:
				connection = new FBConnection(targetSystem, serverName, user, password, port, standAlone);
				break;
		}
	}


	public String getName() {
		return name;
	}


	public Connection getConnection() {
		return connection;
	}


	@Override
	public String toString() {
		return name;
	}

}
