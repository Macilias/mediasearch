package com.macilias.mediasearch.extensions.handler;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import com.macilias.mediasearch.client.model.interfaces.Reorderable;

/**
 * Handles drag & drop row reordering
 */
public class TableRowTransferHandler extends TransferHandler {
	private final DataFlavor localObjectFlavor = new ActivationDataFlavor(int[].class, DataFlavor.javaJVMLocalObjectMimeType, "Integer Row Index");
	private JTable table = null;


	public TableRowTransferHandler(JTable table) {
		this.table = table;
	}


	@Override
	protected Transferable createTransferable(JComponent c) {
		assert (c == table);
		return new DataHandler(table.getSelectedRows(), localObjectFlavor.getMimeType());
	}


	@Override
	public boolean canImport(TransferHandler.TransferSupport info) {
		boolean b = info.getComponent() == table && info.isDrop() && info.isDataFlavorSupported(localObjectFlavor);
		table.setCursor(b ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
		return b;
	}


	@Override
	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY_OR_MOVE;
	}


	/*
		NOTE that java.lang.ClassCastException: [I cannot be cast to java.io.InputStream can accour while DnD.
		This is a known unsolved Bug in open JDK (https://bugs.openjdk.java.net/browse/JDK-8024061) and it has no impact on functionality.
	 */
	@Override
	public boolean importData(TransferHandler.TransferSupport info) {
		JTable target = (JTable) info.getComponent();
		JTable.DropLocation dl = (JTable.DropLocation) info.getDropLocation();
		int index = dl.getRow();
		int max = table.getModel().getRowCount();
		if (index < 0 || index > max) {
			index = max;
		}
		target.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		try {
			int[] rowsFrom = (int[]) info.getTransferable().getTransferData(localObjectFlavor);
				int offset = ((Reorderable) table.getModel()).reorder(rowsFrom, index);
				target.getSelectionModel().addSelectionInterval(index + offset, index + offset + rowsFrom.length - 1);
				return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	protected void exportDone(JComponent c, Transferable t, int act) {
		if (act == TransferHandler.MOVE) {
			table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

}