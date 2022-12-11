package com.sensor.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sensor.entity.Sensor;
import com.sensor.entity.SensorData;
import com.sensor.projection.SensorDataView;

/**
 * @author hdogiparthi
 *
 */
public interface SensorDataRepository extends CrudRepository<SensorData, Integer> {

	
	
	
	
	  @Query("select avg(u.temp), avg(u.humidity), avg(u.windSpeed) from SensorData u join"
	  		+ " u.sensor s where s.sensorId = :id")
	  SensorDataView findSensorDataBySensorId1(@Param("id") int id);
	  
	  
	  @Query("select u from SensorData u join"
		  		+ " u.sensor s where s.sensorId = :id")
		  List<SensorData> findSensorDataBySensorId2(@Param("id") int id);
	  
	  @Query("SELECT new com.sensor.projection.SensorDataView(s.sensorId, avg(u.temp), avg(u.humidity), avg(u.windSpeed)) "
	  		+ "from SensorData u "
	  		+ "join u.sensor s where s.sensorId = :id")
	 SensorDataView findSensorDataBySensorId(@Param("id") int id);
	  
	   
	  @Query("SELECT new com.sensor.projection.SensorDataView(s.sensorId, avg(u.temp), avg(u.humidity), avg(u.windSpeed)) "
		  		+ "from SensorData u "
		  		+ "join u.sensor s where s.sensorId = :id"
		  		+ " and time BETWEEN :from AND :to ")
		  SensorDataView findSensorDataBySensorIdDateRange(@Param("id") int id, LocalDateTime to, LocalDateTime from);
	  
}
