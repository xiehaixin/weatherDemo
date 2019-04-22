package SpringBootJava8.server;

import SpringBootJava8.vo.WeatherResponse;

public interface WeatherServer {

	WeatherResponse getWeatherByCityId(String citykey);
	
	WeatherResponse getWeatherByCityName(String cityName);
	
	void syncDataByCityKey(String cityKey);
}
