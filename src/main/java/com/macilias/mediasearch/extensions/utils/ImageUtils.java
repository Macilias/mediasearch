package com.macilias.mediasearch.extensions.utils;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/*
 * This ImageUtils provides methods for getting and transforming a picture into a byte[]
 */
public class ImageUtils {

	private static final Logger LOG = Logger.getLogger(ImageUtils.class);

	public static byte[] image2byteArray(final String path) throws IOException {
//		return image2byteArray(new ClassPathResource(path).getInputStream());
		LOG.warn("not implemented yet");
		return new byte[0];
	}


	public static byte[] image2byteArray(final URL url) throws IOException {
		return image2byteArray(url.openStream());
	}


//	public static byte[] image2byteArrayFromFSMediaStore(final String uid, final SpecialistsBroker context) throws IOException {
////		Media placeholder = (Media) context.requireSpecialist(StoreAgent.TYPE).getStore(Store.Type.MEDIASTORE).getStoreElement(uid, IDProvider.UidType.MEDIASTORE_LEAF);
////		return image2byteArray(placeholder.getPicture(context.requireSpecialist(LanguageAgent.TYPE).getMasterLanguage()).getInputStream(context.requireSpecialist(ResolutionAgent.TYPE).getOriginalResolution()));
//		LOG.warn("not implemented yet");
//		return new byte[0];
//	}


	public static byte[] image2byteArray(final InputStream inputStream) throws IOException {
		return IOUtils.toByteArray(inputStream);
	}

}