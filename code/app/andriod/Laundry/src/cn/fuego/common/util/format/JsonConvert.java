/**   
* @Title: JsonConvert.java 
* @Package cn.fuego.common.util.format 
* @Description: TODO
* @author Tang Jun   
* @date 2014-12-4 上午12:51:02 
* @version V1.0   
*/ 
package cn.fuego.common.util.format;

import java.io.IOException;

import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.fuego.common.log.FuegoLog;

 /** 
 * @ClassName: JsonConvert 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-12-4 上午12:51:02 
 *  
 */
public class JsonConvert
{
	private static final FuegoLog log = FuegoLog.getLog(DataTypeConvert.class);

	public static String ObjectToJson(Object object)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		 String json = "";
		try
		{
			json = mapper.writeValueAsString(object);
		} catch (Exception e)
		{
			log.error("object to json failed",e);
		}
		return json;
	}
	
	public static Object jsonToObject(String json,Class clazz)
	{
		ObjectMapper mapper = new ObjectMapper();
		 
		Object	rspObj = null;
		try
		{
			rspObj = mapper.readValue(json,clazz);
		} catch (Exception e)
		{
			log.error("json to object failed",e);
		}
		return rspObj;
	}
}
