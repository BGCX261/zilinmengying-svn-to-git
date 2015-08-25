/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		RequestCounterDaoHibernateImpl.java
 * Class:			RequestCounterDaoHibernateImpl
 * Date:			Dec 6, 2006 11:16:43 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 6, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.dao.hibernate3;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jdkcn.dao.RequestCounterDao;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.RequestCounter;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 6, 2006 11:16:43 AM
 * @version $Id RequestCounterDaoHibernateImpl.java$
 */
public class RequestCounterDaoHibernateImpl extends
		BaseHibernateDaoSupport<RequestCounter> implements RequestCounterDao {

	/* (non-Javadoc)
	 * @see com.jdkcn.dao.RequestCounterDao#getRequestList(com.jdkcn.domain.Entry, int)
	 */
	@SuppressWarnings("unchecked")
	public List<RequestCounter> getRequestList(Entry entry, int size) {
		DetachedCriteria criteria = DetachedCriteria.forClass(RequestCounter.class);
		if (StringUtils.isNotBlank(entry.getName())) {
			criteria.add(Restrictions.or(Restrictions.like("uri", "entry/id/" + entry.getId()+ ".html", MatchMode.ANYWHERE), Restrictions.like("uri", "entry/" + entry.getName() + ".html",MatchMode.ANYWHERE)));
		}else{
			criteria.add(Restrictions.like("uri", "entry/id/" + entry.getId()+ ".html", MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.or(Restrictions.like("referer", "google", MatchMode.ANYWHERE), Restrictions.like("referer", "baidu", MatchMode.ANYWHERE)));
		criteria.addOrder(Order.desc("requestTime"));
		return getHibernateTemplate().findByCriteria(criteria, -1, size);
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.RequestCounterDao#getRequestListByUri(java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	public List<RequestCounter> getRequestListByUri(String uri, int size) {
		DetachedCriteria criteria = DetachedCriteria.forClass(RequestCounter.class);
		criteria.add(Restrictions.eq("uri", uri));
		criteria.add(Restrictions.or(Restrictions.like("referer", "google", MatchMode.ANYWHERE), Restrictions.like("referer", "baidu", MatchMode.ANYWHERE)));
		criteria.addOrder(Order.desc("requestTime"));
		return getHibernateTemplate().findByCriteria(criteria, -1, size);
	}

}
