package com.macilias.mediasearch.extensions.handler;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;

import com.macilias.mediasearch.client.model.interfaces.ResultHandler;
import com.macilias.mediasearch.client.model.MediaSearchHit;
import com.macilias.mediasearch.client.model.ResultTableModel;

public class ResultsToGalleryHandler extends AbstractGalleryHandler implements ResultHandler {

	public String getTitle() {
		return "Übernehmen";
	}


	public String getAltTitle() {
		return "An Position übernehmen";
	}


	public String getDescription() {
		return "Auswahl direkt in die Bilderstrecke übernehmen";
	}


	public String getAltDescription() {
		return "Auswahl direkt in die Bilderstrecke an eine bestimmte Position übernehmen";
	}


	public boolean closeAfterHandling() {
		return true;
	}


	public boolean toBackgroundAfterHandling() {
		return false;
	}


	public void handleSelection(final JTable resultTable, final Component component) {
		handleSelection(resultTable, 0);
	}


	public void handleSelection(final JTable resultTable, int targetPosition) {
		int[] selectedRows = resultTable.getSelectedRows();
		if (selectedRows.length > 0) {
			List<MediaSearchHit> mediaSearchHits = ((ResultTableModel) resultTable.getModel()).getMediaSearchHits();
			List<MediaSearchHit> selectedSearchHits = new LinkedList<MediaSearchHit>();
			for (int selectedRow : selectedRows) {
				selectedSearchHits.add(mediaSearchHits.get(resultTable.convertRowIndexToModel(selectedRow)));
			}
			addToGallery(selectedSearchHits, targetPosition);
		}
	}


}
