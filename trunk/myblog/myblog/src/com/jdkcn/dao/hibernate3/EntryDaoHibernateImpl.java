/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		EntryDaoHibernateImpl.java
 * Class:			EntryDaoHibernateImpl
 * Date:			2006-9-15
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| 2006-9-15   | Rory Ye	    | Created			|
 *          | 2007-8-28   | Rory Ye     | fix the related entries bug
 * </pre>
 **/
package com.jdkcn.dao.hibernate3;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.jdkcn.dao.EntryDao;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.Tag;
import com.jdkcn.util.DateUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-9-15
 * @version $Id EntryDaoHibernateImpl.java$
 */
public class EntryDaoHibernateImpl extends BaseHibernateDaoSupport<Entry> implements
		EntryDao {
	
	private Log log = LogFactory.getLog(EntryDaoHibernateImpl.class);
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.EntryDao#getEntryByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Entry getEntryByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Entry.class);
		criteria.add(Restrictions.eq("name", name));
		List result = getHibernateTemplate().findByCriteria(criteria);
		if(result.isEmpty())
			return null;
		else{
			return (Entry)result.get(0);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.EntryDao#getRecentEntries(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Entry> getRecentEntries(int size) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Entry.class);
		criteria.add(Restrictions.in("entryStatus", Entry.EntryStatus.SHOWING_ENTRY_STATUS));
		criteria.add(Restrictions.eq("type", Entry.Type.POST));
		criteria.addOrder(Order.desc("postTime"));
		List<Entry> entries = getHibernateTemplate().findByCriteria(criteria, -1, size);
		for (Entry entry:entries) {
			Hibernate.initialize(entry.getCategories());
			log.debug("[myblog]: force initialize entry's categories.");
			Hibernate.initialize(entry.getTags());
			log.debug("[myblog]: force initialize entry's tags.");
		}
		return entries;
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.EntryDao#getNextEntry(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Entry getNextEntry(final String id) {
		List entries = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("from Entry entry where entry.postTime<(select current.postTime from Entry current where current.id=:id) and entry.entryStatus=:entryStatus and entry.type=:type order by entry.postTime desc");
				query.setParameter("id", id);
				query.setParameter("entryStatus", Entry.EntryStatus.PUBLISH);
				query.setParameter("type", Entry.Type.POST);
				query.setMaxResults(1);
				return query.list();
			}});
		if(entries.isEmpty())
			return null;
		else
			return (Entry)entries.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.EntryDao#getPreviousEntry(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Entry getPreviousEntry(final String id) {
		List entries = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("from Entry entry where entry.postTime>(select current.postTime from Entry current where current.id=:id) and entry.entryStatus=:entryStatus and entry.type=:type order by entry.postTime asc");
				query.setParameter("id", id);
				query.setParameter("entryStatus", Entry.EntryStatus.PUBLISH);
				query.setParameter("type", Entry.Type.POST);
				query.setMaxResults(1);
				return query.list();
			}});
		if(entries.isEmpty())
			return null;
		else
			return (Entry)entries.get(0);
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.dao.EntryDao#getRelatedEntries(com.jdkcn.domain.Entry, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Entry> getRelatedEntries(Entry entry, int size) {
		List<String> ids = new ArrayList<String>();
		for(Tag tag:entry.getTags()) {
			ids.add(tag.getId());
		}
		if(ids.isEmpty()) {
			return new ArrayList<Entry>();
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Entry.class);
		criteria.add(Restrictions.not(Restrictions.eq("id", entry.getId())));
		criteria.addOrder(Order.desc("postTime"));
		criteria.add(Restrictions.eq("entryStatus", Entry.EntryStatus.PUBLISH));
		criteria.add(Restrictions.eq("type", Entry.Type.POST));
		criteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		criteria.createCriteria("tags").add(Restrictions.in("id", ids));
//		criteria.setProjection(Projections.distinct(Projections.property("id")));
		List result = getHibernateTemplate().findByCriteria(criteria, -1, size);
//		DetachedCriteria entryCriteria = DetachedCriteria.forClass(Entry.class);
//		entryCriteria.add(Restrictions.in("id", result));
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.EntryDao#getPages(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Entry> getPages(String entryStatus) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Entry.class);
		criteria.add(Restrictions.eq("type", Entry.Type.PAGE));
		if(StringUtils.isNotBlank(entryStatus)){
			criteria.add(Restrictions.eq("entryStatus", entryStatus));
		}
		return getHibernateTemplate().findByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.dao.EntryDao#getMonthList()
	 */
	@SuppressWarnings("unchecked")
	public List<Date> getMonthList() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Entry.class);
		criteria.add(Restrictions.eq("type", Entry.Type.POST));
		criteria.add(Restrictions.in("entryStatus", Entry.EntryStatus.SHOWING_ENTRY_STATUS));
		criteria.addOrder(Order.desc("postTime"));
		criteria.setProjection(Projections.distinct(Projections.property("postTime")));
		List<Date> result = new ArrayList<Date>();
		List tempList = getHibernateTemplate().findByCriteria(criteria);
		for (int i=0; i<tempList.size(); i++) {
			Date date = (Date)tempList.get(i);
			if (!result.contains(DateUtil.getMonthStartTime(date))) {
				result.add(DateUtil.getMonthStartTime(date));
			}
		}
		return result;
	}
}
