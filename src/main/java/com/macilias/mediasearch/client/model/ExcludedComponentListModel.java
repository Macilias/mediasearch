package com.macilias.mediasearch.client.model;

import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;

public class ExcludedComponentListModel extends DefaultListModel {


	public List<String> getExcludedComponentNames() {
		List<String> result = new LinkedList<String>();
		for (int i = 0; i < super.size(); i++) {
			result.add((String) getElementAt(i));
		}
		return result;
	}
}
