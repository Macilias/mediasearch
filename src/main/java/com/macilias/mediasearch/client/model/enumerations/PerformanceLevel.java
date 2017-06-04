package com.macilias.mediasearch.client.model.enumerations;

import org.imgscalr.Scalr;

public enum PerformanceLevel {

	VERY_HIGH_PERFORMANCE_LEVEL("Deep Thought ist ein Taschenrechner gegen meinen.", 128, Resolution.ORIGINAL, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT),
	HIGH_PERFORMANCE_LEVEL("Der Rechner ist nagel neu.", 64, Resolution.AUTO_MAX, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_HEIGHT),
	NORMAL_PERFORMANCE_LEVEL("Der Rechner ist ganz ok.", 32, Resolution.AUTO_DEF, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT),
	LOW_PERFORMANCE_LEVEL("Ich hoffe ich bekomme bald einen Neuen.", 24, Resolution.AUTO_MIN, Scalr.Method.BALANCED, Scalr.Mode.FIT_TO_HEIGHT),
	VERY_LOW_PERFORMACE_LEVEL("Jeder Taschenrechner ist schneller.", 16, Resolution.SVGA, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_HEIGHT);

	private String description;
	private int memory;
	private Resolution resolution;
	private Scalr.Method scalingMethod;
	private Scalr.Mode scalingMode;


	private PerformanceLevel(final String description, final int memory, final Resolution resolution, final Scalr.Method scalingMethod, final Scalr.Mode scalingMode) {
		this.description = description;
		this.memory = memory;
		this.resolution = resolution;
		this.scalingMethod = scalingMethod;
		this.scalingMode = scalingMode;
	}


	public int getMemory() {
		return memory;
	}


	public Resolution getResolution() {
		return resolution;
	}


	public Scalr.Method getScalingMethod() {
		return scalingMethod;
	}


	public Scalr.Mode getScalingMode() {
		return scalingMode;
	}


	@Override
	public String toString() {
		return description;
	}
}
