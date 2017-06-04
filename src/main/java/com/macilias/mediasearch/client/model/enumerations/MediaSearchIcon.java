package com.macilias.mediasearch.client.model.enumerations;

public enum MediaSearchIcon {
	PLACEHOLDER_PIC("mediasearch_placeholder_pic", "jpg"),
	PLACEHOLDER_PREV("mediasearch_placeholder_prev", "jpg"),
	LOADING_ICON("mediasearch_loading", "gif"),
	BOOKMARK_ICON("mediasearch_bookmark", "png"),
	STAR_ICON("mediasearch_star", "png"),
	SEARCH_ICON("mediasearch_search", "png");

	private String name;
	private String suffix;
	private byte[] imageData = {0};


	MediaSearchIcon(final String name, final String suffix) {
		this.name = name;
		this.suffix = suffix;
	}


	public String getName() {
		return name;
	}


	public String getSuffix() {
		return suffix;
	}


	public byte[] getImageData() {
		return imageData;
	}


	public void setImageData(final byte[] imageData) {
		this.imageData = imageData;
	}
}
