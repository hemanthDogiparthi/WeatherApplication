package com.sensor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.dto.SensorModel;
import com.sensor.service.SensorService;
import com.sensor.util.InputValidator;

/**
 * @author hdogiparthi
 *
 */
@RestController
public class SensorRegistrationController {

	@Autowired
	InputValidator SensorModelValidator;
	
	@Autowired
	SensorService sensorService;
	
	/**
	 * @param sensors
	 * REST API to register sensors
	 */
	@RequestMapping(method = RequestMethod.POST, value="Sensor") 
	  public void registerSensors(@RequestBody List<SensorModel> sensors) {
			  SensorModelValidator.validateInput(sensors);
			  sensorService.save(sensors);
	  }
	
	/**
	 * @return
	 * REST API to retrieve all sensor Information
	 */
	@RequestMapping(method = RequestMethod.GET, value="Sensor")  
	  public List<SensorModel> retrieveAllSensors() { 
		    return sensorService.getAllSensors();
		}
}
