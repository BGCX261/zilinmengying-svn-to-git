/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		TagDaoHibernateImpl.java
 * Class:			TagDaoHibernateImpl
 * Date:			Sep 16, 2006
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Sep 16, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.dao.hibernate3;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jdkcn.dao.TagDao;
import com.jdkcn.domain.Tag;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 16, 2006
 * @version $Id TagDaoHibernateImpl.java$
 */
public class TagDaoHibernateImpl extends BaseHibernateDaoSupport<Tag> implements
		TagDao {
	private static final Log log = LogFactory.getLog(TagDaoHibernateImpl.class);
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.TagDao#getTags()
	 */
	@SuppressWarnings("unchecked")
	public List<Tag> getTags() {
		return getHibernateTemplate().find("from Tag");
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.TagDao#getTagByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Tag getTagByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Tag.class);
		criteria.add(Restrictions.eq("name", name));
		List result = getHibernateTemplate().findByCriteria(criteria);
		if(result.isEmpty()) {
			return null;
		}else if(result.size()>1) {
			log.error("[myblog]:there are " + result.size() + " tags found with the name:" + name + ",but unique required!");
		}
		return (Tag)result.get(0);
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.dao.TagDao#getHotTags(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Tag> getHotTags(int size) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Tag.class);
		criteria.addOrder(Order.desc("count"));
		return getHibernateTemplate().findByCriteria(criteria, -1, size);
	}
}
