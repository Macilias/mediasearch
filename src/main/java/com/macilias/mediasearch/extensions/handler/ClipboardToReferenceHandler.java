package com.macilias.mediasearch.extensions.handler;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;

import com.macilias.mediasearch.client.model.ClipboardTableModel;
import com.macilias.mediasearch.client.model.interfaces.ClipboardHandler;
import com.macilias.mediasearch.client.model.MediaSearchHit;

public class ClipboardToReferenceHandler extends AbstractReferenceHandler implements ClipboardHandler {

	public String getTitle() {
		return "Übernehmen";
	}


	public String getDescription() {
		return "Erstes Element als Vorspannmedium übernehmen";
	}


	public boolean closeAfterHandling() {
		return true;
	}


	public boolean toBackgroundAfterHandling() {
		return false;
	}


	public boolean emptyClipboardAfterHandling() {
		return true;
	}


	public void handleSelection(final JTable clipboardTable, final Component component) {
		List<MediaSearchHit> mediaSearchHits = ((ClipboardTableModel) clipboardTable.getModel()).getMediaSearchHits();
		if (!mediaSearchHits.isEmpty()) {
			setReference(mediaSearchHits.get(0));
		}
	}
}
