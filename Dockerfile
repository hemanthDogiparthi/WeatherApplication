FROM openjdk:11
COPY target/WeatherApplication-1.0.jar WeatherApplication-1.0.jar
EXPOSE 8080
CMD ["java", "-jar", "WeatherApplication-1.0.jar"]