/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		LinkDao.java
 * Class:			LinkDao
 * Date:			Mar 29, 2007 10:52:10 AM
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
package com.jdkcn.dao;

import java.util.List;

import com.jdkcn.domain.Link;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Mar 29, 2007 10:52:10 AM
 * @version $Id LinkDao.java$
 */
public interface LinkDao extends BaseDao<Link> {
	
	/**
	 * get all the links.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Link> getLinks();
	
	/**
	 * get the recommend links.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Link> getRecommendLinks();
}
