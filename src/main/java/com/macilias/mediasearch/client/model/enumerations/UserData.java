package com.macilias.mediasearch.client.model.enumerations;

import java.awt.Dimension;
import java.awt.Point;

import com.macilias.mediasearch.client.model.MediaSearch;
import org.imgscalr.Scalr;

public enum UserData {

	SEARCHES_A("mediaSearch.favourites.availableSearches", MediaSearch.class, true),
	SEARCHES_V("mediaSearch.favourites.visibleSearches", MediaSearch.class, true),
	COMPONENTS("mediaSearch.productivity.excludedComponents", String.class, true),
	FIRST("mediaSearch.productivity.first", String.class, false),
	LAST("mediaSearch.productivity.last", String.class, false),
	POSITION_DIALOG("mediaSearch.productivity.galleryPosition", Boolean.class, false),
	ACTIVE("mediaSearch.productivity.active", Boolean.class, false),
	INACTIVE("mediaSearch.productivity.inactive", Boolean.class, false),
	WINDOWLOC("mediaSearch.productivity.windowLocation", Point.class, false),
	WINDOWSIZE("mediaSearch.productivity.windowSize", Dimension.class, false),
	AUTOSAVE("mediaSearch.productivity.autosave", Boolean.class, false),
	MEMORY("mediaSearch.performance.maxMemoryInMB", Integer.class, false),
	RESOLUTION("mediaSearch.performance.maxPictureResolution", Resolution.class, false),
	METHOD("mediaSearch.performance.scalingMethod", Scalr.Method.class, false),
	MODE("mediaSearch.performance.scalingMode", Scalr.Mode.class, false);

	private final String key;
	private final Class mainClass;
	private final boolean isCollection;


	UserData(final String key, final Class mainClass, final boolean isCollection) {
		this.key = key;
		this.mainClass = mainClass;
		this.isCollection = isCollection;
	}


	public String getKey() {
		return key;
	}


	public Class getMainClass() {
		return mainClass;
	}


	public boolean isCollection() {
		return isCollection;
	}


	public static UserData getUserDataForKey(final String key) {
		for (UserData userData : values()) {
			if (key.equals(userData.key)) {
				return userData;
			}
		}
		return null;
	}

}
