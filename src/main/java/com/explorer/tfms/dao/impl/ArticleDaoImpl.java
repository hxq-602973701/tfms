package com.explorer.tfms.dao.impl;
import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.ArticleDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.Article;

@Repository("articleDao")
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao{
	public Article getArticleByTitle(Long title){
		String hql = "FROM Article a WHERE a.title.id=?";
		return (Article) this.getEntiyByHql(hql,new Object[]{title});
	}
}
