package com.macilias.mediasearch.client.controller;

import java.awt.Dimension;

import javax.swing.JProgressBar;
import javax.swing.JTable;

import com.macilias.mediasearch.client.model.interfaces.SearchManager;

/**
 * This class is responsible for performing and managing the fetching of a pictures
 */
public abstract class PictureLoadController {

	/**
	 * This method is responsible for managing the amount of fetched pictures
	 *
	 * @param resultTable       the jTable containing the data model with MediaSearchHits to operate on and sort information.
	 * @param dimension         the desired dimension of the picture (if resolution provided - a thumbnail service can be used)
	 * @param progressBar       progressbar to visualize the load status
	 * @param memoryProgressBar progressbar to visualize the memory consumption status
	 * @param actualPosition    the actual selected row index
	 */
	public abstract void loadImages(SearchManager manager, JTable resultTable, Dimension dimension, JProgressBar progressBar, JProgressBar memoryProgressBar, int actualPosition);


	/**
	 * This method is responsible for stoping the fetching of pictures
	 */
	public abstract void stopFetching();


	public abstract boolean isFetchingInProgress();


	// The controller will be locked to prevent creating new fetchingThreads during a reconnection.
	// This is only necessary for fetching controller (not the search controller).
	// While the searching is a one time process with a beginning and a end the fetching may be triggered
	// any time a desired picture was removed because of memory capacity.
	public abstract void setLocked(boolean locked);

}
