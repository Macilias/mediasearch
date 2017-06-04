package com.macilias.mediasearch.extensions.handler;

import com.macilias.mediasearch.client.MediaSearchClient;
import com.macilias.mediasearch.client.model.Connection;
import com.macilias.mediasearch.client.model.MediaSearchHit;
import org.apache.log4j.Logger;

public class AbstractOpenInClientHandler {

	private static final Logger LOG = Logger.getLogger(AbstractOpenInClientHandler.class);

	protected void openInClient(final MediaSearchHit mediaSearchHit) {

		Connection connection = MediaSearchClient.getInstance().getClientConnection();

		LOG.warn("not implemented yet");
//		FSConnection fsConnection = (FSConnection) connection;
//		SpecialistsBroker context = fsConnection.getContext();
//		StoreAgent storeAgent = context.requestSpecialist(StoreAgent.TYPE);
//		MediaStoreRoot mediaStore = (MediaStoreRoot) storeAgent.getStore(Store.Type.MEDIASTORE);
//		ClientUtil.show(mediaStore.getMediaByUid(mediaSearchHit.getUid())).openInBackground(false).waitForLoad(false).execute();
	}
}
