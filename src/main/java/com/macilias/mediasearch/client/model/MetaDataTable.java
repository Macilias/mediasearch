package com.macilias.mediasearch.client.model;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import javax.swing.text.View;

public class MetaDataTable extends JTable {

	@Override
	public void doLayout() {
		TableColumn col = getColumnModel().getColumn(1);
		for (int row = 0; row < getRowCount(); row++) {
			Component c = prepareRenderer(col.getCellRenderer(), row, 1);
			if (c instanceof JTextArea) {
				JTextArea a = (JTextArea) c;
				int h = getPreferredHeight(a) + getIntercellSpacing().height;
				if (getRowHeight(row) != h) {
					setRowHeight(row, h);
				}
			}
		}
		super.doLayout();
	}


	private int getPreferredHeight(final JTextComponent c) {
		Insets insets = c.getInsets();
		View view = c.getUI().getRootView(c).getView(0);
		int preferredHeight = (int) view.getPreferredSpan(View.Y_AXIS);
		return preferredHeight + insets.top + insets.bottom;
	}
}
