package com.macilias.mediasearch.extensions.renderers;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ColoredRenderer extends DefaultTableCellRenderer {

	private static final Logger LOG = Logger.getLogger(ColoredRenderer.class);

	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
//		setIcon(new ColorIcon((Color) value));
		LOG.warn("not implemented yet");
		return this;
	}
}
