package com.macilias.mediasearch.client.model.facebook;

import com.macilias.mediasearch.client.model.interfaces.SettingsPersistor;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Some Description
 *
 * @author macilias@gmail.com
 */
public class FBSettingsPersistor implements SettingsPersistor {

    private static final Logger LOG = Logger.getLogger(FBSettingsPersistor.class);

    @Override
    public Map<String, String> loadUserSettings() {
        LOG.warn("loadUserSettings(): not implemented yet");
        return null;
    }

    @Override
    public void saveUserSettings(Map<String, String> userSettings) {
        LOG.warn("saveUserSettings(): not implemented yet");
    }
}
