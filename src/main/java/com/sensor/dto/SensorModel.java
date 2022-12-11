package com.sensor.dto;

/**
 * @author hdogiparthi
 *
 */
public class SensorModel {

	private int sensorId;
	
	private String country;
	private String city;
	
	private String name;
	
	
	public SensorModel() {
	}
	
	public SensorModel(int sensorId, String country, String city, String name) {
		super();
		this.sensorId = sensorId;
		this.country = country;
		this.city = city;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return sensorId;
	}
	public void setId(int sensorId) {
		this.sensorId = sensorId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
