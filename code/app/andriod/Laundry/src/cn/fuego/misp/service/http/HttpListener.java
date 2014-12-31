package cn.fuego.misp.service.http;

public interface HttpListener
{
	void handle(MispHttpMessage message);
	
	void sendMessage(MispHttpMessage message);

}
