package com.macilias.mediasearch.client.model.enumerations;

import java.util.Calendar;
import java.util.GregorianCalendar;

public enum Interval {

	DAY("Tag", null),
	WEEK("Woche", null),
	MONTH("Monat", null),
	YEAR("Jahr", null),
	Y2010("2010", 2010),
	Y2011("2011", 2011),
	Y2012("2012", 2012),
	Y2013("2013", 2013),
	Y2014("2014", 2014),
	Y2015("2015", 2015),
	Y2016("2016", 2016);

	private final String name;
	private final int year;


	Interval(final String name, final Integer year) {
		this.name = name;
		if (year == null) {
			this.year = Calendar.getInstance().get(Calendar.YEAR);
		} else {
			this.year = year;
		}
	}


	public int getYear() {
		return year;
	}


	public long getStartDateInMillis() {
		Calendar calendar = Calendar.getInstance();
		if (isYearInterval()) {
			return new GregorianCalendar(getYear(), Calendar.JANUARY, 1).getTimeInMillis();
		} else {
			switch (this) {
				default:
				case DAY:
					calendar.add(Calendar.DAY_OF_YEAR, -1);
					break;
				case WEEK:
					calendar.add(Calendar.WEEK_OF_YEAR, -1);
					break;
				case MONTH:
					calendar.add(Calendar.MONTH, -1);
					break;
				case YEAR:
					calendar.add(Calendar.YEAR, -1);
			}
			return calendar.getTimeInMillis();
		}
	}


	public long getEndDateInMillis() {
		return new GregorianCalendar(getYear() + 1, Calendar.JANUARY, 1).getTimeInMillis();
	}


	private boolean isYearInterval() {
		return ordinal() > 3;
	}


	@Override
	public String toString() {
		return name;
	}
}
