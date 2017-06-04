package com.macilias.mediasearch.client.model.interfaces;

import java.util.List;

import com.macilias.mediasearch.client.model.MediaSearchHit;

public interface SearchCaller {

	void receiveSearchResultBunch(List<MediaSearchHit> mediaSearchHits);


	void receiveFinalSearchResultBunch(List<MediaSearchHit> mediaSearchHits);


	void stopSearching();


	void stopSearchingAndReactToTermination();


	void stopFetching();


	boolean isQueryInProgress();


	boolean isFetchingInProgress();

}
