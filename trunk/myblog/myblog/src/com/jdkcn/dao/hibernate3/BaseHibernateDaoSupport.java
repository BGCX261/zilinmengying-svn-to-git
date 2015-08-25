/**
 * <pre>
 * Copyright:		Copyright(C) 2006, jdkcn.com
 * Filename:		BaseHibernateDaoSupport.java
 * Class:			BaseHibernateDaoSupport
 * Date:			Sep 10, 2006 5:41:12 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">Rory</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Sep 10, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.dao.hibernate3;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.jdkcn.dao.BaseDao;
import com.jdkcn.util.HibernateUtils;
import com.jdkcn.util.PaginationSupport;


/**
 * @author <a href="rory.cn@gmail.com">Rory</a>
 * @since Sep 10, 2006 5:41:12 PM
 * @version $id:BaseHibernateDaoSupport.java $
 * @see <a href="http://www.blogjava.net/calvin/archive/2006/04/28/43830.html">Java5泛型的用法，T.class的获取和为擦拭法站台</a>
 */
public abstract class BaseHibernateDaoSupport<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseHibernateDaoSupport() {
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.BaseDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(T domain) {
		getHibernateTemplate().saveOrUpdate(domain);
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.dao.BaseDao#remove(java.lang.Object)
	 */
	public void remove(T domain) {
		getHibernateTemplate().delete(domain);
	}


	/* (non-Javadoc)
	 * @see com.jdkcn.dao.BaseDao#get(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		T o = (T) getHibernateTemplate().get(entityClass, id);
		return o;
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.dao.BaseDao#findPageByCriteria(org.hibernate.criterion.DetachedCriteria, int, int)
	 */
	@SuppressWarnings("unchecked")
	public PaginationSupport<T> findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize, final int startIndex) {
		return (PaginationSupport<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);

				// Get the orginal orderEntries
				OrderEntry[] orderEntries = HibernateUtils.getOrders(executableCriteria);
				// Remove the orders
				executableCriteria = HibernateUtils.removeOrders(executableCriteria);
				// get the original projection
				Projection projection = HibernateUtils.getProjection(executableCriteria);

				int totalCount = ((Integer) executableCriteria.setProjection(Projections.rowCount()).uniqueResult())
						.intValue();

				executableCriteria.setProjection(projection);
				if (projection == null) {
					// Set the ResultTransformer to get the same object
					// structure with hql
					executableCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				}
				// Add the orginal orderEntries
				executableCriteria = HibernateUtils.addOrders(executableCriteria, orderEntries);

				// Now, the Projection and the orderEntries have been resumed
				List<T> items = HibernateUtils.getPageResult(executableCriteria, startIndex, pageSize);
				return new PaginationSupport(items, totalCount, startIndex, pageSize);
			}
		}, true);
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.BaseDao#getListByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByCriteria(DetachedCriteria detachedCriteria) {
		return getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.dao.BaseDao#getListByCriteria(org.hibernate.criterion.DetachedCriteria, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByCriteria(DetachedCriteria detachedCriteria,
			int offset, int size) {
		return getHibernateTemplate().findByCriteria(detachedCriteria, offset, size);
	}
}
