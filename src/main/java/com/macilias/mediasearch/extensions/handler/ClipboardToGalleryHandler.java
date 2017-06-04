package com.macilias.mediasearch.extensions.handler;

import java.awt.Component;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;

import com.macilias.mediasearch.client.model.ClipboardTableModel;
import com.macilias.mediasearch.client.model.interfaces.ClipboardHandler;
import com.macilias.mediasearch.client.model.MediaSearchHit;

public class ClipboardToGalleryHandler extends AbstractGalleryHandler implements ClipboardHandler {

	@Override
	public String getTitle() {
		return "Übernehmen";
	}


	public String getAltTitle() {
		return "An Position übernehmen";
	}


	@Override
	public String getDescription() {
		return "Auswahl in die Bilderstrecke übernehmen";
	}


	public String getAltDescription() {
		return "Auswahl in die Bilderstrecke an eine bestimmte Position übernehmen";
	}


	@Override
	public boolean closeAfterHandling() {
		return true;
	}


	@Override
	public boolean toBackgroundAfterHandling() {
		return false;
	}


	@Override
	public boolean emptyClipboardAfterHandling() {
		return true;
	}


	@Override
	public void handleSelection(final JTable clipboardTable, final Component component) {
		handleSelection(clipboardTable, 0);
	}


	public void handleSelection(JTable clipboardTable, int targetPosition) {
		List<MediaSearchHit> mediaSearchHits = ((ClipboardTableModel) clipboardTable.getModel()).getMediaSearchHits();
		Collections.reverse(mediaSearchHits);
		addToGallery(mediaSearchHits, targetPosition);
	}
}
