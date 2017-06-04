package com.macilias.mediasearch.client.model.interfaces;

import javax.swing.table.TableCellRenderer;

public interface ColumnDescriptor {

	int getCount();


	String getTitle(final int columnIndex);


	Class<?> getType(final int columnIndex);


	int getMinWidth(final int columnIndex);


	int getPrefWidth(final int columnIndex);


	int getMaxWidth(final int columnIndex);


	int getHorizontalAlignment(final int columnIndex);


	TableCellRenderer getTableCellRenderer(final int columnIndex);
}
