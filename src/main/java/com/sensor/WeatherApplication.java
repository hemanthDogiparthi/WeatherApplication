package com.sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author hdogiparthi
 *
 */
@SpringBootApplication
@EnableConfigServer 
public class WeatherApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

}
