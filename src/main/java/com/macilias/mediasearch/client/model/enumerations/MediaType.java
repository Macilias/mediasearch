package com.macilias.mediasearch.client.model.enumerations;

public enum MediaType {

	PICTURE("Bilder", "Picture"),
	FILE("Dateien", "File"),
	MEDIA("alle", "Media");

	private final String name;
	private final String query;


	MediaType(final String name, final String query) {
		this.name = name;
		this.query = query;
	}


	@Override
	public String toString() {
		return name;
	}


	public String getName() {
		return name;
	}


	public String getQuery() {
		return query;
	}

}
