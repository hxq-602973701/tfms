package com.explorer.tfms.dao.base;
import java.util.List;

import com.explorer.tfms.domain.PageBean;

public interface BaseDao<T> {
	/**
	 * @Title: saveEntity 
	 * @Description: 添加实体方法
	 * @param entity:
	 * @throws: 
	 * @date: 3月6日 下午4:23:26
	 * @version: V1.0
	 *
	 */
	public void saveEntity(T entity);

	/**
	 * @Title: deleteEntity 
	 * @Description: 删除实体
	 * @param entity:
	 * @throws: 
	 * @date: 3月6日 下午4:23:08
	 * @version: V1.0
	 *
	 */
	public void deleteEntity(T entity);
	
	/**
	 * @Title: getEntity 
	 * @Description:获得实体
	 * @param id
	 * @return:Object
	 * @date: 3-6 下午02:06:40
	 * @version: V1.0
	 *
	 */
	public Object getEntity(Long id);
	/**
	 * @Title: loadEntity 
	 * @Description: 加载实体
	 * @param id
	 * @return:对象
	 * @date: 3-6 下午02:08:22
	 * @version: V1.0
	 *
	 */
	public Object loadEntity(Long id);
	
	/**
	 * @Title: updateEntity 
	 * @Description: 更新实体
	 * @param entity:
	 * @date: 3-6 下午02:10:51
	 * @version: V1.0
	 *
	 */
	public void updateEntity(T entity);
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存或者跟新方法
	 * @param entity:
	 * @date: 3-6 下午02:20:59
	 * @version: V1.0
	 *
	 */
	public void saveOrUpdateEntity(T entity);
	
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
	public Object getEntiyByHql(String hql,Object...args);
	
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
	public Object getEntiyBySql(String sql,Object...args);
	
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
	public void executeHQL(String hql,Object...args);
	/**
	 * @Title: executeSQL 
	 * @Description: 通过Sql语句执行操作
	 * @param hql
	 * @param args:
	 * @date: 3月6日 下午4:21:42
	 * @version: V1.0
	 *
	 */
	public void executeSQL(String sql,Object...args);
	
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
	public List<Object[]> findBySQL(String sql,Object...args);
	
	/**
	 * @Title: listAll 
	 * @Description: 不分页查询所有
	 * @return: List<T>
	 * @date: 3-6 下午02:24:22
	 * @version: V1.0
	 *
	 */
	public List<T> listAll();
	
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
	public List<T> listAllByArgs(String hql,Object...args);
	
	/**
	 * @Title: listAllByArgs 
	 * @Description: 通过Hql语句的条件升序查询
	 * @param hql
	 * @Param order
	 * @param args
	 * @return:List<T>
	 * @date: 3-6 下午02:27:58
	 * @version: V1.0
	 */
	public List<T> listAllAsc(String hql,String order,Object...args);
	
	/**
	 * @Title: listAllByArgs 
	 * @Description: 通过Hql语句的条件降序查询
	 * @param hql
	 * @Param order
	 * @param args
	 * @return:List<T>
	 * @date: 3-6 下午02:27:58
	 * @version: V1.0
	 */
	public List<T> listAllDESC(String hql,String order,Object...args);
	
	/**
	 * @Title: listAllSQL 
	 * @Description: 通过sql语句查询
	 * @param sql
	 * @param args
	 * @return:List<T> listAllSQL
	 * @date: 3-6 下午03:35:41
	 * @version: V1.0
	 */
	public List<T> listAllSQL(String sql,Object...args);
	
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
	public List<T> listAllSQLASC(String sql,String order,Object...args);
	
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
	public List<T> listAllSQLDESC(String sql,String order,Object...args);
	
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
	public PageBean<T> findAll(String hql,int currentPage,Object...args);
	
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
	public PageBean<T> findAll(String hql,int currentPage,int pageSize,Object...args);
	
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
	public PageBean<T> findAll(String hql,int currentPage,int pageSize);
	
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
	public PageBean<T> findAllSQL(String sql,int currentPage,Object...args);
}
