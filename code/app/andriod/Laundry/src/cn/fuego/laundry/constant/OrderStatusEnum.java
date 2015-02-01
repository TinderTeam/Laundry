package cn.fuego.laundry.constant;

public enum OrderStatusEnum
{
	OrderSubmit("已下单",1),   //可以取消
	
	PaySuccess("付款成功",2),  //不能动
	WaitBuyerPay("待付款",3),  //可以付款

	PayFinished("交易完成",4),  //不管
	OrderComplete("已完成",5),  //可以删除
	OrderCancel("已取消",6),   //可以删除
	OrderAbolish("已作废",7),   //看不到
	OnOperating("处理中",8);   //不能动


	private String strValue;
	private int intValue;
	
	private OrderStatusEnum(String strValue, int intValue)
	{
		this.strValue = strValue;
		this.intValue = intValue;
	}
 
	
	public String getStrValue()
	{
		return strValue;
	}


	public void setStrValue(String strValue)
	{
		this.strValue = strValue;
	}


	public int getIntValue()
	{
		return intValue;
	}


	public void setIntValue(int intValue)
	{
		this.intValue = intValue;
	}


	public static OrderStatusEnum getEnumByInt(int intValue)
	{
		for (OrderStatusEnum c : OrderStatusEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static OrderStatusEnum getEnumByStr(String strValue)
	{
		for (OrderStatusEnum c : OrderStatusEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	}	 

}
