package com.explorer.tfms.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 文章实体类
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_article")
public class Article implements java.io.Serializable{
	private Long id;
	/**
	 * 文章标题
	 */
	private Channel title;
	/**
	 * 发布时间
	 */
	private String createTime;
	/**
	 * 文章内容
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
	@OneToOne
	@JoinColumn(name="channelId")
	public Channel getTitle() {
		return title;
	}
	public void setTitle(Channel title) {
		this.title = title;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
