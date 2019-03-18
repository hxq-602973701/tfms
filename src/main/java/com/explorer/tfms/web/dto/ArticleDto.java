package com.explorer.tfms.web.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.explorer.tfms.domain.Article;


public class ArticleDto {
	private Long id;
	/**
	 * 文章标题
	 */
	private Long title;
	/**
	 * 发布时间
	 */
	private String createTime;
	/**
	 * 文章内容
	 */
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotNull(message="文章标题不能为空")
	public Long getTitle() {
		return title;
	}
	public void setTitle(Long title) {
		this.title = title;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@NotEmpty(message="文章内容不能为空")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public ArticleDto(){}
	
	public Article getArticle(){
		Article article = new Article();
		article.setId(id);
		article.setContent(content);
		article.setCreateTime(createTime);
		return article;
	}
	
	public ArticleDto(Article article){
		this.setContent(article.getContent());
		this.setCreateTime(article.getCreateTime());
		this.setId(article.getId());
	}
	
	public ArticleDto(Article article,Long title){
		this.setContent(article.getContent());
		this.setCreateTime(article.getCreateTime());
		this.setId(article.getId());
		this.setTitle(title);
	}
}
