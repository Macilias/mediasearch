package com.macilias.mediasearch.client.controller.facebook;

import com.macilias.mediasearch.client.controller.PictureLoadController;
import com.macilias.mediasearch.client.model.interfaces.SearchManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * Some Description
 *
 * @author macilias@gmail.com
 */
public class FBPictureLoadController extends PictureLoadController {

    private static final Logger LOG = Logger.getLogger(FBPictureLoadController.class);

    @Override
    public void loadImages(SearchManager manager, JTable resultTable, Dimension dimension, JProgressBar progressBar, JProgressBar memoryProgressBar, int actualPosition) {
        LOG.warn("loadUserSettings(): not implemented yet");
    }

    @Override
    public void stopFetching() {
        LOG.warn("stopFetching(): not implemented yet");
    }

    @Override
    public boolean isFetchingInProgress() {
        LOG.warn("isFetchingInProgress(): not implemented yet");
        return false;
    }

    @Override
    public void setLocked(boolean locked) {
        LOG.warn("setLocked(): not implemented yet");
    }
}
