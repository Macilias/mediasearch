package com.macilias.mediasearch.client.model;

import com.macilias.mediasearch.client.model.interfaces.SearchManager;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

//import de.espirit.common.Logging;
//import de.espirit.common.util.HashCodeBuilder;
//import de.espirit.firstspirit.access.store.IDProvider;
//import de.espirit.firstspirit.access.store.mediastore.Media;
//import de.espirit.firstspirit.access.store.mediastore.Picture;

public class MediaSearchHit {

	private static final Logger LOG = Logger.getLogger(MediaSearchHit.class);

	private static final Color DARK_RED = new Color(139, 0, 0);
	private static final String UNKNOWN = "unbekannt";
	private static final String STATUS = "Status";
	private String uid;
	private long id;
	private Date creationDate;
	private String text;
	private int width;
	private int height;
	private List<MetaData> metaInformation;
	private String name;
	private int type;
	private Picture fsPictureReference;
	private boolean fsPictureFetched = false;
	private byte[] preview;
	private volatile byte[] picture;
	private int status;


	public Color getStateAsColor() {
//		switch (status) {
//			case IDProvider.RELEASED:
//				return Color.WHITE; // 0
//			case IDProvider.CHANGED:
//				return Color.RED;   // 1
//			case IDProvider.NEVER_RELEASED:
//				return DARK_RED;  // 3
//			default:
//				return Color.GRAY;
//		}
		return Color.WHITE;
	}


	public void setStatus(final int status) {
		this.status = status;
//		switch (status) {
//			case IDProvider.RELEASED:
//				addMetaInformation(STATUS, "freigegeben");
//				break;
//			case IDProvider.CHANGED:
//				addMetaInformation(STATUS, "verändert");
//				break;
//			case IDProvider.NEVER_RELEASED:
//				addMetaInformation(STATUS, "noch nie freigegeben");
//				break;
//			default:
//				addMetaInformation(STATUS, UNKNOWN);
//		}
		addMetaInformation(STATUS, UNKNOWN);
	}


	public String getUid() {
		return uid;
	}


	public void setUid(final String uid) {
		this.uid = uid;
	}


	public long getId() {
		return id;
	}


	public void setId(final long id) {
		this.id = id;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}


	public String getName() {
		return name;
	}


	public void setName(final String name) {
		this.name = name;
	}


	public String getTypeAsString() {
//		switch (type) {
//			case Media.PICTURE:
//				return "Bild";
//			case Media.FILE:
//				return "Datei";
//			default:
//				return UNKNOWN;
//		}
		return "Bild";
	}


	public int getType() {
		return type;
	}


	public void setType(final int type) {
		this.type = type;
	}


	public String getText() {
		return text;
	}


	public void setText(final String text) {
		this.text = text;
	}


	public String getDimension() {
		return height + "/" + width;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public void setWidth(final int width) {
		this.width = width;
	}


	public void setHeight(final int height) {
		this.height = height;
	}


	public List<MetaData> getMetaInformation() {
		return metaInformation;
	}


	public void addMetaInformation(final String key, final String metaInformation) {
		if (this.metaInformation == null) {
			this.metaInformation = new LinkedList<MetaData>();
		}
		this.metaInformation.add(new MetaData(key, metaInformation));
	}


	public Picture getFsPictureReference() {
		return fsPictureReference;
	}


	public void setFsPictureReference(final Picture fsPictureReference) {
		this.fsPictureReference = fsPictureReference;
	}


	public boolean isFsPictureFetched() {
		return fsPictureFetched;
	}


	public void setFsPictureFetched(final boolean fsPictureFetched) {
		this.fsPictureFetched = fsPictureFetched;
	}


	public byte[] getPreview() {
		return preview;
	}


	public void setPreview(final byte[] preview) {
		this.preview = preview;
	}


	public byte[] getPicture() {
		return picture;
	}


	public void setPicture(final byte[] picture) {
		this.picture = picture;
	}


	public ImageIcon getScaledPicture(final SearchManager manager, final Scalr.Method scalingMethod, final Scalr.Mode scalingMode, final int targetWidth, final int targetHeight, final BufferedImageOp ops) {
		if (picture != null) {
			InputStream in = new ByteArrayInputStream(picture);
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(in);
			}
			catch (IOException e) {
				LOG.error(String.format("Error while reading the picture of '%s'. The byte representation could not be transformed into a BufferedImage. Consult stack trace for exact reason:", uid), e);
			}
			catch (OutOfMemoryError e) {
				bi = null;
				LOG.error(String.format("Error while reading the picture of '%s'! The problem is not enough space:", uid), e);
				manager.reactToOutOfMemoryProblem(false);
			}
			if (bi != null) {
				Image scaledInstance = Scalr.resize(bi, scalingMethod, scalingMode, targetWidth, targetHeight, ops);
				return new ImageIcon(scaledInstance);
			}
		}
		return new ImageIcon();
	}


	@Override
	public String toString() {
		return String.valueOf(id);
	}


	@Override
	public boolean equals(final Object aThat) {
		if (this == aThat) {
			return true;
		}
		if (!(aThat instanceof MediaSearchHit)) {
			return false;
		}
		MediaSearchHit that = (MediaSearchHit) aThat;
		return new EqualsBuilder().append(this.getId(), that.getId()).append(this.getUid(), that.getUid()).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).append(this.getUid()).toHashCode();
	}
}
