package com.explorer.tfms.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体类
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_users")
public class User implements java.io.Serializable{
	private Long id;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 用户登录名称
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户性别
	 */
	private String sex;
	/**
	 * email
	 */
	private String email;
	/**
	 * 注册时间
	 */
	private String regDate;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 用户地址
	 */
	private String address;
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
