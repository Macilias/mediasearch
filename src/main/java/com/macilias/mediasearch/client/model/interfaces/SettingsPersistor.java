package com.macilias.mediasearch.client.model.interfaces;

import java.util.Map;

public interface SettingsPersistor {

	Map<String, String> loadUserSettings();


	void saveUserSettings(Map<String, String> userSettings);
}
