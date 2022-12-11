package com.sensor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.component.SensorDataQuery;
import com.sensor.dto.SensorDataModel;
import com.sensor.dto.SensorModel;
import com.sensor.entity.SensorData;
import com.sensor.projection.SensorDataView;
import com.sensor.service.SensorDataService;
import com.sensor.service.SensorService;
import com.sensor.util.InputValidator;

/**
 * @author hdogiparthi
 *
 */
@RestController
public class SensorDataController {

	@Autowired
	InputValidator sensorModelValidator;

	@Autowired
	SensorDataService sensorDataService;

	/**
	 * @param sensorData
	 * REST API for Sending in Sensor Data
	 */
	@RequestMapping(method = RequestMethod.POST, value = "Sensor/SensorData")
	public void registerSensors(@RequestBody SensorDataModel sensorData) {
		sensorModelValidator.validateInput(sensorData);
		sensorDataService.save(sensorData);
	}
	
	/**
	 * @return
	 * REST API for returning all details of sensors registered
	 */
	@RequestMapping(method = RequestMethod.GET, value = "Sensor/SensorData")
	public List<SensorDataModel> retrieveAllSensors() {
		return sensorDataService.getAllSensors();
	}

	
	/**
	 * @param id
	 * @param fetchDateTimeFrom
	 * @param fetchDateTimeTo
	 * @param days
	 * @return
	 * REST API to fetch custom sensor information based on Days or Date-Range
	 */
	@RequestMapping(method = RequestMethod.GET, value = "Sensor/{id}/SensorData")
	 public ResponseEntity<SensorDataView> fetchWeatherSensors(
			 	@PathVariable int id,
	            @RequestParam(value = "From", required = false) String fetchDateTimeFrom,
	            @RequestParam(value = "To", required = false) String fetchDateTimeTo,
	            @RequestParam(value = "days", required = false) Integer days){
		
		
	 SensorDataQuery dataQuery = sensorModelValidator.validateInput(id, 
													fetchDateTimeFrom,
													fetchDateTimeTo, days);
		
	 return ResponseEntity.ok().body(sensorDataService.fetchAverageValues(dataQuery));
	}
	
}
