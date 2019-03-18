package com.explorer.tfms.dao.base.impl;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.explorer.tfms.dao.base.BaseDao;
import com.explorer.tfms.domain.PageBean;

//通过UserDaoImpl的父类（BaseDaoImpl<T>T获得需要操作的实体类）
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	//当userDaoImpl继承BaseDaoImpl，当service[业务逻辑层]层被调用的时候，
	//肯定会实例化BaseDaoImpl类的无参构造方法
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	
	public BaseDaoImpl(){
		//构建出实体类
		//获得带有泛型的父类
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得父类的泛型
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	protected Session getSession(){
		Session session = this.sessionFactory.getCurrentSession();
		if(session==null){
			return this.sessionFactory.openSession();
		}
		return session;
	}
	
	/**
	 * @Title: saveEntity 
	 * @Description: 添加实体方法
	 * @param entity:
	 * @throws: 
	 * @date: 3月6日 下午4:23:26
	 * @version: V1.0
	 *
	 */
	public void saveEntity(T entity) {
		
		this.getSession().save(entity);
	}

	/**
	 * @Title: deleteEntity 
	 * @Description: 删除实体
	 * @param entity:
	 * @throws: 
	 * @date: 3月6日 下午4:23:08
	 * @version: V1.0
	 *
	 */
	public void deleteEntity(T entity) {
		this.getSession().delete(entity);
	}
	
	/**
	 * @Title: getEntity 
	 * @Description:获得实体
	 * @param id
	 * @return:Object
	 * @date: 3-6 下午02:06:40
	 * @version: V1.0
	 *
	 */
	public Object getEntity(Long id){
		return this.getSession().get(clazz,id);
	}
	
	/**
	 * @Title: loadEntity 
	 * @Description: 加载实体
	 * @param id
	 * @return:对象
	 * @date: 3-6 下午02:08:22
	 * @version: V1.0
	 *
	 */
	public Object loadEntity(Long id){
		return this.getSession().load(clazz,id);
	}
	
	/**
	 * @Title: updateEntity 
	 * @Description: 更新实体
	 * @param entity:
	 * @date: 3-6 下午02:10:51
	 * @version: V1.0
	 *
	 */
	public void updateEntity(T entity){	
		this.getSession().update(entity);
	}
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存或者跟新方法
	 * @param entity:
	 * @date: 3-6 下午02:20:59
	 * @version: V1.0
	 *
	 */
	public void saveOrUpdateEntity(T entity){
		this.getSession().saveOrUpdate(entity);
	}
	
	/**
	 * @Title: getEntiyByHql 
	 * @Description: 通过条件查询对象
	 * @param hql
	 * @param args
	 * @return:Object
	 * @date: 3-6 下午03:40:13
	 * @version: V1.0
	 *
	 */
	public Object getEntiyByHql(String hql,Object...args){
		Query query = this.getSession().createQuery(hql);
		if(args!=null){
			for(int i = 0; i < args.length; i++){
				query.setParameter(i,args[i]);
			}
		}
		return query.uniqueResult();
	}
	
	/**
	 * @Title: getEntiyBySql
	 * @Description: 通过条件查询对象
	 * @param hql
	 * @param args
	 * @return:Object
	 * @date: 3-6 下午03:40:13
	 * @version: V1.0
	 *
	 */
	public Object getEntiyBySql(String sql,Object...args){
		Query query = this.getSession().createSQLQuery(sql).addEntity(clazz);
		if(args!=null){
			for(int i = 0; i < args.length; i++){
				query.setParameter(i,args[i]);
			}
		}
		return query.uniqueResult();
	}
	
	/**
	 * @Title: executeHQL 
	 * @Description: 通过Hql语句执行操作
	 * @param hql
	 * @param args:
	 * @throws: 
	 * @date: 3月6日 下午4:20:56
	 * @version: V1.0
	 *
	 */
	public void executeHQL(String hql,Object...args){
		Query query = this.getSession().createQuery(hql);
		if(args!=null){
			for(int i = 0; i < args.length; i++){
				query.setParameter(i,args[i]);
			}
		}
		query.executeUpdate();
	}
	
	/**
	 * @Title: executeSQL 
	 * @Description: 通过Sql语句执行操作
	 * @param hql
	 * @param args:
	 * @date: 3月6日 下午4:21:42
	 * @version: V1.0
	 *
	 */
	public void executeSQL(String sql,Object...args){
		Query query = this.getSession().createSQLQuery(sql);
		if(args!=null){
			for(int i = 0; i < args.length; i++){
				query.setParameter(i,args[i]);
			}
		}
		query.executeUpdate();
	}
	
	/**
	 * @Title: findBySQL 
	 * @Description: 可以获得数据库中的元数据
	 * @param sql
	 * @param args
	 * @return:
	 * @throws: 
	 * @date: 3月6日 下午4:27:29
	 * @version: V1.0
	 *
	 */
	public List<Object[]> findBySQL(String sql,Object...args) {
		SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		for(int i = 0;i<args.length;i++){
			sqlQuery.setParameter(i, args[i]);
		}
		return sqlQuery.list();
	}

	/**
	 * @Title: listAll 
	 * @Description: 不分页查询所有
	 * @return: List<T>
	 * @date: 3-6 下午02:24:22
	 * @version: V1.0
	 *
	 */
	public List<T> listAll(){
		return this.getSession()
					.createQuery("FROM "+clazz.getSimpleName())
					.list();
	}
	
	/**
	 * @Title: listAllByArgs 
	 * @Description: 通过Hql语句的条件查询
	 * @param hql
	 * @param args
	 * @return:List<T>
	 * @date: 3-6 下午02:27:58
	 * @version: V1.0
	 *
	 */
	public List<T> listAllByArgs(String hql,Object...args){
		Query query = this.getSession().createQuery(hql);
		if(args!=null){
			for(int i = 0; i < args.length; i++){
				query.setParameter(i,args[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * @Title: listAllByArgs 
	 * @Description: 通过Hql语句的条件升序查询
	 * @param hql
	 * @Param order
	 * @param args
	 * @return:List<T>
	 * @date: 3-6 下午02:27:58
	 * @version: V1.0
	 *
	 */
	public List<T> listAllAsc(String hql,String order,Object...args){
		hql = hql+" ORDER BY "+order+" ASC";
		Query query = this.getSession().createQuery(hql);
		if(args!=null){
			for(int i = 0; i < args.length; i++){
				query.setParameter(i,args[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * @Title: listAllByArgs 
	 * @Description: 通过Hql语句的条件降序查询
	 * @param hql
	 * @Param order:排序条件
	 * @param args
	 * @return:List<T>
	 * @date: 3-6 下午02:27:58
	 * @version: V1.0
	 *
	 */
	public List<T> listAllDESC(String hql,String order,Object...args){
		hql = hql+" ORDER BY "+order+" DESC";
		Query query = this.getSession().createQuery(hql);
		if(args!=null){
			for(int i = 0; i < args.length; i++){
				query.setParameter(i,args[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * @Title: listAllSQL 
	 * @Description: 通过sql语句查询
	 * @param sql
	 * @param order
	 * @param args
	 * @return:List<T> listAllSQL
	 * @date: 3-6 下午03:35:41
	 * @version: V1.0
	 *
	 */
	public List<T> listAllSQL(String sql,Object...args){
		Query query = this.getSession().createSQLQuery(sql).addEntity(clazz);
		if(args!=null){
			for(int i = 0; i < args.length; i++){
				query.setParameter(i,args[i]);
			}
		}
		
		return query.list();
	}
	
	/**
	 * @Title: listAllSQL 
	 * @Description: 通过sql语句查询
	 * @param sql
	 * @param order
	 * @param args
	 * @return:List<T> listAllSQL
	 * @date: 3-6 下午03:35:41
	 * @version: V1.0
	 *
	 */
	public List<T> listAllSQLASC(String sql,String order,Object...args){
		sql = sql+"ORDER BY "+order+" ASC";
		Query query = this.getSession().createSQLQuery(sql).addEntity(clazz);
		if(args!=null){
			for(int i = 0; i < args.length; i++){
				query.setParameter(i,args[i]);
			}
		}
		
		return query.list();
	}
	
	/**
	 * @Title: listAllSQL 
	 * @Description: 通过sql语句降序查询
	 * @param sql
	 * @param order
	 * @param args
	 * @return:List<T> listAllSQL
	 * @date: 3-6 下午03:35:41
	 * @version: V1.0
	 *
	 */
	public List<T> listAllSQLDESC(String sql,String order,Object...args){
		sql = sql+"ORDER BY "+order+" DESC";
		Query query = this.getSession().createSQLQuery(sql).addEntity(clazz);
		if(args!=null){
			for(int i = 0; i < args.length; i++){
				query.setParameter(i,args[i]);
			}
		}
		
		return query.list();
	}
	
	/**
	 * @Title: findAll 
	 * @Description: 基于HQl的分页查询
	 * @param hql
	 * @param currentPage
	 * @param args
	 * @return:
	 * @throws: 
	 * @date: 3月6日 下午4:39:17
	 * @version: V1.0
	 *
	 */
	public PageBean<T> findAll(String hql,int currentPage,Object...args){
		PageBean<T> pageBean = new PageBean<T>();
		Long count = (long) this.listAllByArgs(hql,args).size(); 						//总记录数
		int pageSize = pageBean.getPageSize();             						//每页多少条
		int pageCount =	(int) (count%pageSize==0?(count/pageSize):(count/pageSize+1));	//总页数
		Query query = this.getSession().createQuery(hql);
		for(int i = 0; i < args.length; i++){
			query.setParameter(i, args[i]);
		}
		List<T> records = query
				.setFirstResult((currentPage-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
		pageBean.setCurrentPage(currentPage);
		pageBean.setRecordCount(count);
		pageBean.setRecords(records);
		pageBean.setPageCount(pageCount);
		return pageBean;
	}
	
	/**
	 * @Title: findAll 
	 * @Description: 基于HQl的分页查询
	 * @param hql
	 * @param currentPage
	 * @param pageSize
	 * @param args
	 * @return:
	 * @throws: 
	 * @date: 3月6日 下午4:39:17
	 * @version: V1.0
	 *
	 */
	public PageBean<T> findAll(String hql,int currentPage,int pageSize,Object...args){
		PageBean<T> pageBean = new PageBean<T>();
		Long count = (long) this.listAllByArgs(hql,args).size(); 						//总记录数             						//每页多少条
		int pageCount =	(int) (count%pageSize==0?(count/pageSize):(count/pageSize+1));	//总页数
		Query query = this.getSession().createQuery(hql);
		for(int i = 0; i < args.length; i++){
			query.setParameter(i, args[i]);
		}
		List<T> records = query
				.setFirstResult((currentPage-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
		pageBean.setCurrentPage(currentPage);
		pageBean.setRecordCount(count);
		pageBean.setRecords(records);
		pageBean.setPageCount(pageCount);
		return pageBean;
	}
	
	/**
	 * @Title: findAll 
	 * @Description: 基于HQl的分页查询
	 * @param hql
	 * @param currentPage
	 * @param pageSize
	 * @return:
	 * @throws: 
	 * @date: 3月6日 下午4:39:17
	 * @version: V1.0
	 *
	 */
	public PageBean<T> findAll(String hql,int currentPage,int pageSize){
		PageBean<T> pageBean = new PageBean<T>();
		Long count = (long) this.listAllByArgs(hql).size(); 						//总记录数             						//每页多少条
		int pageCount =	(int) (count%pageSize==0?(count/pageSize):(count/pageSize+1));	//总页数
		Query query = this.getSession().createQuery(hql);
		List<T> records = query
				.setFirstResult((currentPage-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
		pageBean.setCurrentPage(currentPage);
		pageBean.setRecordCount(count);
		pageBean.setRecords(records);
		pageBean.setPageCount(pageCount);
		return pageBean;
	}
	
	/**
	 * @Title: findAllSQL 
	 * @Description: 基于SQL的分页查询
	 * @param sql
	 * @param currentPage
	 * @param args
	 * @return:
	 * @throws: 
	 * @date: 3月6日 下午5:02:59
	 * @version: V1.0
	 *
	 */
	public PageBean<T> findAllSQL(String sql,int currentPage,Object...args){
		PageBean<T> pageBean = new PageBean<T>();
		Long count = (long) this.listAllByArgs(sql,args).size(); 						//总记录数
		int pageSize = pageBean.getPageSize();             						//每页多少条
		int pageCount =	(int) (count%pageSize==0?(count/pageSize):(count/pageSize+1));	//总页数
		Query query = this.getSession().createSQLQuery(sql).addEntity(clazz);
		for(int i = 0; i < args.length; i++){
			query.setParameter(i, args[i]);
		}
		List<T> records = query
				.setFirstResult((currentPage-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
		pageBean.setCurrentPage(currentPage);
		pageBean.setRecordCount(count);
		pageBean.setRecords(records);
		pageBean.setPageCount(pageCount);
		return pageBean;
	}
}
