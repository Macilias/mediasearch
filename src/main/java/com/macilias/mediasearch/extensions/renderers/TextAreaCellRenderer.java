package com.macilias.mediasearch.extensions.renderers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class TextAreaCellRenderer implements TableCellRenderer {

	private JTextArea renderer;
	private final Color oddRowColor = new Color(243, 243, 243);


	public TextAreaCellRenderer() {
		renderer = new JTextArea();
		renderer.setLineWrap(true);
		renderer.setWrapStyleWord(true);
		renderer.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}


	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
		if (isSelected) {
			renderer.setForeground(table.getSelectionForeground());
			renderer.setBackground(table.getSelectionBackground());
		} else {
			renderer.setForeground(table.getForeground());
			renderer.setBackground((row % 2 == 0) ? oddRowColor : table.getBackground());
		}
		renderer.setFont(table.getFont());
		renderer.setText((value == null) ? "" : value.toString());
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(renderer);
		table.setRowHeight(row, contentPane.getPreferredSize().height); // sets row's height
		return contentPane;
	}
}
