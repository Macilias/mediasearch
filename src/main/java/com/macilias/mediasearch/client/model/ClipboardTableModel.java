package com.macilias.mediasearch.client.model;

import java.util.ArrayList;
import java.util.List;

import com.macilias.mediasearch.client.model.interfaces.Reorderable;
import org.apache.log4j.Logger;

public class ClipboardTableModel extends AbstractMediaSearchHitTableModel implements Reorderable {

	private static final Logger LOG = Logger.getLogger(ClipboardTableModel.class);
	
	public static final int COL_INDEX_CREATED_AT = 0;
	public static final int COL_INDEX_UID = 1;
	public static final int COL_INDEX_TEXT = 2;
	public static final int COL_INDEX_DIMENSION = 3;
	public static final int COL_INDEX_PREVIEW = 4;


	public ClipboardTableModel() {
		super(new ClipboardColumnDescriptor());
	}


	public void addAll(final List<MediaSearchHit> mediaSearchHits) {
		int sizeBeforeInsertion = this.mediaSearchHits.size();
		for (MediaSearchHit mediaSearchHit : mediaSearchHits) {
			if (!this.mediaSearchHits.contains(mediaSearchHit)) {
				this.mediaSearchHits.add(mediaSearchHit);
			}
		}
		if (this.mediaSearchHits.size() != sizeBeforeInsertion) {
			fireTableRowsInserted(sizeBeforeInsertion == 0 ? 0 : sizeBeforeInsertion - 1, this.mediaSearchHits.size() - 1);
		}
	}


	public void removeAll(final List<MediaSearchHit> hitsToRemove) {
		if (hitsToRemove.size() > 0) {
			mediaSearchHits.removeAll(hitsToRemove);
			fireTableDataChanged();
		}
	}


	public Object getValueAt(final int rowIndex, final int columnIndex) {
		MediaSearchHit mediaSearchHit = mediaSearchHits.get(rowIndex);
		if (mediaSearchHit == null) {
			LOG.warn(String.format("The Table has only '%s' rows. Therefore access to row index = '%s' is invalid", mediaSearchHits.size(), rowIndex));
			return null;
		}
		switch (columnIndex) {
			case COL_INDEX_CREATED_AT:
				return mediaSearchHit.getCreationDate();
			case COL_INDEX_UID:
				return mediaSearchHit.getUid();
			case COL_INDEX_TEXT:
				return mediaSearchHit.getText();
			case COL_INDEX_DIMENSION:
				return mediaSearchHit.getDimension();
			case COL_INDEX_PREVIEW:
				return mediaSearchHit.getPreview();
			default:
				LOG.warn(String.format("The Table has only '%s' columns. Therefore access to column index = '%s' is invalid", getColumnCount(), columnIndex));
				return null;
		}
	}


	public int reorder(int[] fromIndices, int toIndex) {
		List<MediaSearchHit> toCopy = new ArrayList<MediaSearchHit>(fromIndices.length);
		int offset = 0;
		for (int fromIndex : fromIndices) {
			toCopy.add(mediaSearchHits.get(fromIndex));
			if (fromIndex < toIndex) {
				--offset;
			}
		}
		mediaSearchHits.removeAll(toCopy);
		mediaSearchHits.addAll(toIndex + offset, toCopy);

		fireTableDataChanged();
		return offset;
	}
}
