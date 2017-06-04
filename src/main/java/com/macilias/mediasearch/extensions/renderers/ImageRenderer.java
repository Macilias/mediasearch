package com.macilias.mediasearch.extensions.renderers;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * Use a ImageRenderer to render a byte[] picture
 */
public class ImageRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		setText("");
		if (value != null) {
			ImageIcon imageIcon = new ImageIcon((byte[]) value);
			setIcon(imageIcon);
			if (table.getRowHeight(row) < imageIcon.getIconHeight()) {
				table.setRowHeight(row, imageIcon.getIconHeight());
			}
		}
		return this;
	}
}
