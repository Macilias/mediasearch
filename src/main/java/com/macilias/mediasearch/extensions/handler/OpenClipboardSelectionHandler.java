package com.macilias.mediasearch.extensions.handler;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;

import com.macilias.mediasearch.client.model.ClipboardTableModel;
import com.macilias.mediasearch.client.model.MediaSearchHit;
import com.macilias.mediasearch.client.model.interfaces.ClipboardHandler;

public class OpenClipboardSelectionHandler extends AbstractOpenInClientHandler implements ClipboardHandler {

	@Override
	public String getTitle() {
		return "Medium öffnen";
	}


	@Override
	public String getDescription() {
		return "Öffnet das ausgewählte Medium in FirstSpirit";
	}


	@Override
	public boolean closeAfterHandling() {
		return false;
	}


	@Override
	public boolean toBackgroundAfterHandling() {
		return true;
	}


	@Override
	public boolean emptyClipboardAfterHandling() {
		return false;
	}


	@Override
	public void handleSelection(final JTable clipboardTable, final Component component) {
		if (clipboardTable.getSelectedRow() != -1) {
			List<MediaSearchHit> mediaSearchHits = ((ClipboardTableModel) clipboardTable.getModel()).getMediaSearchHits();
			MediaSearchHit mediaSearchHit = mediaSearchHits.get(clipboardTable.convertRowIndexToModel(clipboardTable.getSelectedRow()));
			openInClient(mediaSearchHit);
		}
	}
}
