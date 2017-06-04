package com.macilias.mediasearch.extensions.layout;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class CenteredFrame extends JFrame {

	public CenteredFrame(final String title) {
		super(title);
	}


	public void center() {
		pack();
		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// Determine the new location of the window
		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;

		// Move the window
		setLocation(x, y);
	}

}
