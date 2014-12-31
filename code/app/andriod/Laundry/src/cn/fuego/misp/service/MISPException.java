/**   
* @Title: MISPException.java 
* @Package cn.fuego.misp.service 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午9:15:06 
* @version V1.0   
*/ 
package cn.fuego.misp.service;

import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.dao.file.MispMessageReader;

 /** 
 * @ClassName: MISPException 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午9:15:06 
 *  
 */
public class MISPException extends RuntimeException
{
	/**
	 * 
	 */
	private int errorCode = MISPErrorMessageConst.SUCCESS;

	public MISPException()
	{
		super();
	}
	
	public MISPException(int errorCode)
	{
		super(String.valueOf(errorCode));

		this.errorCode = errorCode;
	}
	
	public MISPException(int errorCode,Throwable cause)
	{
		super(String.valueOf(errorCode),cause);

		this.errorCode = errorCode;
	}

	public MISPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		// super(message, cause, enableSuppression, writableStackTrace);
	}

	public MISPException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public MISPException(String message)
	{
		super(message);
	}

	public MISPException(Throwable cause)
	{
		super(cause);
	}

	
	public int getErrorCode()
	{
		return errorCode;
	}
	
	@Override
	public String getMessage()
	{
		
		return  MispMessageReader.getInstance().getPropertyByName(super.getMessage());
	}

	@Override
	public String toString()
	{
		String message = getLocalizedMessage();
		return (message != null) ? (message) : "";

	}
}
