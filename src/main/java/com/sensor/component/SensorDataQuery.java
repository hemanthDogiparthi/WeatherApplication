package com.sensor.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sensor.projection.SensorDataView;
import com.sensor.repository.SensorDataRepository;

/**
 * @author hdogiparthi
 *
 */
@Component
public class SensorDataQuery {
	   
	private int sensor_id;
	
	@Autowired
	private SensorDataRepository sensorDataRepository;

	public int getSensor_id() {
		return sensor_id;
	}

	public void setSensor_id(int sensor_id) {
		this.sensor_id = sensor_id;
	}
	
	public SensorDataView  fetchDetails() {
		return sensorDataRepository.findSensorDataBySensorId(this.sensor_id);
	}
	
	
}
