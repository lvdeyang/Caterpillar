/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.orm.common.dao.IBaseDao
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.orm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.dao.DataAccessException;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;
import pub.caterpillar.orm.hql.SubHql;
import pub.caterpillar.orm.po.PageObject;



/**
 * 
 * @author zhuzheng
 *
 * @param <T> 数据类型
 */
public interface IBaseDao<T>  
{

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.load
	 * @Description:
	 *
	 * @param id
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:37:02
	 *
	 */
	T load(Serializable id);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.loadAll
	 * @Description:
	 *
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:37:13
	 *
	 */
	List<T> loadAll();

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.get
	 * @Description:
	 *
	 * @param id
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:37:26
	 *
	 */
	T get(Serializable id);
	
	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.get
	 * @Description:
	 *
	 * @param id
	 * @return
	 *
	 * @author:lvdeyang
	 * @date:2017-3-2 下午16:17:50
	 *
	 */
	T get(String uuid);
	
	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.findAll
	 * @Description:
	 *
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:37:41
	 *
	 */
	List<T> findAll();

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.delete
	 * @Description:
	 *
	 * @param id
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:37:50
	 *
	 */
	void delete(Serializable id);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.delete
	 * @Description:
	 *
	 * @param o
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:38:00
	 *
	 */
	void delete(T o);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.deleteAll
	 * @Description:
	 *
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:38:08
	 *
	 */
	void deleteAll();

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.deleteAll
	 * @Description:
	 *
	 * @param c
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:38:17
	 *
	 */
	void deleteAll(Collection<T> c);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.save
	 * @Description:
	 *
	 * @param o
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:38:24
	 *
	 */
	void save(T o);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.saveAll
	 * @Description:
	 *
	 * @param c
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:38:34
	 *
	 */
	void saveAll(Collection<T> c);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.update
	 * @Description:
	 *
	 * @param o
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:38:44
	 *
	 */
	void update(T o);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.saveOrUpdate
	 * @Description:
	 *
	 * @param o
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:38:52
	 *
	 */
	void saveOrUpdate(T o);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.saveOrUpdateAll
	 * @Description:
	 *
	 * @param entitys
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:38:59
	 *
	 */
	void saveOrUpdateAll(Collection<T> entitys);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.merge
	 * @Description:
	 *
	 * @param o
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:39:07
	 *
	 */
	void merge(T o);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.evict
	 * @Description:
	 *
	 * @param o
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:39:14
	 *
	 */
	void evict(T o);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.replicate
	 * @Description:
	 *
	 * @param o
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:39:21
	 *
	 */
	void replicate(T o);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.find
	 * @Description:
	 *
	 * @param hql
	 * @param value
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:39:58
	 *
	 */
	List<T> find(String hql, Object... values);
	
	List findSpecialType(String hql);


	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.deleteByHql
	 * @Description:
	 *
	 * @param hql
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:40:16
	 *
	 */
	int deleteByHql(DeleteHql hql);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.findByHql
	 * @Description:
	 *
	 * @param hql
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:40:31
	 *
	 */
	List<T> findByHql(QueryHql hql);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.findByHql
	 * @Description:
	 *
	 * @param hql
	 * @param pageNum
	 * @param pageSize
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:40:59
	 *
	 */
	List<T> findByHql(QueryHql hql, int pageNum, int pageSize);
	
	List<Object> findBySql(String sql);
	
	List<Object> findBySql(String sql, int pageNum, int pageSize);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.getByHql
	 * @Description:
	 *
	 * @param hql
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:42:53
	 *
	 */
	T getByHql(QueryHql hql);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.countByHql
	 * @Description:
	 *
	 * @param hql
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:43:04
	 *
	 */
	int countByHql(CountHql hql);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.countAll
	 * @Description:
	 *
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:43:13
	 *
	 */
	int countAll();



	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.deleteByField
	 * @Description:
	 *
	 * @param propertyName
	 * @param value
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:44:31
	 *
	 */
	int deleteByField(String propertyName, Object value);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.findAll
	 * @Description:
	 *
	 * @param page
	 * @param pageSize
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:45:13
	 *
	 */
	List<T> findAll(int page, int pageSize);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.findByField
	 * @Description:
	 *
	 * @param field
	 * @param value
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:46:21
	 *
	 */
	List<T> findByField(String field, Object value);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.findByFields
	 * @Description:
	 *
	 * @param Fields
	 * @param values
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:47:03
	 *
	 */
	List<T> findByFields(String[] Fields, Object[] values);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.findByField
	 * @Description:
	 *
	 * @param field
	 * @param value
	 * @param page
	 * @param pageSize
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:48:51
	 *
	 */
	List<T> findByField(String field, Object value, int page, int pageSize);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.findByFields
	 * @Description:
	 *
	 * @param propertyNames
	 * @param values
	 * @param page
	 * @param pageSize
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:49:47
	 *
	 */
	List<T> findByFields(String[] propertyNames, Object[] values, int page,
			int pageSize);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.countByField
	 * @Description:
	 *
	 * @param field
	 * @param value
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:51:04
	 *
	 */
	int countByField(String field, Object value);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.countByFields
	 * @Description:
	 *
	 * @param fields
	 * @param values
	 * @return
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:51:47
	 *
	 */
	int countByFields(String[] fields, Object[] values);

	/**
	 * 
	 * @Function: platform.orm.common.dao.BaseDaoInterface.findByPage
	 * @Description:
	 *
	 * @param hsql
	 * @param page
	 * @return
	 * @throws DataAccessException
	 *
	 * @author:zhuzheng
	 * @date:2014-4-27 上午10:56:20
	 *
	 */
	List<T> findAll(PageObject page) throws DataAccessException;

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.getAnyOne
	 * @Description:
	 * @return
	 * @author:zhuzheng
	 * @date:2014-5-23 下午12:46:18
	 */
	T getAnyOne();

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.getByFields
	 * @Description:
	 * @param Fields
	 * @param values
	 * @return
	 * @author:zhuzheng
	 * @date:2014-5-23 下午12:58:42
	 */
	T getByFields(String[] Fields, Object[] values);

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.getByField
	 * @Description:
	 * @param field
	 * @param value
	 * @return
	 * @author:zhuzheng
	 * @date:2014-5-23 下午01:07:30
	 */
	T getByField(String field, Object value);

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.deleteAllByIds
	 * @Description:
	 * @param ids
	 * @author:zhuzheng
	 * @date:2014-5-27 下午02:34:13
	 */
	void deleteAllByIds(Collection<Long> ids);

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.getAllByIds
	 * @Description:
	 * @param ids
	 * @author:houyanlong
	 * @date:2014-9-27 下午02:34:13
	 */
	public List<T> getAllByIds(Collection<Long> ids);
	
	public List<T> getAllByUuids(Collection<String> uuids);
	
	/**
	 * @Function: platform.orm.base.dao.IBaseDao.newDeleteHql
	 * @Description:
	 * @return
	 * @author:zhuzheng
	 * @date:2014-5-27 下午02:57:09
	 */
	DeleteHql newDeleteHql();

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.newQueryHql
	 * @Description:
	 * @return
	 * @author:zhuzheng
	 * @date:2014-5-27 下午02:57:17
	 */
	QueryHql newQueryHql();

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.newCountHql
	 * @Description:
	 * @return
	 * @author:zhuzheng
	 * @date:2014-5-27 下午02:57:41
	 */
	CountHql newCountHql();  
 
	SubHql newSubHql();

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.findByPage
	 * @Description:
	 * @param hql
	 * @param page
	 * @return
	 * @throws DataAccessException
	 * @author:zhuzheng
	 * @date:2014年7月17日 下午6:45:38
	 */
	List<T> findByPage(QueryHql hql, PageObject page)
			throws DataAccessException;

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.saveAndEvict
	 * @Description:
	 * @param o
	 * @author:zhuzheng
	 * @date:2014年7月21日 下午1:46:16
	 */
	void saveAndEvict(T o);

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.findFieldByHql
	 * @Description:
	 * @param hql
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @author:zhuzheng
	 * @date:2014年7月25日 下午3:12:50
	 */
	<O> List<O> findFieldByHql(QueryHql hql, int pageNum, int pageSize);

	/**
	 * @Function: platform.orm.base.dao.IBaseDao.findFieldByHql
	 * @Description:
	 * @param hql
	 * @return
	 * @author:zhuzheng
	 * @date:2014年7月25日 下午3:17:19
	 */
	<O> List<O> findFieldByHql(QueryHql hql);

	<O> O getFieldByHql(QueryHql hql);

	T getAnyByHql(QueryHql hql);

	void flush();

	List<T> findByHqlIgnoreCache(QueryHql hql);
	
	void execute(String hql);
	
	int countByHql(String hql);

	List<T> findByHqlPage(QueryHql hql, int pageNum, int pageSize);

	List<T> findByHqlAndCount(QueryHql hql, int count);

	void deleteByUuid(String uuid);
} 
