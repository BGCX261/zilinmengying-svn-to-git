/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		TagDao.java
 * Class:			TagDao
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

import java.util.List;

import com.jdkcn.domain.Tag;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 16, 2006
 * @version $Id TagDao.java$
 */
public interface TagDao extends BaseDao<Tag> {
	
	/**
	 * get all of the tags.
	 * @return the list of all tags
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Tag> getTags();
	
	/**
	 * Get the tag by tag's name
	 * @param name tag's name
	 * @return The tag
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Tag getTagByName(String name);
	
	/**
	 * Get the hot tags.
	 * @param size
	 * @return the tag list.
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Tag> getHotTags(int size);
}
