/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		LinkDaoHibernateImpl.java
 * Class:			LinkDaoHibernateImpl
 * Date:			Mar 29, 2007 10:52:59 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Mar 29, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.dao.hibernate3;

import java.util.List;

import com.jdkcn.dao.LinkDao;
import com.jdkcn.domain.Link;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Mar 29, 2007 10:52:59 AM
 * @version $Id LinkDaoHibernateImpl.java$
 */
public class LinkDaoHibernateImpl extends BaseHibernateDaoSupport<Link> implements LinkDao {

	/* (non-Javadoc)
	 * @see com.jdkcn.dao.LinkDao#getLinks()
	 */
	@SuppressWarnings("unchecked")
	public List<Link> getLinks() {
		return getHibernateTemplate().find("from Link l order by l.order");
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.LinkDao#getRecommendLinks()
	 */
	@SuppressWarnings("unchecked")
	public List<Link> getRecommendLinks() {
		return getHibernateTemplate().find("from Link l where l.recommend=? order by l.order", Boolean.TRUE);
	}
	
}
