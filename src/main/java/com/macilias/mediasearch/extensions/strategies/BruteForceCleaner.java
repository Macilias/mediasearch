package com.macilias.mediasearch.extensions.strategies;

import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.JTable;

import com.macilias.mediasearch.client.model.enumerations.Media;
import com.macilias.mediasearch.client.model.interfaces.PictureCacheCleaner;
import com.macilias.mediasearch.client.model.MediaSearchHit;
import com.macilias.mediasearch.client.model.ResultTableModel;

public class BruteForceCleaner implements PictureCacheCleaner {

	private byte[] placeholderPic;
	private byte[] placeholderPrev;


	/*
	 * This strategy is going to relief half of the available space.
	 * The strategy does it by starting at actualPosition +1 and cleaning everything what comes after, as long the space is available.
	 * In the case, in which there was not enough space to clean up, it starts at the beginning and goes its way to actualPosion.
	 */
	public void eraseSomePictures(final JTable resultTable, final JProgressBar progressBar, final JProgressBar memoryBar, final int actualPosition, final byte[] placeholderPic, final byte[] placeholderPrev) {
		this.placeholderPic = placeholderPic;
		this.placeholderPrev = placeholderPrev;
		ResultTableModel model = (ResultTableModel) resultTable.getModel();
		List<MediaSearchHit> mediaSearchHits = model.getMediaSearchHits();
		long desiredSpaceInByte = model.getMaximalSpaceInByte() / 2;
		int n = mediaSearchHits.size() / 10;
		if (n > 20) {
			n = 20;
		}

		boolean minimumSpaceErased = false;

		// go from actual position (+n) to the end
		for (int i = actualPosition + n; i < mediaSearchHits.size() && !minimumSpaceErased; i++) {
			minimumSpaceErased = eraseAndUpdateModel(resultTable, progressBar, memoryBar, model, mediaSearchHits, desiredSpaceInByte, i);
		}

		// if still not minimum space erased go from beginning to actual position
		for (int i = 0; i < actualPosition && !minimumSpaceErased; i++) {
			minimumSpaceErased = eraseAndUpdateModel(resultTable, progressBar, memoryBar, model, mediaSearchHits, desiredSpaceInByte, i);
		}
	}


	private boolean eraseAndUpdateModel(final JTable resultTable, final JProgressBar progressBar, final JProgressBar memoryBar, final ResultTableModel model, final List<MediaSearchHit> mediaSearchHits, final long desiredSpaceInByte, final int i) {
		int j = resultTable.convertRowIndexToModel(i);
		MediaSearchHit mediaSearchHit = mediaSearchHits.get(j);
		int size = erasePictureAndPreview(mediaSearchHit);
		if (size > 0) {
			model.decreaseConsumedSpaceInByte(size);
			model.fireTableRowsUpdated(j, j);
			memoryBar.setValue(model.getConsumedSpaceInByteAsInt());
			progressBar.setValue(progressBar.getValue() - 1);
			return model.getConsumedSpaceInByte() < desiredSpaceInByte;
		}
		return false;
	}


	public int erasePictureAndPreview(final MediaSearchHit mediaSearchHit) {
		if (mediaSearchHit.getType() == Media.PICTURE && mediaSearchHit.isFsPictureFetched()) {
			int result = mediaSearchHit.getPicture().length;
			mediaSearchHit.setPicture(placeholderPic);
			mediaSearchHit.setPreview(placeholderPrev);
			mediaSearchHit.setFsPictureFetched(false);
			return result;
		} else {
			return 0;
		}
	}

}
