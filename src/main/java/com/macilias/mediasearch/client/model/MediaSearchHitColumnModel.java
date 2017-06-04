package com.macilias.mediasearch.client.model;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import com.macilias.mediasearch.client.model.interfaces.ColumnDescriptor;

public class MediaSearchHitColumnModel extends DefaultTableColumnModel {

	private final ColumnDescriptor columnDescriptor;


	public MediaSearchHitColumnModel(final ColumnDescriptor columnDescriptor) {
		this.columnDescriptor = columnDescriptor;
		initialize();
	}


	private void initialize() {
		for (int i = 0; i < columnDescriptor.getCount(); i++) {
			TableColumn tableColumn = new TableColumn(i, columnDescriptor.getPrefWidth(i), columnDescriptor.getTableCellRenderer(i), null);
			tableColumn.setMinWidth(columnDescriptor.getMinWidth(i));
			tableColumn.setMaxWidth(columnDescriptor.getMaxWidth(i));
			tableColumn.setHeaderValue(columnDescriptor.getTitle(i));
			this.addColumn(tableColumn);
		}
	}


	public Class<?> getColumnClass(final int columnTypeIndex) {
		return columnDescriptor.getType(columnTypeIndex);
	}

}
