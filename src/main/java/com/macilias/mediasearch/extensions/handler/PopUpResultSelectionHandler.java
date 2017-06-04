package com.macilias.mediasearch.extensions.handler;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.macilias.mediasearch.client.model.interfaces.ResultHandler;
import org.apache.commons.lang3.StringUtils;

import com.macilias.mediasearch.client.model.MediaSearchHit;
import com.macilias.mediasearch.client.model.ResultTableModel;
import com.macilias.mediasearch.client.model.enumerations.MediaSearchIcon;

public class PopUpResultSelectionHandler extends AbstractPopUpHandler implements ResultHandler {

	@Override
	public String getTitle() {
		return "IDs anzeigen";
	}


	@Override
	public String getDescription() {
		return "Hiermit werden die IDs der markierten Suchtreffer in einem Dialog zum kopieren bereitgestellt";
	}


	@Override
	public boolean closeAfterHandling() {
		return false;
	}


	@Override
	public boolean toBackgroundAfterHandling() {
		return false;
	}


	@Override
	public void handleSelection(final JTable resultTable, final Component component) {
		int[] selectedRows = resultTable.getSelectedRows();
		if (selectedRows.length > 0) {
			String message = StringUtils.join(createListFromSelection(resultTable, selectedRows), ", ");
			getTextArea().setText(message);
			JOptionPane.showMessageDialog(component, new JScrollPane(getTextArea()), "IDs der Ergebnistabelle", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
		} else {
			JOptionPane.showMessageDialog(component, "Bitte Suchergebnis(se) selektieren", "Hinweis", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
		}
	}


	private List<MediaSearchHit> createListFromSelection(final JTable resultTable, final int[] selectedRows) {
		List<MediaSearchHit> allHits = ((ResultTableModel) resultTable.getModel()).getMediaSearchHits();
		List<MediaSearchHit> selectedHits = new LinkedList<MediaSearchHit>();
		for (int selectedRow : selectedRows) {
			selectedHits.add(allHits.get(resultTable.convertRowIndexToModel(selectedRow)));
		}
		return selectedHits;
	}
}
