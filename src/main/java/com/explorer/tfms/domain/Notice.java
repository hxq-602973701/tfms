package com.explorer.tfms.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 公告实体类
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_notices")
public class Notice implements java.io.Serializable{
	private Long id;
	private String title;
	private String state = "0";
	private String createTime;
	private String content;
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotEmpty(message="公告标题不能为空！")
	@Size(max=16,message="公告标题不能超过16个字！")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotEmpty(message="公告状态不能为空")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@NotEmpty(message="公告内容不能为空")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}