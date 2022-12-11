package com.sensor.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sensor.component.*;
import com.sensor.dto.SensorDataModel;
import com.sensor.dto.SensorModel;
import com.sensor.exceptions.InvalidRequestException;

@Component
public class InputValidator {

	@Autowired
	SensorDataRangeCalculator dateRangeCalculator;
	
	@Autowired
	SensorDaysCalculator daysCalculator;
	
	@Autowired
	SensorDataQuery sensorDataQuery;
	
	public void validateInput(List<SensorModel> sensors)  {
		// TODO Auto-generated method stub
		for(SensorModel sensor: sensors) {
			if(StringUtils.isEmpty(sensor.getCity()) ||
					StringUtils.isEmpty(sensor.getName()) ||
							StringUtils.isEmpty(sensor.getCountry())   ) {
				throw new InvalidRequestException("Sensor Request missing mandatory fields");
			}
		}	
	}
	
	
	public SensorDataQuery validateInput(int sensorId, String fetchDateTimeFrom, String fetchDateTimeTo, Integer days)  {
		// TODO Auto-generated method stub
		if((fetchDateTimeFrom !=null && fetchDateTimeTo !=null)) {
			if(days !=null) {
				throw new InvalidRequestException("Ambiguous Query Parameter. Remove either Days or Date Range");
			}
			return buildDateRangeQueryObject(sensorId, fetchDateTimeFrom, fetchDateTimeTo);
		}
		
				
		if((fetchDateTimeFrom ==null && fetchDateTimeTo == null)) {
			if(days == null) {
				return buildQueryObject(sensorId);
			}
			return buildDaysQueryObject(sensorId, days);
		}
		
		throw new InvalidRequestException("Ambiguous Query Parameter. Enter Date Range From and To");
	}
	
	public SensorDataQuery buildDaysQueryObject(int sensorId, Integer days)  {
		daysCalculator.setSensor_id(sensorId);
		daysCalculator.setDays(days);
		return daysCalculator;
	}	
	
	public SensorDataQuery buildDateRangeQueryObject(int sensorId, String from , String to)  {
		dateRangeCalculator.setSensor_id(sensorId);
		dateRangeCalculator.setFrom(from);
		dateRangeCalculator.setTo(to);
		return dateRangeCalculator;
	}
	
	public SensorDataQuery buildQueryObject(int sensorId)  {
		sensorDataQuery.setSensor_id(sensorId);
		return sensorDataQuery;
	}
	
	
	public void validateInput(SensorDataModel sensorData)  {
		// TODO Auto-generated method stub
			if(StringUtils.isEmpty(String.valueOf(sensorData.getSensor_id())) ) {
				throw new InvalidRequestException("Sensor Data Request missing mandatory fields");
			}
		}	
	}

