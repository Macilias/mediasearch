package com.macilias.mediasearch.client;

import com.macilias.mediasearch.client.model.Connection;
import com.macilias.mediasearch.client.model.enumerations.MediaSearchIcon;
import com.macilias.mediasearch.client.model.facebook.FBConnection;
import com.macilias.mediasearch.client.view.MediaSearchGUI;
import com.macilias.mediasearch.extensions.utils.ImageUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public final class MediaSearchClient  {

	private static final Logger LOG = Logger.getLogger(MediaSearchClient.class);

	private Connection connection;

	// modern thread safe singleton
	private static MediaSearchClient instance = new MediaSearchClient();


	private MediaSearchClient() {
		setupLogging(Level.INFO);
		setupFBConnection(null);
		setPicturesAndIcons(false);
		if (!connectionEstablished()) {
			LOG.warn("connection could not be established at startup");
		}
	}


	public static MediaSearchClient getInstance() {
		return instance;
	}


	public static void main(final String[] args) {
		MediaSearchGUI.getInstance().open();
	}


	public void shutDown() {
		closeConnection();
		instance = null;
	}


	/*
	 * A connection to first spirit can be either established providing a context:SpecialistsBroker
	 * or providing necessary information in connection:FSConnection
	 */
	private boolean setupFBConnection(final Map<String, Object> params) {
		if (connection != null) {
			connection.closeConnection();
		}
		connection = new FBConnection(null, "", "", "", 1234, true);
		setPicturesAndIcons(false);
		return true;
	}

	private void setupLogging(Level level) {
		BasicConfigurator.configure();
		LOG.setLevel(level);
	}


	/*
	 * A connection to first spirit can be either established providing a context:SpecialistsBroker
	 * or providing necessary information for a Connector which are:
	 * String host,
	 * int port,
	 * String login,
	 * String password,
	 * String projectName
	 * this information must be already defined in connection:FSConnection
	 */
//	public boolean setupStandaloneFirstSpiritConnection(final FSConnection firstSpiritConnection) {
//
//		try {
//			Logging.logInfo(String.format("Initializing new standalone FS connection with SERVER_NAME = '%s', on PORT = '%s', with USER = '%s' and PROJECT_NAME = '%s'", firstSpiritConnection.getServerName(), firstSpiritConnection.getPort(), firstSpiritConnection.getUser(), firstSpiritConnection.getProjectName()), getClass());
//			assert firstSpiritConnection.getServerName() != null : "The server name has to be provided";
//			assert firstSpiritConnection.getUser() != null : "The user name has to be provided";
//			assert firstSpiritConnection.getPassword() != null : "The user password has to be provided";
//			assert firstSpiritConnection.getPort() > 0 : "The port has to be provided";
//			assert firstSpiritConnection.getProjectName() != null : "The project name has to be provided";
//			connection = new FSConnection(true, firstSpiritConnection.getServerName(), firstSpiritConnection.getUser(), firstSpiritConnection.getPassword(), firstSpiritConnection.getPort(), firstSpiritConnection.getProjectName());
//			connection.openConnection();
//			setPicturesAndIcons(false);
//			return connection.isConnected();
//		}
//		catch (Exception e) {
//			Logging.logError("Error establishing standalone connection", e, MediaSearchClient.class);
//			return false;
//		}
//	}


	public void closeConnection() {
		connection.closeConnection();
	}


//	public void reconnect(final Connection connection, final MethodCaller caller) {
//		closeConnection();
//		switch (connection.getTargetSystem()) {
//			default:
//			case FS:
//				reconnectToFirstSpirit((FSConnection) connection, caller);
//				break;
//		}
//	}


//	private void reconnectToFirstSpirit(final FSConnection mediaSearchConnection, final MethodCaller caller) {
//		caller.callBack(setupStandaloneFirstSpiritConnection(mediaSearchConnection));
//	}


	public Connection getClientConnection() {
		return connection;
	}


	public boolean connectionEstablished() {
//		try {
//			if (connection == null) {
//				FSConnection dev = (FSConnection) Environment.DEV_CON.getConnection();
//				return setupStandaloneFirstSpiritConnection(dev);
//			} else {
//				Logging.logDebug("The connection is connected = " + connection.isConnected(), getClass());
//				return connection.isConnected();
//			}
//		}
//		catch (Exception e) {
//			Logging.logError(String.format("Error while accessing connection to FirstSpirit."), e, getClass());
//		}

		LOG.warn("not implemented yet");

		return false;
	}


	public void setPicturesAndIcons(final boolean forceReload) {
		for (MediaSearchIcon mediaSearchIcon : MediaSearchIcon.values()) {
			if (forceReload || mediaSearchIcon.getImageData().length == 1) {
				setPictureOrIcon(mediaSearchIcon);
			}
		}
	}


	private void setPictureOrIcon(final MediaSearchIcon mediaSearchIcon) {
		if (connection != null && !connection.isStandAlone() && !loadPictureFromMediaStore(mediaSearchIcon)) {
			LOG.debug(String.format("Trying to fetch picture or icon '%s' from classpath because the repository fetch was unsuccessful.", mediaSearchIcon.getName()));
			loadPictureFromClassPath(mediaSearchIcon);
		} else {
			loadPictureFromClassPath(mediaSearchIcon);
		}
	}


	private void loadPictureFromClassPath(final MediaSearchIcon mediaSearchIcon) {
		try {
			byte[] imageData = ImageUtils.image2byteArray("images/" + mediaSearchIcon.getName() + "." + mediaSearchIcon.getSuffix());
			if (imageData != null) {
				mediaSearchIcon.setImageData(imageData);
			}
		}
		catch (IOException e) {
			LOG.debug(String.format("Error loading placeholder '%s' from classpath.", mediaSearchIcon.getName()), e);
		}
	}


	private boolean loadPictureFromMediaStore(final MediaSearchIcon mediaSearchIcon) {
//		try {
//			byte[] imageData = ImageUtils.image2byteArrayFromFSMediaStore(mediaSearchIcon.getName(), ((FSConnection) connection).getContext());
//			if (imageData != null) {
//				Logging.logDebug(String.format("Placeholder '%s' successfully loaded from media store.", mediaSearchIcon.getName()), getClass());
//				mediaSearchIcon.setImageData(imageData);
//				return true;
//			}
//		}
//		catch (IOException e) {
//			Logging.logError(String.format("Error loading placeholder '%s' from media store.", mediaSearchIcon.getName()), e, getClass());
//		}

		LOG.warn("not implemented yet");

		return false;
	}

}
