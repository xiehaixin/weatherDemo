package SpringBootJava8.server.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import SpringBootJava8.server.CityDataService;
import SpringBootJava8.util.XmlBuilder;
import SpringBootJava8.vo.City;
import SpringBootJava8.vo.CityList;

@Service
public class CityDataServiceImpl implements CityDataService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<City> listCity() throws Exception {
		// 读取XML文件
		Resource resource = new ClassPathResource("citylist.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(),"utf-8"));
		StringBuffer stringBuffer = new StringBuffer();
		String line = "";
		while ((line = br.readLine())!=null) {
			stringBuffer.append(line);
		}
		
		br.close();
		
		logger.info(stringBuffer.toString());
		
		CityList cityList = XmlBuilder.xmlStrToObject(CityList.class, stringBuffer.toString());
		
		
		
		return cityList.getCity();
	}

}
