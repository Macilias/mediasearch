package com.macilias.mediasearch.client.controller;

import com.macilias.mediasearch.client.model.Connection;
import com.macilias.mediasearch.client.model.MediaSearch;
import com.macilias.mediasearch.client.model.interfaces.SearchManager;
import com.macilias.mediasearch.client.model.interfaces.SearchCaller;

public abstract class MediaSearchController {

	/**
	 * This method is responsible for performing a media search
	 *
	 * @param caller      the SearchCaller which receive bunches of search results as mediaSearchHits
	 * @param connection  this is the primary target system specific component for handling media search requests
	 * @param mediaSearch the mediaSearch Object contains all relevant information regarding the search parameters
	 */
	public abstract void performSearch(SearchCaller caller, SearchManager manager, Connection connection, MediaSearch mediaSearch);


	public abstract void stopSearching();

	/**
	 * returns true if (and only if) the thread is present and running
	 */
	public abstract boolean isSearchInProgress();
}
