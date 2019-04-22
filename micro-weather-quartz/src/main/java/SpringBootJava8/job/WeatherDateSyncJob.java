package SpringBootJava8.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import SpringBootJava8.server.CityDataService;
import SpringBootJava8.server.WeatherServer;
import SpringBootJava8.vo.City;

public class WeatherDateSyncJob extends QuartzJobBean{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CityDataService cityDataService;
	
	@Autowired
	private WeatherServer weatherServer;
	
	@Override
	public void executeInternal(JobExecutionContext context) {
		List<City> listCity = null;
		try {
			listCity = cityDataService.listCity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("错误",e);
		}
		
		for (City city : listCity) {
			weatherServer.syncDataByCityKey(city.getCityId());
		}
	}

	
}
