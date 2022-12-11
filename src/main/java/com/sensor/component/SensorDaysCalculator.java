package com.sensor.component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sensor.projection.SensorDataView;
import com.sensor.repository.SensorDataRepository;

/**
 * @author hdogiparthi
 *
 */
@Component
public class SensorDaysCalculator extends SensorDataQuery  {
	
	@Autowired
    SensorDataRepository sensorDataRepository;
	
	
	private int days;
	
	
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	@Override
	public SensorDataView fetchDetails() {
		// TODO Auto-generated method stub
		LocalDateTime	to = LocalDateTime.now();
		LocalDateTime	from = LocalDateTime.now().minusDays(days);
		return sensorDataRepository.findSensorDataBySensorIdDateRange(super.getSensor_id(),to,from);
	}
	
	   
}
