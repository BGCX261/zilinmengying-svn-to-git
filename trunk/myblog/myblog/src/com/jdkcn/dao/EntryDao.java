/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		EntryDao.java
 * Class:			EntryDao
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

import java.util.Date;
import java.util.List;

import com.jdkcn.domain.Entry;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-9-15
 * @version $Id EntryDao.java$
 */
public interface EntryDao extends BaseDao<Entry> {
	/**
	 * get entry by entry's name
	 * @param name the entry's name
	 * @return Entry
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Entry getEntryByName(String name);
	
	/**
	 * get the recent entries.
	 * @param size
	 * @return the entry list
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Entry> getRecentEntries(int size);
	
	/**
	 * get the next entry by the current entry's id ,it's means older than the current entry.
	 * @param id the current entry's id
	 * @return the nexe entry
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Entry getNextEntry(String id);
	
	/**
	 * get the previous entry by the current entry's id, it's means newer than the current entry.
	 * @param id the current entry's id
	 * @return the previous entry
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Entry getPreviousEntry(String id);
	
	/**
	 * get the related entries.
	 * @param entry
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Entry> getRelatedEntries(Entry entry, int size);
	
	/**
	 * get all of the pages.<br/>
	 * Entry's type is <i>page</i>
	 * @param entryStatus the entryStatus.if null got all of the pages.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Entry> getPages(String entryStatus);
	
	/**
	 * get the month list for archive.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Date> getMonthList();
}
