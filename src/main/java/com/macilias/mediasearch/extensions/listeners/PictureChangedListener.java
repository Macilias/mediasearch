package com.macilias.mediasearch.extensions.listeners;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.macilias.mediasearch.client.model.MediaSearchHit;
import com.macilias.mediasearch.client.model.interfaces.SearchManager;

public final class PictureChangedListener implements TableModelListener {
	private final SearchManager manager;
	private MediaSearchHit mediaSearchHit;
	private int selectedIndex;


	public PictureChangedListener(final SearchManager manager) {
		this.manager = manager;
		selectedIndex = 0;
		mediaSearchHit = null;
	}


	@Override
	public void tableChanged(final TableModelEvent event) {
		if (mediaSearchHit != null && event.getFirstRow() == selectedIndex) {
			manager.updatePreviewPanel(mediaSearchHit, -1);
		}
	}


	public void setMediaSearchHit(final MediaSearchHit mediaSearchHit) {
		this.mediaSearchHit = mediaSearchHit;
	}


	public void setSelectedIndex(final int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

}
