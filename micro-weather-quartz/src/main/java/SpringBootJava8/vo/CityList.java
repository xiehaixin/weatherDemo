package SpringBootJava8.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="c")
//访问类型			XmlAccessType.FIELD 字段
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {

	
	@XmlElement(name="d")
	private List<City> city;

	public List<City> getCity() {
		return city;
	}

	public void setCity(List<City> city) {
		this.city = city;
	}
	
	
	
}
