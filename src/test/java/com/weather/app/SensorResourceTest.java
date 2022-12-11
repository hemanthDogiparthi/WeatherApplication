package com.weather.app;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.sensor.WeatherApplication;
import com.sensor.dto.SensorDataModel;
import com.sensor.dto.SensorModel;
import com.sensor.entity.SensorData;
import com.sensor.repository.SensorDataRepository;
import com.sensor.repository.SensorRepository;
import com.sensor.service.SensorService;
import com.sensor.util.InputValidator;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.util.ClassUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
		 classes = WeatherApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application.properties")
public class SensorResourceTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	SensorService studioService;
	
	@Autowired
	InputValidator studioInputValidator;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	SensorDataRepository sensorDataRepo;
	
	@Autowired
	SensorRepository sensorRepo;
	
	
	
	@AfterTestMethod
    public void cleanup() {
		sensorDataRepo.deleteAll();
		sensorRepo.deleteAll();
    }
	
	
	@Test
    public void givenSensorCreated_thenStatusis200() throws Exception {
        //given+
		
		List<SensorModel> sModelList = createSensorModel();
		
        this.mockMvc.perform(post("/Sensor").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sModelList)));
		
		
        this.mockMvc.perform(get("/Sensor").contentType("application/json")).andDo(print()).andExpect(status().isOk());
        
        cleanup();
	 }
	
	@Test
    public void givenSensorCreatedWithInsufficientParams_thenBadRequest() throws Exception {
		SensorModel sModel = new SensorModel();
		sModel.setId(1);
		sModel.setName("Sensor 1");
		List<SensorModel> sModelList = new ArrayList<>();
		sModelList.add(sModel);
		
        this.mockMvc.perform(post("/Sensor").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sModelList)))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("missing")));
        
        cleanup();
	 }
	
	
	@Test
    public void givenSensorDataWithNoSensor_thenResourceNotFound() throws Exception {
        //given
		SensorDataModel sDataModel = createSensorData();
        
        this.mockMvc.perform(post("/Sensor/SensorData").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sDataModel)))
        .andExpect(status().isNotFound())	
        .andExpect(content().string(containsString("No")));
        
        cleanup();
    }
	
	@Test
    public void givenSensorDataWithData_thenSuccess() throws Exception {
        //given
			List<SensorModel> sModelList = createSensorModel();
			this.mockMvc.perform(post("/Sensor").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sModelList)));
			SensorDataModel sDataModel = createSensorData();
        
			//when and then
        this.mockMvc.perform(post("/Sensor/SensorData").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sDataModel)))
        .andExpect(status().isOk());
        
        
        cleanup();
    }
	
	@Test
    public void givenSensorDataWhenFetchLatestDateRange_thenSuccess() throws Exception {
       
		//given
		List<SensorModel> sModelList = createSensorModel();
		this.mockMvc.perform(post("/Sensor").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sModelList)));
		SensorDataModel sDataModel = createSensorData();
		
		String responseString =
				this.mockMvc.perform(get("/Sensor").contentType(MediaType.APPLICATION_JSON)).
				andReturn().getResponse().getContentAsString();
		
		System.out.print("hemanth " + responseString);
		sDataModel.setSensor_id(JsonUtils.toModelJson(responseString).get(0).getId());
		
		this.mockMvc.perform(post("/Sensor/SensorData").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sDataModel)))
			.andExpect(status().isOk());
		
		//when and then
        this.mockMvc.perform(get("/Sensor/"+sDataModel.getSensor_id()+"/SensorData"+"?To="+LocalDate.now().toString()+"&From="+LocalDate.now().minusDays(7)))
        		.andDo(print())
        .andExpect(status().isOk())	
        .andExpect(content().string(containsString("49.62")));
        
        cleanup();
    }
	
	
	@Test
    public void givenSensorDataWhenFetchLatestDays_thenSuccess() throws Exception {
        
		//given
		List<SensorModel> sModelList = createSensorModel();
		this.mockMvc.perform(post("/Sensor").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sModelList)));
		SensorDataModel sDataModel = createSensorData();
		
		String responseString =
				this.mockMvc.perform(get("/Sensor").contentType(MediaType.APPLICATION_JSON)).
				andReturn().getResponse().getContentAsString();
		
		sDataModel.setSensor_id(JsonUtils.toModelJson(responseString).get(0).getId());
		
		this.mockMvc.perform(post("/Sensor/SensorData").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sDataModel)))
			.andExpect(status().isOk());
		
		System.out.print("hemanth " + responseString);
		
		//when and then
        this.mockMvc.perform(get("/Sensor/"+sDataModel.getSensor_id()+"/SensorData"+"?Days=7"))
        		.andDo(print())
        .andExpect(status().isOk())	
        .andExpect(content().string(containsString("49.62")));
        
        cleanup();
    }
	
	@Test
    public void givenSensorDataWhenFetchLallData_thenSuccess() throws Exception {
        
		//given
		List<SensorModel> sModelList = createSensorModel();
		this.mockMvc.perform(post("/Sensor").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sModelList)));
		SensorDataModel sDataModel = createSensorData();
		
		String responseString =
				this.mockMvc.perform(get("/Sensor").contentType(MediaType.APPLICATION_JSON)).
				andReturn().getResponse().getContentAsString();
		
		sDataModel.setSensor_id(JsonUtils.toModelJson(responseString).get(0).getId());
		
		System.out.print("hemanth " + responseString);
		
		this.mockMvc.perform(post("/Sensor/SensorData").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(sDataModel)))
			.andExpect(status().isOk());
		
		//when and then
        this.mockMvc.perform(get("/Sensor/"+sDataModel.getSensor_id()+"/SensorData"))
        		.andDo(print())
        .andExpect(status().isOk())	
        .andExpect(content().string(containsString("49.62")));
        
        cleanup();
    }
	
	
	@Test
    public void givenSensorDataWhenFetchDataIncompleteURL_thenBadRequest() throws Exception {
        
        this.mockMvc.perform(get("/Sensor/1/SensorData?To="+LocalDate.now().toString()))
        		.andDo(print())
        		.andExpect(status().isBadRequest())
    			.andExpect(content().string(containsString("Ambiguous Query Parameter")));
        
        cleanup();
    }
	
	@Test
    public void givenSensorDataWhenFetchDataExcessURL_thenBadRequest() throws Exception {
		
       this.mockMvc.perform(get("/Sensor/1/SensorData?To="+LocalDate.now().toString()+"&From="+LocalDate.now().minusDays(7)+"&days=7"))
      		.andDo(print())
  		.andExpect(status().isBadRequest())
   			.andExpect(content().string(containsString("either")));
        
        cleanup();
    }
	
	
	private List<SensorModel> createSensorModel() {
		List<SensorModel> sModelList = new ArrayList<>();
			SensorModel sModel = new SensorModel();
			sModel.setName("Sensor 1");
			sModel.setCountry("Ireland");
			sModel.setCity("Athlone");
			sModelList.add(sModel);
        return sModelList;
	}
	
	private SensorDataModel createSensorData() {
		SensorDataModel sensorData = new SensorDataModel();
		sensorData.setHumidity(49.62);
		sensorData.setTemp(98.2);
		sensorData.setWindSpeed(11.0);
		sensorData.setSensor_id(1);
        return sensorData;
	}
	
	
}


