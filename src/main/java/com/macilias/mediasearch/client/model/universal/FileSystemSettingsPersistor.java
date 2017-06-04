package com.macilias.mediasearch.client.model.universal;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import com.macilias.mediasearch.client.model.interfaces.SettingsPersistor;
import org.apache.log4j.Logger;

public class FileSystemSettingsPersistor implements SettingsPersistor {

	private static final Logger LOG = Logger.getLogger(FileSystemSettingsPersistor.class);

	private File userSettingsFile = new File("userSettings.json");


	public Map<String, String> loadUserSettings() {
		Map<String, String> emptySettings = new HashMap<String, String>();
		try {
			if (!userSettingsFile.exists()) {
				userSettingsFile.createNewFile();
			} else {
				Type type = new TypeToken<Map<String, String>>() {}.getType();
				Map<String, String> parsedSettings = new Gson().fromJson(FileUtils.readFileToString(userSettingsFile, Charsets.UTF_8.name()), type);
				if (parsedSettings != null) {
					return parsedSettings;
				}
			}
		}
		catch (IOException e) {
			LOG.error(String.format("Error loading user settings from file: '%s'", userSettingsFile), e);
		}
		return emptySettings;
	}


	public void saveUserSettings(final Map<String, String> userSettings) {
		Map<String, String> previousSettings = loadUserSettings();
		previousSettings.putAll(userSettings);
		String settings = new Gson().toJson(previousSettings);
		try {
			FileUtils.writeStringToFile(userSettingsFile, settings, Charsets.UTF_8.name());
		}
		catch (IOException e) {
			LOG.error(String.format("Error writing user settings to file: '%s'", userSettingsFile), e);
		}
	}


	@VisibleForTesting
	public void setUserSettingsFile(final File userSettingsFile) {
		this.userSettingsFile = userSettingsFile;
	}

}
