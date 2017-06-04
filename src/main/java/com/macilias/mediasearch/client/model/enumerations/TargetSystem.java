package com.macilias.mediasearch.client.model.enumerations;

public enum TargetSystem {

	FB("Face Book", Persistence.FILE);

	private final String name;
	private final Persistence persistence;


	TargetSystem(final String name, final Persistence persistence) {
		this.name = name;
		this.persistence = persistence;
	}


	public Persistence getPersistence() {
		return persistence;
	}


	@Override
	public String toString() {
		return name;
	}
}
