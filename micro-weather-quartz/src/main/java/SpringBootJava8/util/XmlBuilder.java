package SpringBootJava8.util;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class XmlBuilder {

	@SuppressWarnings("unchecked")
	public static <T> T xmlStrToObject(Class<T> clazz,String xmlStr) throws Exception{
		T xmlObjec = null;
		
		Reader reader = null;
		
		// xml 转为对象的接口
		JAXBContext context = JAXBContext.newInstance(clazz);
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		reader  = new StringReader(xmlStr);
		
		xmlObjec = (T) unmarshaller.unmarshal(reader);
		if(reader!=null) {
			reader.close();
		}
		
		return xmlObjec;
	}
}
