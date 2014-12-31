/**   
* @Title: ReflectionUtil.java 
* @Package cn.fuego.util 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-24 下午06:16:47 
* @version V1.0   
*/ 
package cn.fuego.common.util.meta;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.format.DataTypeConvert;

/** 
 * @ClassName: ReflectionUtil 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-24 下午06:16:47 
 *  
 */

public class ReflectionUtil
{
	private static final FuegoLog log = FuegoLog.getLog(ReflectionUtil.class);

	public static Class getTypeByFieldName(Class clazz,String fieldName) throws NoSuchFieldException, SecurityException
	{
		Class filedClass = clazz.getDeclaredField(fieldName).getType();
		
		return filedClass;
	}
 
 
	public static Object convertToFieldObject(Class clazz,String fieldName,Object value)  
	{
		Object object = null;
		
		try
		{
			Class fieldClass = clazz.getDeclaredField(fieldName).getType();
			if(!value.getClass().equals(fieldClass))
			{
				String strValue = value.toString();
				object = DataTypeConvert.convertStrToObject(strValue, fieldClass);	
			}
			else
			{
				object = value;
			}
			

		}
		catch(Exception e)
		{
			log.error("can not convert to right type.the class is " + clazz + " the field name is " + fieldName + "the value is " + value);
			log.error("convert data failed",e);
			throw new RuntimeException(e);
		}
		
	 
		
		return object;
	}
	
    public static void setObjetField(Object obj, String fieldName, String value)
    {
    	try
    	{
        	Class type = obj.getClass().getField(fieldName).getType();
        	String methodName  =fieldName.replaceFirst(fieldName.substring(0, 1),fieldName.substring(0, 1).toUpperCase())  ; 
        	Method setter =  obj.getClass().getMethod(methodName, type);
        	Object valueObject = convertToFieldObject(obj.getClass(),fieldName,value);
        	setter.invoke(obj, valueObject);
         
    	}
    	catch(Exception e)
    	{
    		log.error("set value failed. the class is " + obj.getClass().toString() + "the filedName is " + fieldName + "the value is " +value);
    		throw new RuntimeException(e);
    	}

 
    }
    public static Class<Object> getSuperClassGenricType(final Class clazz)
    {
    	return getSuperClassGenricType(clazz,0);
    }
    public static Class<Object> getSuperClassGenricType(final Class clazz,int index)
	{

		// 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType))
		{
			log.error("can not get the class");
			return Object.class;
		}
		// 返回表示此类型实际类型参数的 Type 对象的数组。
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0)
		{
			log.error("can not get the class");

			return Object.class;
		}
		if (!(params[index] instanceof Class))
		{
			log.error("can not get the class");
			return Object.class;
		}

		return (Class) params[index];
	}

}
