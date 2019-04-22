package SpringBootJava8.server;

import java.util.List;

import SpringBootJava8.vo.City;

public interface CityDataService {

	/**
	 * 获取City列表
	 * @return
	 * @throws Exception
	 */
	List<City> listCity()throws Exception;
}
