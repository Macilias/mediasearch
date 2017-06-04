package com.macilias.mediasearch.extensions.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.macilias.mediasearch.client.view.MediaSearchGUI;
import com.macilias.mediasearch.client.view.OptionPane;
import com.macilias.mediasearch.client.model.enumerations.MediaSearchIcon;
import com.macilias.mediasearch.client.model.interfaces.SearchCaller;
import com.macilias.mediasearch.client.view.DefaultOptionPane;

public class StopSearchingAndFetchingAction extends AbstractAction {
	private final SearchCaller caller;
	private final JComponent clientPanel;
	private OptionPane optionpane = new DefaultOptionPane();


	public StopSearchingAndFetchingAction(final SearchCaller caller, final JComponent clientPanel) {
		this.caller = caller;
		this.clientPanel = clientPanel;
	}


	@Override
	public void actionPerformed(final ActionEvent e) {
		if (caller.isQueryInProgress()) {
			caller.stopSearchingAndReactToTermination();
			int stopFetching = optionpane.showConfirmDialog(clientPanel, "Suche wurde abgebrochen. Auch das Laden der Bilder abbrechen?", MediaSearchGUI.DIALOG_INFO, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
			if (stopFetching == JOptionPane.YES_OPTION) {
				caller.stopFetching();
			}
		} else if (caller.isFetchingInProgress()) {
			int stopFetching = optionpane.showConfirmDialog(clientPanel, "Soll das Laden der Bilder abgebrochen werden?", MediaSearchGUI.DIALOG_INFO, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
			if (stopFetching == JOptionPane.YES_OPTION) {
				caller.stopFetching();
			}
		}
	}


	public void setOptionpane(final OptionPane optionpane) {
		this.optionpane = optionpane;
	}

}
