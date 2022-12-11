package com.sensor.repository;

import org.springframework.data.repository.CrudRepository;

import com.sensor.entity.Sensor;

/**
 * @author hdogiparthi
 *
 */
public interface SensorRepository extends CrudRepository<Sensor, Integer> {

}
