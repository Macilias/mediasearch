package com.macilias.mediasearch.client.model;

import java.util.List;

import org.apache.log4j.Logger;

public class ResultTableModel extends AbstractMediaSearchHitTableModel {

	private static final Logger LOG = Logger.getLogger(ResultTableModel.class);
		
	public static final int COL_INDEX_STATUS = 0;
	public static final int COL_INDEX_CREATED_AT = 1;
	public static final int COL_INDEX_UID = 2;
	public static final int COL_INDEX_TEXT = 3;
	public static final int COL_INDEX_DIMENSION = 4;

	// 1 MB = 1024 KB = 1048576 Byte || MAX_INT = 2,147,483,647
	private static final long BYTE_CONSTANT = 1048576;

	private long maximalSpaceInByte = 128 * BYTE_CONSTANT;
	private long consumedSpaceInByte = 0;


	public ResultTableModel(final List<MediaSearchHit> mediaSearchHits, final int maximalSpaceInMegaByte) {
		super(mediaSearchHits, new ResultColumnDescriptor());
		maximalSpaceInByte = maximalSpaceInMegaByte * BYTE_CONSTANT;
	}


	public void addAll(final List<MediaSearchHit> mediaSearchHits) {
		int from = this.mediaSearchHits.size();
		this.mediaSearchHits.addAll(mediaSearchHits);
		int till = this.mediaSearchHits.size() - 1;
		this.fireTableRowsInserted(from, till);
	}


	public Object getValueAt(final int rowIndex, final int columnIndex) {
		MediaSearchHit mediaSearchHit = mediaSearchHits.get(rowIndex);
		if (mediaSearchHit == null) {
			LOG.warn(String.format("The Table has only '%s' rows. Therefore access to row index = '%s' is invalid", mediaSearchHits.size(), rowIndex));
			return null;
		}
		switch (columnIndex) {
			case COL_INDEX_STATUS:
				return mediaSearchHit.getStateAsColor();
			case COL_INDEX_CREATED_AT:
				return mediaSearchHit.getCreationDate();
			case COL_INDEX_UID:
				return mediaSearchHit.getUid();
			case COL_INDEX_TEXT:
				return mediaSearchHit.getText();
			case COL_INDEX_DIMENSION:
				return mediaSearchHit.getDimension();
			default:
				LOG.warn(String.format("The Table has only '%s' columns. Therefore access to column index = '%s' is invalid", getColumnCount(), columnIndex));
				return null;
		}
	}


	/*
	 * 1 Byte = 8 Bit
	 * 1 KB = 1024 Byte =8192 Bit
	 * 1 MB = 1024 KB = 1048576 Byte
	 * 1 GB = 1024 MB = 1048576 KB = 1073741824 Byte
	 * 1 TB = 1024 GB = 1048576 MB = 1073741824 KB
	 */
	public long getConsumedSpaceInByte() {
		return consumedSpaceInByte;
	}


	public int getConsumedSpaceInByteAsInt() {
		return consumedSpaceInByte >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) consumedSpaceInByte;
	}


	public long getMaximalSpaceInByte() {
		return maximalSpaceInByte;
	}


	public int getMaximalSpaceInByteAsInt() {
		return maximalSpaceInByte >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) maximalSpaceInByte;
	}


	public long getMaximalSpaceInMegaByte() {
		return maximalSpaceInByte / BYTE_CONSTANT;
	}


	public void increaseConsumedSpaceInByte(final int plus) {
		consumedSpaceInByte += plus;
	}


	public void decreaseConsumedSpaceInByte(final int minus) {
		consumedSpaceInByte -= minus;
	}

}
