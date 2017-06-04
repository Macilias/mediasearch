package com.macilias.mediasearch.client.model.interfaces;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import com.macilias.mediasearch.client.model.Connection;
import com.macilias.mediasearch.client.model.MediaSearch;
import com.macilias.mediasearch.client.model.enumerations.Resolution;
import org.imgscalr.Scalr;

import com.macilias.mediasearch.client.model.MediaSearchHit;

public interface SearchManager {

	void useConnection(Connection connection);


	void updateSearchResultCount(String count);


	void updatePreviewPanel(MediaSearchHit mediaSearchHit, int selectedModelIndex);


	void setVisibleFavouriteSearches(List<MediaSearch> favouriteSearches);


	void setAvailableFavouriteSearches(List<MediaSearch> favouriteSearches);


	void useMaximalSpaceInMegaByte(int maximalSpaceInMegaByte);


	void usePictureResolution(Resolution resolution);


	void useScalingMethod(Scalr.Method method);


	void useScalingMode(Scalr.Mode mode);


	void setPositionDialogPreferred(boolean isPreferred);


	void setActiveSearchPreferred(boolean isPreferred);


	void setInactiveSearchPreferred(boolean isPreferred);


	void setWindowSavingPreferred(boolean isPreferred);


	void setAutoSavePreferred(boolean isPreferred);


	boolean isPositionDialogPreferred();


	boolean isActiveSearchPreferred();


	boolean isInactiveSearchPreferred();


	boolean isWindowSavingPreferred();


	boolean isAutoSavePreferred();


	List<MediaSearch> getVisibleFavouriteSearches();


	List<MediaSearch> getAvailableFavouriteSearches();


	int getMaximalSpaceInMegaByte();


	Point getWindowLocation();


	Dimension getWindowSize();


	void reactToOutOfMemoryProblem(boolean whileFetching);


	void reactToReconnection();


	void reactToHandlerContextChanged();


	void lockConnection();
}
