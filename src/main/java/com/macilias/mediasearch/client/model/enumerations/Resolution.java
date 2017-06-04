package com.macilias.mediasearch.client.model.enumerations;

import java.awt.Dimension;

public enum Resolution {

	ORIGINAL("ORIGINAL", 0, 0),
	AUTO_MAX("AUTOMATIC MAX", 0, 0),
	AUTO_DEF("AUTOMATIC", 0, 0),
	AUTO_MIN("AUTOMATIC MIN", 0, 0),
	RETINA_MBP_15("MBP 15\" mit Retina (2880 x 1800)", 2880, 1800),
	IMAC_27("iMac 27\" (2560 x 1440)", 2560, 1440),
	WUXGA("WUXGA (1920 x 1200)", 1920, 1200),
	FULL_HD("FULL HD (1920 x 1080)", 1920, 1080),
	HD_PLUS("HD+ (1600 x 900)", 1600, 900),
	MBP_15("MBP 15\" (1440 x 900)", 1440, 900),
	MBP_13("MBP 13\" (1280 x 800)", 1280, 800),
	WXGA("WXGA (1280 x 800)", 1280, 800),
	SVGA("SVGA (800 x 600)", 800, 600),
	HVGA("HVGA (480 x 320)", 480, 320);

	private final String resolution;
	private int height;
	private int width;
	private boolean manipulated;


	private Resolution(final String resolution, final int width, final int height) {
		this.resolution = resolution;
		this.width = width;
		this.height = height;
		manipulated = false;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public boolean isManipulated() {
		return manipulated;
	}


	public Dimension getDimension() {
		return new Dimension(width, height);
	}


	public void setDimension(final Dimension dimension) {
		height = dimension.height;
		width = dimension.width;
		manipulated = true;
	}


	@Override
	public String toString() {
		return resolution;
	}
}
