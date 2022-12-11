package com.sensor.projection;


/**
 * @author hdogiparthi
 *
 */
public class SensorDataView {

	private int sensorId;
	private Double  temp;
	private Double  humidity;
	private Double  windSpeed;
	
	public SensorDataView(int sensorId, Double temp, Double humidity, Double windSpeed) {
		super();
		this.sensorId = sensorId;
		this.temp = temp;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
	}
	
	
	public int getSensorId() {
		return sensorId;
	}


	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
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
	
	
   
}
