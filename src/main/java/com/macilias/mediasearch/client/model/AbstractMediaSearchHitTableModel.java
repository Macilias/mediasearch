package com.macilias.mediasearch.client.model;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.macilias.mediasearch.client.model.interfaces.ColumnDescriptor;

public abstract class AbstractMediaSearchHitTableModel extends AbstractTableModel {

	final MediaSearchHitColumnModel columns;
	List<MediaSearchHit> mediaSearchHits;


	public AbstractMediaSearchHitTableModel(final ColumnDescriptor columns) {
		this.columns = new MediaSearchHitColumnModel(columns);
		mediaSearchHits = new LinkedList<MediaSearchHit>();
	}


	public AbstractMediaSearchHitTableModel(final List<MediaSearchHit> mediaSearchHits, final ColumnDescriptor columns) {
		this(columns);
		this.mediaSearchHits = mediaSearchHits;
	}


	public MediaSearchHitColumnModel getColumnModel() {
		return columns;
	}


	public List<MediaSearchHit> getMediaSearchHits() {
		return mediaSearchHits;
	}


	public void clear() {
		mediaSearchHits.clear();
		fireTableDataChanged();
	}


	@Override
	public int getRowCount() {
		return mediaSearchHits.size();
	}


	@Override
	public int getColumnCount() {
		return columns.getColumnCount();
	}


	@Override
	public String getColumnName(final int columnIndex) {
		return columns.getColumn(columnIndex).getHeaderValue().toString();
	}


	@Override
	public Class<?> getColumnClass(final int columnTypeIndex) {
		return columns.getColumnClass(columnTypeIndex);
	}


	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return false;
	}


	@Override
	public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {}

}
