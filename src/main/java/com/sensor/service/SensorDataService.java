package com.sensor.service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensor.exceptions.MissingDataException;
import com.sensor.projection.SensorDataView;
import com.sensor.repository.SensorDataRepository;
import com.sensor.repository.SensorRepository;
import com.sensor.dto.SensorDataModel;
import com.sensor.component.SensorDataQuery;
import com.sensor.entity.Sensor;
import com.sensor.entity.SensorData;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hdogiparthi
 *
 */
@Service
public class SensorDataService {
	
	
	@Autowired
    SensorDataRepository sensorDataRepository;
	
	@Autowired
    SensorRepository sensorRepository;
	
    public void save(SensorDataModel SensorDataModel) {
    	SensorData sensorData = prepareEntity(SensorDataModel);
    	sensorDataRepository.save(sensorData);	
    }
    
    public SensorDataView fetchAverageValues(SensorDataQuery sensorDataQuery) {
    	checkSensorId(sensorDataQuery.getSensor_id());
    	return sensorDataQuery.fetchDetails();
    }
    
    public List<SensorDataModel> getAllSensors() {
		// TODO Auto-generated method stub
		 return prepareJson((List<SensorData>)sensorDataRepository.findAll());
	}
	
    
    private SensorData prepareEntity(SensorDataModel sensorDataModel) {
    	
			return new SensorData(sensorDataModel.getTemp(), 
    								sensorDataModel.getHumidity(),
    								sensorDataModel.getWindSpeed(),
    								LocalDateTime.now(Clock.systemUTC()), 
    								checkSensorId(sensorDataModel.getSensor_id()) );
		}
	
	private Sensor checkSensorId(int id) {
		Optional<Sensor> sensor = sensorRepository.findById(id);		
		
		return sensor.orElseThrow(() ->
		new MissingDataException(
				"No sensor present with the ID ".concat(
			String.valueOf(id))
			));
	}
	
	private List<SensorDataModel> prepareJson(List<SensorData> sensors) {
		// TODO Auto-generated method stub
				
		return sensors.stream()
                .map(sensor -> new SensorDataModel(sensor.getSensor().getId(), sensor.getTemp(), sensor.getHumidity(), sensor.getWindSpeed() , sensor.getTime()))
                .collect(Collectors.toList());
		
	}

}
