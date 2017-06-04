package com.macilias.mediasearch.extensions.renderers;

import org.apache.log4j.Logger;

import java.text.Format;

import javax.swing.table.DefaultTableCellRenderer;


public class FormatRenderer extends DefaultTableCellRenderer {

	private static final Logger LOG = Logger.getLogger(FormatRenderer.class);

	private final Format formatter;


	public FormatRenderer(final Format formatter) {
		this.formatter = formatter;
	}


	@Override
	public void setValue(Object value) {
		// Format the Object before setting its value in the renderer
		try {
			if (value != null) {
				value = formatter.format(value);
			}
		}
		catch (IllegalArgumentException e) {
			LOG.error(String.format("The value '%s' could not be formatted with the '%s' formatter. The exception is:", value, formatter.toString()), e);
		}

		super.setValue(value);
	}

}
