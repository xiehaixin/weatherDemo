package SpringBootJava8.quartzconfiguration;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import SpringBootJava8.job.WeatherDateSyncJob;

@Configuration
public class QuartzConfiguration {

	@Bean
	public JobDetail weatherDataSyncJobDetail() {
		//  												自定义名称
		return JobBuilder.newJob(WeatherDateSyncJob.class).withIdentity("weather")
		.storeDurably().build();
	}
	
	/**
	 * Trigger 触发
	 * @return
	 */
	@Bean 
	public Trigger weatherDataSyncTrigger() {
		
		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
//					设置时间 秒数 轮询多少秒执行一次
				.withIntervalInSeconds(20).repeatForever();
		
		return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
				// 自定义名称
				.withIdentity("adsf").withSchedule(schedBuilder).build();
	}
	
}
