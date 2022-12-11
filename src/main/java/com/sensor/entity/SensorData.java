package com.sensor.entity;

import java.time.LocalDateTime;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;


/**
 * @author hdogiparthi
 *
 */
@Entity
public class SensorData {

	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private Double  temp;
	private Double  humidity;
	private Double  windSpeed;
	private LocalDateTime time;
	
	public SensorData() {
	}
	
	public SensorData(Double temp, Double humidity, Double windSpeed, LocalDateTime time, Sensor sensor) {
		super();
		this.temp = temp;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.time = time;
		this.sensor = sensor;
	}
	
	@ManyToOne
	@JoinColumn(name="sensorId")
	private Sensor sensor;
	   
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
