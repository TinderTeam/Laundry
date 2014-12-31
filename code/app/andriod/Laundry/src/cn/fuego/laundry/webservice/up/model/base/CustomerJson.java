package cn.fuego.laundry.webservice.up.model.base;

import java.util.Date;

/**
 * 
 * @author jun
 *
 */
public class CustomerJson
{
	private int user_id;
	private String grade;
	private int score;
	private String cellphone;
	private String email;
	private String car_id;  //车牌号
	private int recommender_id;
	private String status;
	private String request; //请求类型 激活/升级/解冻
	private Date login_date;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	
	public int getRecommender_id() {
		return recommender_id;
	}
	public void setRecommender_id(int recommender_id) {
		this.recommender_id = recommender_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	
	public Date getLogin_date() {
		return login_date;
	}
	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}
	@Override
	public String toString() {
		return "CustomerJson [user_id=" + user_id + ", grade=" + grade
				+ ", score=" + score + ", cellphone=" + cellphone + ", email="
				+ email + ", car_id=" + car_id + ", recommender_id="
				+ recommender_id + ", status=" + status + ", request="
				+ request + ", login_date=" + login_date + "]";
	}
	

}
