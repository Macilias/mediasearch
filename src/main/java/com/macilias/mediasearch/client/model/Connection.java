package com.macilias.mediasearch.client.model;

import com.macilias.mediasearch.client.model.enumerations.TargetSystem;

public abstract class Connection {

	private final TargetSystem targetSystem;
	private final String serverName;
	private final String user;
	private final String password;
	private final int port;
	private final boolean standAlone;


	public Connection(final TargetSystem targetSystem, final String serverName, final String user, final String password, final int port, final boolean standAlone) {
		this.targetSystem = targetSystem;
		this.serverName = serverName;
		this.user = user;
		this.password = password;
		this.port = port;
		this.standAlone = standAlone;
	}


	public TargetSystem getTargetSystem() {
		return targetSystem;
	}


	public String getServerName() {
		return serverName;
	}


	public String getUser() {
		return user;
	}


	public String getPassword() {
		return password;
	}


	public int getPort() {
		return port;
	}


	public boolean isStandAlone() {
		return standAlone;
	}


	public abstract void openConnection();


	public abstract boolean isConnected();


	public abstract Object getConnection();


	public abstract void closeConnection();
}
