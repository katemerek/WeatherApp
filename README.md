# Weather App 
## Description
Rest service for recording measurements from a meteorological sensor.
This project is made for educational purposes with Spring Boot. In the repository there is a rest client (RestClientForWeatherApp) for this Api.

### Steps to run the application
- Import this project as a Maven project.
- Run the [scriptSensor.sql and scriptMeasurement.sql]  (src/main/resources) in the database to create sensor and measurement tables.
- Add a new sensor to table sensor from POST request. Sensor name must be unique.
- Add a new measurement to table measurement: value temperature,  (The sensor field must match one of the sensor names registered in the sensor table)

Test the API with client tool such as special app (RestClientForWeatherApp) or Postman to perform various operations.
The app will start running at <http://localhost:8080/api/v1>.
#### Measurement parameters
- air temperature (value, double),
- is it raining outside (raining),
- name of the sensor that recorded the measurement (sensor)

### REST APIs
- GET /sensors
- POST /sensors/registration
- GET /measurements
- POST /measurements/add
- GET /measurements/rainyDaysCount

This sentence for review.