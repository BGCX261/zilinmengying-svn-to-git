/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		UserDao.java
 * Class:			UserDao
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
package com.jdkcn.dao;


import com.jdkcn.domain.User;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 16, 2006
 * @version $Id UserDao.java$
 */
public interface UserDao extends BaseDao<User> {
	/**
	 * get user by username
	 * @param username
	 * @return the persistence
	 * @throws org.springframework.dao.DataAccessException in case of Hibernate errors and not unique result found
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public User getUserByUsername(String username);
}
