package com.explorer.tfms.service;
import java.util.List;
import com.explorer.tfms.domain.Article;

public interface ArticleService {
	
	public List<Article> findAllArticles();
	
	public Article getArticle(Long id);
	
	public void deleteArticle(Long id);

	public void saveArticle(Article article, Long title);

	public void updateArticle(Article article, Long title);
	
	public Article getArticleByTitle(Long titleId);
}
