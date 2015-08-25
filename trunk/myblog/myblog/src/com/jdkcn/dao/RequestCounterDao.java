/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		RequestCounterDao.java
 * Class:			RequestCounterDao
 * Date:			Dec 6, 2006 11:14:52 AM
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
package com.jdkcn.dao;

import java.util.List;

import com.jdkcn.domain.Entry;
import com.jdkcn.domain.RequestCounter;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 6, 2006 11:14:52 AM
 * @version $Id RequestCounterDao.java$
 */
public interface RequestCounterDao extends BaseDao<RequestCounter> {
	
	/**
	 * get the entry's request referer.<br/>
	 * only contains the search engine's <i>Google.com</i> <i>Baidu.com</i>
	 * @param entry
	 * @param size the size of referer's count.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<RequestCounter> getRequestList(Entry entry, int size);
	
	/**
	 * get the request referer by the uri.
	 * @param uri
	 * @param size
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<RequestCounter> getRequestListByUri(String uri, int size);
}
