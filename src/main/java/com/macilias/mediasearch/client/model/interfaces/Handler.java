package com.macilias.mediasearch.client.model.interfaces;

import java.awt.Component;

import javax.swing.JTable;

public interface Handler {

	String getTitle();


	String getDescription();


	boolean closeAfterHandling();


	boolean toBackgroundAfterHandling();


	void handleSelection(JTable table, Component component);

}
