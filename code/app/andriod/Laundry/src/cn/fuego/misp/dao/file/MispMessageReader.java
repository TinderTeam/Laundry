/**
 * TODO
 * 上午01:07:45
 */
package cn.fuego.misp.dao.file;

import java.io.InputStream;
import java.util.Properties;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;

/**
 * @author Administrator
 * 
 */
public class MispMessageReader
{

    private static final FuegoLog log = FuegoLog.getLog(MispMessageReader.class);
 
    private static final String MISP_MSG_PATH = "/assets/mispMessage_en_US.properties";
    private static final String ERROR_MSG_PATH = "/assets/errorMessage_en_US.properties";

    private static MispMessageReader instance;
    private Properties mispProp;
    private Properties prop;

    private MispMessageReader()
    {

        try
        {
            /* 而采用类加载器的话，能够更具有通用性 */
            /* 使用文件的读写的方式，文件的路径的相对路径确定了，不能修改 */
        	mispProp = new Properties();
            InputStream inStream = MispMessageReader.class
                    .getResourceAsStream(MISP_MSG_PATH);
            mispProp.load(inStream);
            
            log.info(mispProp.toString());
            
            /* 而采用类加载器的话，能够更具有通用性 */
            /* 使用文件的读写的方式，文件的路径的相对路径确定了，不能修改 */
            prop = new Properties();
 
            prop.load(MispMessageReader.class.getResourceAsStream(ERROR_MSG_PATH));
            
            log.info(prop.toString());

        } catch (Exception e)
        {
            log.error("load misp message failed",e);
        }
    }
    
	public static synchronized MispMessageReader getInstance()
	{
		if (null == instance)
		{
			instance = new MispMessageReader();
		}
		return instance;
	}

    public String getPropertyByName(String name)
    {
    	String message = name;
    	if(null == mispProp)
    	{
    	    log.warn("the misp property  is null");	
    	}
    	else
    	{
    		message = mispProp.getProperty(name);
        	if(!ValidatorUtil.isEmpty(message))
        	{
        		return message;
        	}
        	else
        	{
        		log.warn("can not get message by error code " + name);
        	}
    	}
    	
    	if(null == prop)
    	{
    	    log.warn("the property  is null");	
    	}
    	else
    	{
        	 message = prop.getProperty(name);
        	if(ValidatorUtil.isEmpty(message))
        	{
        		log.warn("can not get the value by name. name is " + name);
        		return name;
        	}
    	}
        return message;
    }

    
 

}
