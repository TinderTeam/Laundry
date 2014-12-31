/**
 * TODO
 * 上午01:07:45
 */
package cn.fuego.common.util.file;

import java.io.InputStream;
import java.util.Properties;

import cn.fuego.common.log.FuegoLog;

/**
 * @author Administrator
 * 
 */
public class PropertyReader
{

    private static final FuegoLog log = FuegoLog.getLog(PropertyReader.class);
 
    private static final String CONFIG_PATH = "Config.properties";
    private static PropertyReader instance;
    private Properties prop;

    private PropertyReader()
    {

        try
        {
            /* 而采用类加载器的话，能够更具有通用性 */
            /* 使用文件的读写的方式，文件的路径的相对路径确定了，不能修改 */
            prop = new Properties();
            InputStream inStream = PropertyReader.class.getClassLoader()
                    .getResourceAsStream(CONFIG_PATH);
            prop.load(inStream);
            
            log.info(prop.toString());

        } catch (Exception e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }
    
	public static synchronized PropertyReader getInstance()
	{
		if (null == instance)
		{
			instance = new PropertyReader();
		}
		return instance;
	}

    public String getPropertyByName(String name)
    {
        return prop.getProperty(name);
    }

    
 

}
