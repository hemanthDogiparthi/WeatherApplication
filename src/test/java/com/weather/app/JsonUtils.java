package com.weather.app;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sensor.dto.SensorModel;

public class JsonUtils {
    static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsBytes(object);
    }
    
    static List<SensorModel>  toModelJson(String content) throws IOException {
    	 ObjectMapper mapper = new ObjectMapper();
    	 List<SensorModel> sensorModel = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, SensorModel.class));
		return sensorModel;
    }
   
}