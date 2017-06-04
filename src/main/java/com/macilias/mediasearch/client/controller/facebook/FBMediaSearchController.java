package com.macilias.mediasearch.client.controller.facebook;

import com.macilias.mediasearch.client.controller.MediaSearchController;
import com.macilias.mediasearch.client.model.Connection;
import com.macilias.mediasearch.client.model.MediaSearch;
import com.macilias.mediasearch.client.model.interfaces.SearchManager;
import com.macilias.mediasearch.client.model.interfaces.SearchCaller;
import org.apache.log4j.Logger;

/**
 * Some Description
 *
 * @author macilias@gmail.com
 */
public class FBMediaSearchController extends MediaSearchController {

    private static final Logger LOG = Logger.getLogger(FBMediaSearchController.class);

    @Override
    public void performSearch(SearchCaller caller, SearchManager manager, Connection connection, MediaSearch mediaSearch) {
        LOG.warn("performSearch(): not implemented yet");
    }

    @Override
    public void stopSearching() {
        LOG.warn("stopSearching(): not implemented yet");
    }

    @Override
    public boolean isSearchInProgress() {
        LOG.warn("isSearchInProgress(): not implemented yet");
        return false;
    }
}
