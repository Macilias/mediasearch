package com.macilias.mediasearch.client.model;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.macilias.mediasearch.client.model.interfaces.ColumnDescriptor;
import com.macilias.mediasearch.extensions.renderers.ColoredRenderer;
import com.macilias.mediasearch.extensions.renderers.FormatRenderer;
import com.macilias.mediasearch.extensions.renderers.ToolTipRenderer;

public class ResultColumnDescriptor implements ColumnDescriptor {

	private static final SimpleDateFormat SORTDATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");


	public enum Column {
		RELEASE_STATE("", Color.class, 15, 15, 15, SwingConstants.CENTER),
		SORT_DATE("Sortierdatum", Date.class, 80, 80, 100, SwingConstants.CENTER),
		UID("UID", String.class, 90, 200, 1000, SwingConstants.LEFT),
		TEXT("Text zum Medium", String.class, 90, 560, 4000, SwingConstants.LEFT),
		DIMENSION("Höhe/Breite", String.class, 70, 80, 90, SwingConstants.CENTER);

		private final String title;
		private final Class<?> columnType;
		private final int minWidth;
		private final int prefWidth;
		private final int maxWidth;
		private final int horizontalAlignment;


		private Column(final String title, final Class<?> columnType, final int minWidth, final int prefWidth, final int maxWidth, final int horizontalAlignment) {
			this.title = title;
			this.columnType = columnType;
			this.minWidth = minWidth;
			this.prefWidth = prefWidth;
			this.maxWidth = maxWidth;
			this.horizontalAlignment = horizontalAlignment;
		}
	}


	@Override
	public int getCount() {
		return Column.values().length;
	}


	@Override
	public String getTitle(final int columnIndex) {
		return Column.values()[columnIndex].title;
	}


	@Override
	public Class<?> getType(final int columnIndex) {
		return Column.values()[columnIndex].columnType;
	}


	@Override
	public int getMinWidth(final int columnIndex) {
		return Column.values()[columnIndex].minWidth;
	}


	@Override
	public int getPrefWidth(final int columnIndex) {
		return Column.values()[columnIndex].prefWidth;
	}


	@Override
	public int getMaxWidth(final int columnIndex) {
		return Column.values()[columnIndex].maxWidth;
	}


	@Override
	public int getHorizontalAlignment(final int columnIndex) {
		return Column.values()[columnIndex].horizontalAlignment;
	}


	@Override
	public TableCellRenderer getTableCellRenderer(final int columnIndex) {
		TableCellRenderer renderer;
		if (getType(columnIndex) == Date.class) {
			renderer = new FormatRenderer(SORTDATE_FORMAT);
		} else if (getType(columnIndex) == Color.class) {
			renderer = new ColoredRenderer();
		} else if (columnIndex == Column.UID.ordinal() || columnIndex == Column.TEXT.ordinal()) {
			renderer = new ToolTipRenderer();
		} else {
			renderer = new DefaultTableCellRenderer();
		}
		((JLabel) renderer).setHorizontalAlignment(getHorizontalAlignment(columnIndex));
		return renderer;
	}
}
