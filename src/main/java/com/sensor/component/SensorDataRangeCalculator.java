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
public class SensorDataRangeCalculator extends SensorDataQuery {
	
	@Autowired
    SensorDataRepository sensorDataRepository;
	
	private String to;
	private String from;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	@Override
	public SensorDataView fetchDetails() {
		// TODO Auto-generated method stub
		LocalDateTime to = LocalDate.parse(this.to).atTime(LocalTime.MAX);
		LocalDateTime from = LocalDate.parse(this.from).atStartOfDay();
		
		return sensorDataRepository.findSensorDataBySensorIdDateRange(super.getSensor_id(),to,from);	
	}
	   
}
