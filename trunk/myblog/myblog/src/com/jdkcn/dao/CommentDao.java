/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		CommentDao.java
 * Class:			CommentDao
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

import com.jdkcn.domain.Comment;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 16, 2006
 * @version $Id CommentDao.java$
 */
public interface CommentDao extends BaseDao<Comment> {

	/**
	 * get the recent comments,the status is approved.
	 * @param size the comment's size
	 * @return the comments
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Comment> getRecentComments(int size);
	
	/**
	 * get the comment list by entry's id
	 * @param entryId the entry's id
	 * @return the entry's comment list
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Comment> getCommentListByEntryId(String entryId);
	
	/**
	 * 
	 * @param entryId
	 * @param status
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Comment> getCommentListByEntryIdAndStatus(String entryId, String status);
	
	/**
	 * get the distinct email about the entry's subscribe comment.
	 * @param entryId
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<String> getSubscribeEntryCommentEmails(String entryId);
}