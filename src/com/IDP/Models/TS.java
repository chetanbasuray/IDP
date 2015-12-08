package com.IDP.Models;

import java.util.List;

public class TS {
	private String task;
	private List<String> units;
	private List<Double> timePoints;
	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}
	/**
	 * @return the units
	 */
	public List<String> getUnits() {
		return units;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(List<String> units) {
		this.units = units;
	}
	/**
	 * @return the timePoints
	 */
	public List<Double> getTimePoints() {
		return timePoints;
	}
	/**
	 * @param timePoints the timePoints to set
	 */
	public void setTimePoints(List<Double> timePoints) {
		this.timePoints = timePoints;
	}
}