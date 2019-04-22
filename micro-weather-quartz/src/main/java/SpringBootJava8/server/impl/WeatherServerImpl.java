package SpringBootJava8.server.impl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import SpringBootJava8.server.WeatherServer;
import SpringBootJava8.util.WeatherUrl;
import SpringBootJava8.vo.WeatherResponse;

@Service
public class WeatherServerImpl implements WeatherServer {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 缓存超时时间 单位：秒
	private static final long OUT_TIME_SECONDS = 10L;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public WeatherResponse getWeatherByCityId(String citykey) {
		String uri = WeatherUrl.WEATHER_URI+"?citykey="+citykey;
		return getWeatherByUri(uri);
	}

	@Override
	public WeatherResponse getWeatherByCityName(String cityName) {
		// TODO Auto-generated method stub
		String uri = WeatherUrl.WEATHER_URI+"?city="+cityName;
		return getWeatherByUri(uri);
	}
	
	private WeatherResponse getWeatherByUri(String uri) {
		WeatherResponse weatherResponse= null;
		String body = null;
		ObjectMapper mapper = new ObjectMapper();
		
		// 获取reids缓存值的集合
		ValueOperations<String, String> values = stringRedisTemplate.opsForValue();
		
		// 查看redis key 的 值 是否有数据，
		if(stringRedisTemplate.hasKey(uri)) {
			logger.info("有缓存");
			
			body = values.get(uri);
			
		}else {
			logger.info("没有缓存");
			// 如果没有数据，再访问第三方数据接口，然后把数据缓存进redis
			ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
			
			if(forEntity.getStatusCodeValue()==200) {
				body = forEntity.getBody();
				values.set(uri, body, OUT_TIME_SECONDS, TimeUnit.SECONDS);
			}
			
			
		}
		
		try {
			weatherResponse = mapper.readValue(body, WeatherResponse.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.error("错误",e);
		}
		
		return weatherResponse;
	}

	@Override
	public void syncDataByCityKey(String cityKey) {
		
		String uri = WeatherUrl.WEATHER_URI+"?citykey="+cityKey;
		
		logger.info(uri);
		
		saveWeatherData(uri);
	}
	
	private void saveWeatherData(String uri) {
		String body = null;
		
		// 获取reids缓存值的集合
		ValueOperations<String, String> values = stringRedisTemplate.opsForValue();
		
		// 如果没有数据，再访问第三方数据接口，然后把数据缓存进redis
		ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
		
		if(forEntity.getStatusCodeValue()==200) {
			body = forEntity.getBody();
			values.set(uri, body, OUT_TIME_SECONDS, TimeUnit.SECONDS);
		}
			
			
	}

}
