package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TurnType implements Serializable{
	private String name;
	private double time;
	/**
	 * @param name
	 * @param time
	 */
	public TurnType(String name, double time) {
		this.name = name;
		this.time = time;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(double time) {
		this.time = time;
	}
	
	
}
