package com.macilias.mediasearch.client.model.facebook;

import com.macilias.mediasearch.client.model.enumerations.TargetSystem;
import com.macilias.mediasearch.client.model.Connection;
import org.apache.log4j.Logger;

/**
 * Some Description
 *
 * @author macilias@gmail.com
 */
public class FBConnection extends Connection {

    private static final Logger LOG = Logger.getLogger(FBConnection.class);

    public FBConnection(TargetSystem targetSystem, String serverName, String user, String password, int port, boolean standAlone) {
        super(targetSystem, serverName, user, password, port, standAlone);
    }

    @Override
    public void openConnection() {
        LOG.warn("openConnection(): not implemented yet");
    }

    @Override
    public boolean isConnected() {
        LOG.warn("isConnected(): not implemented yet");
        return false;
    }

    @Override
    public Object getConnection() {
        LOG.warn("getConnection(): not implemented yet");
        return null;
    }

    @Override
    public void closeConnection() {
        LOG.warn("closeConnection(): not implemented yet");
    }
}
