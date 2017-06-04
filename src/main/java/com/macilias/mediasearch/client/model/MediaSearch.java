package com.macilias.mediasearch.client.model;

import java.util.Date;

import com.macilias.mediasearch.client.model.enumerations.MediaType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.macilias.mediasearch.client.model.enumerations.Interval;

public class MediaSearch implements Cloneable {

	private final String name;
	private String id;
	private String uid;
	private String query;
	private MediaType type;
	private boolean timeFromChecked;
	private boolean timeToChecked;
	private boolean intervalChecked;
	private Date timeFrom;
	private Date timeTo;
	private Interval interval;
	private boolean activeOnly;
	private boolean inactiveOnly;


	public MediaSearch() {
		this(null);
	}


	public MediaSearch(final String name) {
		this.name = StringUtils.trimToEmpty(name);
	}


	@Override
	public MediaSearch clone() {
		return clone(query);
	}


	public MediaSearch clone(final String query) {
		MediaSearch clone = new MediaSearch(name);
		clone.setId(id);
		clone.setUid(uid);
		clone.setQuery(query);
		clone.setType(type);
		clone.setTimeFromChecked(timeFromChecked);
		clone.setTimeToChecked(timeToChecked);
		clone.setIntervalChecked(intervalChecked);
		clone.setTimeFrom((Date) timeFrom.clone());
		clone.setTimeTo((Date) timeTo.clone());
		clone.setInterval(interval);
		clone.setActiveOnly(activeOnly);
		clone.setInactiveOnly(inactiveOnly);
		return clone;
	}


	public String getName() {
		return name;
	}


	public String getId() {
		return id;
	}


	public String getUid() {
		return uid;
	}


	public void setId(final String id) {
		this.id = StringUtils.trimToEmpty(id);
	}


	public void setUid(final String uid) {
		this.uid = StringUtils.trimToEmpty(uid);
	}


	public String getQuery() {
		return query;
	}


	public void setQuery(final String query) {
		this.query = StringUtils.trimToNull(query);
	}


	public String getType() {
		return type.getQuery();
	}


	public void setType(final MediaType type) {
		this.type = type;
	}


	public boolean isActiveOnly() {
		return activeOnly;
	}


	public boolean isInactiveOnly() {
		return inactiveOnly;
	}


	public void setActiveOnly(final boolean activeOnly) {
		this.activeOnly = activeOnly;
	}


	public void setInactiveOnly(final boolean inactiveOnly) {
		this.inactiveOnly = inactiveOnly;
	}


	public boolean isTimeFromChecked() {
		return timeFromChecked;
	}


	public void setTimeFromChecked(final boolean timeFromChecked) {
		this.timeFromChecked = timeFromChecked;
	}


	public boolean isTimeToChecked() {
		return timeToChecked;
	}


	public void setTimeToChecked(final boolean timeToChecked) {
		this.timeToChecked = timeToChecked;
	}


	public boolean isIntervalChecked() {
		return intervalChecked;
	}


	public void setIntervalChecked(final boolean intervalChecked) {
		this.intervalChecked = intervalChecked;
	}


	public Date getTimeFrom() {
		return timeFrom;
	}


	public void setTimeFrom(final Date timeFrom) {
		this.timeFrom = timeFrom;
	}


	public Date getTimeTo() {
		return timeTo;
	}


	public void setTimeTo(final Date timeTo) {
		this.timeTo = timeTo;
	}


	public Interval getInterval() {
		return interval;
	}


	public void setInterval(final Interval interval) {
		this.interval = interval;
	}


	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		MediaSearch that = (MediaSearch) obj;
		return new EqualsBuilder()
						.append(name, that.name)
						.append(id, that.id)
						.append(query, that.query)
						.append(type, that.type)
						.append(timeFromChecked, that.timeFromChecked)
						.append(timeToChecked, that.timeToChecked)
						.append(intervalChecked, that.intervalChecked)
						.append(timeFrom, that.timeFrom)
						.append(timeTo, that.timeTo)
						.append(interval, that.interval)
						.append(activeOnly, that.activeOnly)
						.append(inactiveOnly, that.inactiveOnly)
						.isEquals();
	}


	@Override
	public String toString() {
		return name;
	}
}