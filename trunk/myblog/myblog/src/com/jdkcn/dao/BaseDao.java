/**
 * <pre>
 * Copyright:		Copyright(C) 2006, jdkcn.com
 * Filename:		BaseDao.java
 * Class:			BaseDao
 * Date:			Sep 10, 2006 5:31:06 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">Rory</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Sep 10, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jdkcn.util.PaginationSupport;

/**
 * @author <a href="rory.cn@gmail.com">Rory</a>
 * @since Sep 10, 2006 5:31:06 PM
 * @version $id:BaseDao.java $
 */
public interface BaseDao<T> {
	
	/**
	 * Save or update the domain
	 * @param domain Domain to saveOrUpdate
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdate(T domain);
	
	/**
	 * Remove the domain from database
	 * @param domain domain to remove
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void remove(T domain);

	/**
	 * @param id
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public T get(Serializable id);
	
	/**
	 * find object by pagination supprot
	 * @param detachedCriteria 
	 * @param pageSize
	 * @param startIndex
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public PaginationSupport<T> findPageByCriteria(DetachedCriteria detachedCriteria, int pageSize, int startIndex);
	
	/**
	 * Get list by criteria
	 * @param detachedCriteria the domain query criteria, include condition and the orders.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<T> getListByCriteria(DetachedCriteria detachedCriteria);
	
	/**
	 * Get list by criteria
	 * @param detachedCriteria
	 * @param offset
	 * @param size
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<T> getListByCriteria(DetachedCriteria detachedCriteria, int offset, int size);
}
