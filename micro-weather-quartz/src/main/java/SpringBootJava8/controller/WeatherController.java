package SpringBootJava8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringBootJava8.server.WeatherServer;
import SpringBootJava8.vo.WeatherResponse;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Autowired
	private WeatherServer weatherServer;
	
	@RequestMapping("/citykey/{citykey}")
	public WeatherResponse getWeatherByCityKey(@PathVariable("citykey") String citykey) {
		return weatherServer.getWeatherByCityId(citykey);
	}
	
	@RequestMapping("/cityName/{cityName}")
	public WeatherResponse getWeatherBycityName(@PathVariable("cityName") String cityName) {
		return weatherServer.getWeatherByCityName(cityName);
	}

}
