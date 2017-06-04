package com.macilias.mediasearch.extensions.handler;

import com.macilias.mediasearch.client.model.MediaSearchHit;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Some Description
 *
 * @author macilias@gmail.com
 */
public abstract class AbstractGalleryHandler {

    private static final Logger LOG = Logger.getLogger(AbstractGalleryHandler.class);

    protected void addToGallery(final List<MediaSearchHit> mediaSearchHits, int targetPosition) {

        LOG.warn(String.format("addToGallery(): not implemented yet - %d elements adding to position %d", mediaSearchHits.size(), targetPosition));

    }

}
