package cn.fuego.laundry.webservice.up.model.base;

import java.io.Serializable;

public class ProductTypeJson implements Serializable
{
	private int type_id;
	private String type_name;
	private int father_id;
	private String type_img;
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public int getFather_id() {
		return father_id;
	}
	public void setFather_id(int father_id) {
		this.father_id = father_id;
	}
	@Override
	public String toString() {
		return "ProductTypeJson [type_id=" + type_id + ", type_name="
				+ type_name + ", father_id=" + father_id + "]";
	}
	public String getType_img()
	{
		return type_img;
	}
	public void setType_img(String type_img)
	{
		this.type_img = type_img;
	}
	
	
	
}
