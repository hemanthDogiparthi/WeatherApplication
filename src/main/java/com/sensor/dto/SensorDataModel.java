package com.sensor.dto;

import java.time.LocalDateTime;

/**
 * @author hdogiparthi
 *
 */
public class SensorDataModel {


	private int id;
	private int sensor_id;
	private Double  temp;
	private Double  humidity;
	private Double  windSpeed;
	private LocalDateTime time;
	   
	public SensorDataModel() {
		super();
	}
	   
	public SensorDataModel(int sensor_id, Double temp, Double humidity, Double windSpeed, LocalDateTime time) {
		super();
		this.sensor_id = sensor_id;
		this.temp = temp;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.time = time;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSensor_id() {
		return sensor_id;
	}
	public void setSensor_id(int sensor_id) {
		this.sensor_id = sensor_id;
	}
	public Double  getTemp() {
		return temp;
	}
	public void setTemp(Double  temp) {
		this.temp = temp;
	}
	public Double  getHumidity() {
		return humidity;
	}
	public void setHumidity(Double  humidity) {
		this.humidity = humidity;
	}
	public Double  getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(Double  windSpeed) {
		this.windSpeed = windSpeed;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	   
	   
}
