/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		CategoryDao.java
 * Class:			CategoryDao
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
 *
 * </pre>
 **/
package com.jdkcn.dao;

import java.util.List;

import com.jdkcn.domain.Category;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-9-15
 * @version $Id CategoryDao.java$
 */
public interface CategoryDao extends BaseDao<Category> {
	
	/**
	 * Get all of the categories
	 * @return the list of the categories
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Category> getCategories();
	
	/**
	 * get the category by category's name
	 * @param name category's name
	 * @return category
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Category getCategoryByName(String name);
	
	/**
	 * move the category's parent
	 * @param id
	 * @param from
	 * @param to
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void move(String id, String from, String to);
}
