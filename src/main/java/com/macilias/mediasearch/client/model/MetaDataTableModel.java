package com.macilias.mediasearch.client.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MetaDataTableModel extends AbstractTableModel {

	private List<MetaData> metaDataList = new ArrayList<MetaData>();

	private enum MetaDataTableColumn {
		NAME("Name", String.class),
		VALUE("Wert", String.class);

		private final String columnName;
		private final Class columnClass;


		private MetaDataTableColumn(final String columnName, final Class columnClass) {
			this.columnName = columnName;
			this.columnClass = columnClass;
		}
	}


	public void replaceMetaInfos(final List<MetaData> metaDataList) {
		this.metaDataList = metaDataList;
		fireTableDataChanged();
	}


	@Override
	public int getRowCount() {
		return metaDataList.size();
	}


	@Override
	public int getColumnCount() {
		return MetaDataTableColumn.values().length;
	}


	@Override
	public String getColumnName(final int columnIndex) {
		return MetaDataTableColumn.values()[columnIndex].columnName;
	}


	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		return MetaDataTableColumn.values()[columnIndex].columnClass;
	}


	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return false;
	}


	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		MetaData metaData = metaDataList.get(rowIndex);
		if (metaData != null) {
			if (columnIndex == MetaDataTableColumn.NAME.ordinal()) {
				return metaData.getName();
			} else if (columnIndex == MetaDataTableColumn.VALUE.ordinal()) {
				return metaData.getValue();
			}
		}
		return null;
	}

}
