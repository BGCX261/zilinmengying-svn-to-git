/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		UserDaoHibernateImpl.java
 * Class:			UserDaoHibernateImpl
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

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jdkcn.dao.UserDao;
import com.jdkcn.domain.User;
import com.jdkcn.exception.BlogException;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 16, 2006
 * @version $Id UserDaoHibernateImpl.java$
 */
public class UserDaoHibernateImpl extends BaseHibernateDaoSupport<User>
		implements UserDao {
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.UserDao#getUserByUsername(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username){
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("username", username));
		List<User> users = getHibernateTemplate().findByCriteria(criteria);
		if (users.isEmpty()) {
			return null;
		}else if (users.size()>1) {
			throw new BlogException("there are:" + users.size() + " found, but unique required!");
		}
		return users.get(0);
	}
}
