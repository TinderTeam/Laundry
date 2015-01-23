package cn.fuego.misp.ui.common.alipay;

import java.io.Serializable;

public class MispPayParameter implements Serializable
{
	private String partner= "2088811061975475";
	private String seller = "996825888@qq.com";
	private String rsa_private = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOVjx6iMMZgkgIndiKHoerD18LWnpjzIHEA6oyWAd0UaN5iWAoDeg71PW7h+k5ZPIJ8XCzHLj9PSBKrEKg8gCjKJxH7jR1hDaodL4Vm4HgOmf6XdhFxZOM0cy3NyD4sorFqMca5r1X5EkenDYkFjXmrwKaRifDOWRfGAf6RrWzxbAgMBAAECgYEA35m6xp4Zvc9fCIRMql5eMl8aS0hnb/o0J5vA6k5mdJKQvQkE2a+NRRy1MIsZvDvXdZxVyi0+PuEKsZbT1LiLlk0xZl+EmiXDRSYR6Wsz8LpT7edRhrAJj9IusPV1TDgghVIXmkEYT5dpRLke9l1sYQFWQb4rr6vjrnPxaajbhdECQQD57+1hSYmVX6GZQuZLhnlQt/SlV7Tz45zVBmcd4ZlZ877a19WsKHMWHDQbU7QNONtJ6CiAWaLycRUUPMDmlXlzAkEA6vRBjMiD59vMrLQyWUiBEKexXeCsypvh5OIFQ2hkiBKw6gIl4QK4uEfC1LxcIg1xfc8N7bn7gUlz6tdajyaXeQJAYFhTijAdwB34HitCuRRiSXJP9TilAWrZNujb8RHY2mryREv1CwMgsgI3N92BR6OGLKw4iJmFDa33sTBmL7yo7wJBALVIPQNo+w18dBGU/3wQCzVUje+HGQtC9ypokfMOqvKqqUIE4kEYnnnhNJx7sQK9KKIPjgmshDee+wdpnf/xoNECQDg8O7/Vm23PAUaiN70t/XU2GT334yb0n6VWcdow9LaRLsajL5c8/MbsGBa8gWw0smrcIyHbS0jLe5A1Gsr0jRg=";
	private String rsa_public = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDlY8eojDGYJICJ3Yih6Hqw9fC1p6Y8yBxAOqMlgHdFGjeYlgKA3oO9T1u4fpOWTyCfFwsxy4/T0gSqxCoPIAoyicR+40dYQ2qHS+FZuB4Dpn+l3YRcWTjNHMtzcg+LKKxajHGua9V+RJHpw2JBY15q8CmkYnwzlkXxgH+ka1s8WwIDAQAB";

	private String order_code;
	private String order_name;
	private String order_desc;
	private String order_price;
	private String notify_url;
	public String getPartner()
	{
		return partner;
	}
	public void setPartner(String partner)
	{
		this.partner = partner;
	}
	public String getSeller()
	{
		return seller;
	}
	public void setSeller(String seller)
	{
		this.seller = seller;
	}
	public String getRsa_private()
	{
		return rsa_private;
	}
	public void setRsa_private(String rsa_private)
	{
		this.rsa_private = rsa_private;
	}
	public String getRsa_public()
	{
		return rsa_public;
	}
	public void setRsa_public(String rsa_public)
	{
		this.rsa_public = rsa_public;
	}
	public String getOrder_code()
	{
		return order_code;
	}
	public void setOrder_code(String order_code)
	{
		this.order_code = order_code;
	}
	public String getOrder_name()
	{
		return order_name;
	}
	public void setOrder_name(String order_name)
	{
		this.order_name = order_name;
	}
	public String getOrder_desc()
	{
		return order_desc;
	}
	public void setOrder_desc(String order_desc)
	{
		this.order_desc = order_desc;
	}
	public String getOrder_price()
	{
		return order_price;
	}
	public void setOrder_price(String order_price)
	{
		this.order_price = order_price;
	}
	public String getNotify_url()
	{
		return notify_url;
	}
	public void setNotify_url(String notify_url)
	{
		this.notify_url = notify_url;
	}
	
	
	
	
	

}
