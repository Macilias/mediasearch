package com.macilias.mediasearch.client.model.interfaces;

import javax.swing.JProgressBar;
import javax.swing.JTable;

public interface PictureCacheCleaner {

	void eraseSomePictures(JTable resultTable, JProgressBar progressBar, JProgressBar memoryBar, int actualPosition, byte[] placeholderPic, byte[] placeholderPrev);

}
