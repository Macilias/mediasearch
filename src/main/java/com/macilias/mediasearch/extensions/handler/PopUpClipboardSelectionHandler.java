package com.macilias.mediasearch.extensions.handler;

import java.awt.Component;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.commons.lang3.StringUtils;

import com.macilias.mediasearch.client.model.ClipboardTableModel;
import com.macilias.mediasearch.client.model.MediaSearchHit;
import com.macilias.mediasearch.client.model.enumerations.MediaSearchIcon;
import com.macilias.mediasearch.client.model.interfaces.ClipboardHandler;

public class PopUpClipboardSelectionHandler extends AbstractPopUpHandler implements ClipboardHandler {

	public String getTitle() {
		return "IDs anzeigen";
	}


	public String getDescription() {
		return "Hiermit werden die IDs der Suchtreffer in einem Dialog zum kopieren bereitgestellt";
	}


	public boolean closeAfterHandling() {
		return false;
	}


	public boolean toBackgroundAfterHandling() {
		return false;
	}


	public boolean emptyClipboardAfterHandling() {
		return false;
	}


	public void handleSelection(final JTable clipboardTable, final Component component) {
		List<MediaSearchHit> mediaSearchHits = ((ClipboardTableModel) clipboardTable.getModel()).getMediaSearchHits();
		getTextArea().setText(StringUtils.join(mediaSearchHits, ", "));
		JOptionPane.showMessageDialog(component, new JScrollPane(getTextArea()), "IDs der Sammelmappe", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
	}

}
