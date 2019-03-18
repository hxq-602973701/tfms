package com.explorer.tfms.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 活动实体类
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_activitys")
public class Activity implements java.io.Serializable{
	private Long id;
	/**
	 * 图片的URL
	 */
	private String url;
	/**
	 * 活动标题
	 */
	private String title;
	/**
	 * 是否显示在首页,0 不显示 1显示
	 */
	public String state = "0";
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 创建内容
	 */
	private String content;
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotEmpty(message="必须上传首页图片！")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@NotEmpty(message="活动标题不能为空！")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
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
	@NotEmpty(message="活动内容不能为空！")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
