package com.macilias.mediasearch.client.model;

import javax.swing.DefaultListModel;

public class FavouriteSearchesListModel extends DefaultListModel {

	public MediaSearch getComponent(final int i) {
		return ((MediaSearch) super.getElementAt(i));
	}

}
