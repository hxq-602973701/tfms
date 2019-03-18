package com.explorer.tfms.dao;
import com.explorer.tfms.dao.base.BaseDao;
import com.explorer.tfms.domain.Article;

public interface ArticleDao extends BaseDao<Article>{
	public Article getArticleByTitle(Long title);
}
