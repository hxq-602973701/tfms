package com.explorer.tfms.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 频道实体类
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_channels")
public class Channel implements java.io.Serializable{
	private Long id;
	private String name;
	private String state = "0";
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotEmpty(message="模块名不能为空！")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotEmpty(message="模块状态不能为空！")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
