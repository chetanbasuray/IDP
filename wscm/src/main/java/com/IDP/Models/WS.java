/**
 * 
 */
package com.IDP.Models;

import java.util.List;

/**
 * @author JOY
 *
 */
public class WS {
	private String tasks;
	private List<Integer> timePoints;
	/**
	 * @return the tasks
	 */
	public String getTasks() {
		return tasks;
	}
	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(String tasks) {
		this.tasks = tasks;
	}
	/**
	 * @return the timePoints
	 */
	public List<Integer> getTimePoints() {
		return timePoints;
	}
	/**
	 * @param timePoints the timePoints to set
	 */
	public void setTimePoints(List<Integer> timePoints) {
		this.timePoints = timePoints;
	}
}