package com.sensor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensor.dto.SensorModel;
import com.sensor.entity.Sensor;
import com.sensor.repository.SensorRepository;

/**
 * @author hdogiparthi
 *
 */
@Service
public class SensorService {
	
	
	@Autowired
    SensorRepository sensorRepository;

    public void save(List<SensorModel> SensorModels) {
    	List<Sensor> sensorEntities = prepareEntity(SensorModels);
    	sensorEntities.forEach(s -> sensorRepository.save(s));
    }
    
	private List<Sensor> prepareEntity(List<SensorModel> sensorModels) {
		// TODO Auto-generated method stub
		
		List<Sensor> sensorList = sensorModels.stream()
                .map(sensorModel -> new Sensor(sensorModel.getCountry(), sensorModel.getCity(), sensorModel.getName()))
                .collect(Collectors.toList());
		
		return sensorList;
	}
	
	private List<SensorModel> prepareJson(List<Sensor> sensorEntities) {
		// TODO Auto-generated method stub
		
		List<SensorModel> sensorList = sensorEntities.stream()
                .map(sensorEntity -> new SensorModel(sensorEntity.getId(),sensorEntity.getCountry(), sensorEntity.getCity(), sensorEntity.getName()))
                .collect(Collectors.toList());
		
		return sensorList;
	}

	public List<SensorModel> getAllSensors() {
		// TODO Auto-generated method stub
		return prepareJson((List<Sensor>)sensorRepository.findAll());
	}
}
