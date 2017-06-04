package com.macilias.mediasearch.extensions.handler;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;

import com.macilias.mediasearch.client.model.MediaSearchHit;
import com.macilias.mediasearch.client.model.ResultTableModel;
import com.macilias.mediasearch.client.model.interfaces.ResultHandler;

public class OpenResultSelectionHandler extends AbstractOpenInClientHandler implements ResultHandler {

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
	public void handleSelection(final JTable resultTable, final Component component) {
		if (resultTable.getSelectedRow() != -1) {
			List<MediaSearchHit> mediaSearchHits = ((ResultTableModel) resultTable.getModel()).getMediaSearchHits();
			MediaSearchHit mediaSearchHit = mediaSearchHits.get(resultTable.convertRowIndexToModel(resultTable.getSelectedRow()));
			openInClient(mediaSearchHit);

		}
	}

}
