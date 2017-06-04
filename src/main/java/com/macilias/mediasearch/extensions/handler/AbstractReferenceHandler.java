package com.macilias.mediasearch.extensions.handler;

import com.macilias.mediasearch.client.model.MediaSearchHit;
import org.apache.log4j.Logger;

public abstract class AbstractReferenceHandler {

	private static final Logger LOG = Logger.getLogger(AbstractReferenceHandler.class);

	/**
	 * Will set the FS_REFERENCE to the first MediaSearchHit in the given list.
	 */
	protected void setReference(final MediaSearchHit hit) {

		LOG.warn("not implemented yet");

//		Connection connection = MediaSearchClient.getInstance().getClientConnection();
//		FSConnection con = (FSConnection) connection;
//		Map<String, Object> params = con.getParams();
//		if (hit == null || !params.containsKey(FSConstants.PARAM_FIELD)) {
//			return;
//		}
//		@SuppressWarnings("unchecked")
//		final FormField<TargetReference> referenceField = (FormField<TargetReference>) params.get(FSConstants.PARAM_FIELD);
//		StoreAgent storeAgent = con.getContext().requestSpecialist(StoreAgent.TYPE);
//		MediaStoreRoot mediaStore = (MediaStoreRoot) storeAgent.getStore(Store.Type.MEDIASTORE);
//		Media medium = mediaStore.getMediaByUid(hit.getUid());
//		TargetReference targetReference = TargetReference.TargetReferences.newInstance(con.getLanguage(), medium, null);
//		referenceField.set(targetReference);
	}
}
