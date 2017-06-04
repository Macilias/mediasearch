package com.macilias.mediasearch.client.controller;

import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.macilias.mediasearch.client.MediaSearchClient;
import com.macilias.mediasearch.client.model.Connection;
import com.macilias.mediasearch.client.model.MediaSearch;
import com.macilias.mediasearch.client.model.enumerations.Persistence;
import com.macilias.mediasearch.client.model.enumerations.TargetSystem;
import com.macilias.mediasearch.client.model.enumerations.UserData;
import com.macilias.mediasearch.client.model.universal.FileSystemSettingsPersistor;
import com.macilias.mediasearch.client.model.facebook.FBSettingsPersistor;
import com.macilias.mediasearch.client.model.interfaces.SettingsPersistor;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for reading and saving the user preferences
 */
public class UserSettingsController {

	private static final Logger LOG = Logger.getLogger(UserSettingsController.class);

	private static UserSettingsController instance = new UserSettingsController();
	private final Map<String, Object> userSettings;
	private SettingsPersistor persistor;


	private UserSettingsController() {
		persistor = new FileSystemSettingsPersistor();
		userSettings = new HashMap<String, Object>();
	}


	public static UserSettingsController getInstance() {
		return instance;
	}


	@VisibleForTesting
	public void setPersistor(final SettingsPersistor persistor) {
		this.persistor = persistor;
	}


	public Map<String, Object> getUserSettings() {
		if (userSettings.isEmpty()) {
			Map<String, Object> loadedSettings = loadUserSettings();
			if (loadedSettings == null || loadedSettings.isEmpty()) {
				loadedSettings = new HashMap<String, Object>();
			}
			userSettings.putAll(loadedSettings);
		}
		return userSettings;
	}


	private Map<String, Object> loadUserSettings() {
		return loadUserSettings(null);
	}


	@VisibleForTesting
	@SuppressWarnings("unchecked")
	Map<String, Object> loadUserSettings(Map<String, String> jsonMap) {
		if (jsonMap == null || jsonMap.isEmpty()) {
			jsonMap = persistor.loadUserSettings();
		}
		Map<String, Object> resultSettings = new HashMap<String, Object>();
		if (jsonMap != null) {
			for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
				UserData userData = UserData.getUserDataForKey(entry.getKey());
				if (userData != null) {
					try {
						if (userData.isCollection()) {
							if (userData.getMainClass() == MediaSearch.class) {
								// has to be done separately because Gson doesn't know how to create MediaSearch objects
								addIfNotNull(resultSettings, loadMediaSearchList(entry.getValue()), entry.getKey());
							} else {
								addIfNotNull(resultSettings, loadList(userData.getMainClass(), entry.getValue()), entry.getKey());
							}
						} else {
							addIfNotNull(resultSettings, loadObject(userData.getMainClass(), entry.getValue()), entry.getKey());
						}
					}
					catch (JsonSyntaxException e) {
						LOG.error(String.format("The settings format has probably changed, the setting: '%s' could not be loaded", entry.getKey()), e);
					}
				}
			}
		}
		return resultSettings;
	}


	private void addIfNotNull(final Map<String, Object> userSettings, final Object value, final String key) {
		if (value != null) {
			userSettings.put(key, value);
		} else {
			userSettings.remove(key);
		}
	}


	private List<MediaSearch> loadMediaSearchList(final String json) {
		Type type = new TypeToken<List<MediaSearch>>() {}.getType();
		return new Gson().fromJson(json, type);
	}


	private <T> List<T> loadList(final Class<T> mainClass, final String json) {
		Type type = new TypeToken<List<T>>() {}.getType();
		return new Gson().fromJson(json, type);
	}


	private <T> T loadObject(final Class<T> mainClass, final String json) {
		return new Gson().fromJson(json, TypeToken.get(mainClass).getType());
	}


	@SuppressWarnings("unchecked")
	public void saveUserSettings(final Map<String, Object> userSettings) {
		this.userSettings.putAll(userSettings);
		Map<String, String> jsonMap = new HashMap<String, String>();
		for (Map.Entry<String, Object> entry : userSettings.entrySet()) {
			UserData userData = UserData.getUserDataForKey(entry.getKey());
			if (userData != null) {
				String json = getJsonForObject(userData.getMainClass(), userData.isCollection(), entry.getValue());
				if (StringUtils.isNotEmpty(json)) {
					jsonMap.put(userData.getKey(), json);
				}
			}
		}
		persistSettings(jsonMap);
	}


	private <T> String getJsonForObject(final Class<T> mainClass, final boolean isCollection, final Object value) {
		Gson gson = new Gson();
		String jsonString;
		if (isCollection) {
			Type type = new TypeToken<List<T>>() {}.getType();
			jsonString = gson.toJson(value, type);
		} else {
			jsonString = gson.toJson(value, TypeToken.get(mainClass).getType());
		}
		return jsonString;
	}


	private void persistSettings(final Map<String, String> userSettingsJson) {
		Connection clientConnection = MediaSearchClient.getInstance().getClientConnection();
		if (clientConnection.getTargetSystem() == null) {
			LOG.error("persistSettings(): no connection to save settings on target");
		} else {
			TargetSystem targetSystem = clientConnection.getTargetSystem();
			if (targetSystem.getPersistence().equals(Persistence.FILE)) {
				persistor.saveUserSettings(userSettingsJson);
			} else if (targetSystem.equals(TargetSystem.FB) && targetSystem.getPersistence().equals(Persistence.CMS)) {
				new FBSettingsPersistor().saveUserSettings(userSettingsJson);
			}
		}
	}

}
