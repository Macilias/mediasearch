package com.macilias.mediasearch.extensions.handler;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;

import com.macilias.mediasearch.client.model.interfaces.ResultHandler;
import com.macilias.mediasearch.client.model.MediaSearchHit;
import com.macilias.mediasearch.client.model.ResultTableModel;

public class ResultsToReferenceHandler extends AbstractReferenceHandler implements ResultHandler {

	@Override
	public String getTitle() {
		return "Übernehmen";
	}


	@Override
	public String getDescription() {
		return "Erstes selektiertes Element als Vorspannmedium übernehmen";
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
	public void handleSelection(final JTable resultTable, final Component component) {
		int[] selectedRows = resultTable.getSelectedRows();
		if (selectedRows.length > 0) {
			List<MediaSearchHit> mediaSearchHits = ((ResultTableModel) resultTable.getModel()).getMediaSearchHits();
			for (int selectedRow : selectedRows) {
				setReference(mediaSearchHits.get(resultTable.convertRowIndexToModel(selectedRow)));
				break;
			}
		}
	}

}
