package com.macilias.mediasearch.extensions.handler;

import javax.swing.JTextArea;

public abstract class AbstractPopUpHandler {
	private static final int COLS = 10;
	private static final int ROWS = 10;
	private JTextArea textArea;


	public AbstractPopUpHandler() {
		createIdTextArea();
	}


	private JTextArea createIdTextArea() {
		textArea = new JTextArea(ROWS, COLS);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setCaretPosition(0);
		textArea.setEditable(false);
		return textArea;
	}


	public JTextArea getTextArea() {
		return textArea;
	}

}
