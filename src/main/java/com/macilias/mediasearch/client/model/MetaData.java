package com.macilias.mediasearch.client.model;

public class MetaData {
	private final String name;
	private final String value;


	public MetaData(final String name, final String value) {
		this.name = name;
		this.value = value;
	}


	public String getValue() {
		return value;
	}


	public String getName() {
		return name;
	}

}
