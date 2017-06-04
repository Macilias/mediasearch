package com.macilias.mediasearch.extensions.layout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

/*
 * Search filds are per convinience likely to have rounded corners.
 * This class provides the functionality to round up a swnig component.
 */
public class RoundedCornerBorder extends AbstractBorder {

	private final boolean standalone;


	public RoundedCornerBorder(final boolean standalone) {
		this.standalone = standalone;
	}


	@Override
	public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int r = height - 1;
		RoundRectangle2D round = new RoundRectangle2D.Float(x, y, width - 1, height - 1, r, r);
		Container parent = c.getParent();
		if (parent != null) {
			// Color.white is only for FS environment - otherwise use parent.getBackground()
			if (standalone) {
				g2.setColor(parent.getBackground());
			} else {
				g2.setColor(Color.white);
			}
			Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
			corner.subtract(new Area(round));
			g2.fill(corner);
		}
		g2.setColor(Color.gray);
		g2.draw(round);
		g2.dispose();
	}


	@Override
	public Insets getBorderInsets(final Component c) {
		return new Insets(4, 8, 4, 8);
	}


	@Override
	public Insets getBorderInsets(final Component c, final Insets insets) {
		insets.left = insets.right = 8;
		insets.top = insets.bottom = 4;
		return insets;
	}
}