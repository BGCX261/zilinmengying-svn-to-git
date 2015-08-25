/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		CommentDaoHibernateImpl.java
 * Class:			CommentDaoHibernateImpl
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jdkcn.dao.CommentDao;
import com.jdkcn.domain.Comment;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 16, 2006
 * @version $Id CommentDaoHibernateImpl.java$
 */
public class CommentDaoHibernateImpl extends BaseHibernateDaoSupport<Comment>
		implements CommentDao {
	private Log log = LogFactory.getLog(CommentDaoHibernateImpl.class);
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.CommentDao#getRecentComments(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getRecentComments(int size) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Comment.class);
		criteria.add(Restrictions.eq("status", Comment.Status.APPROVED));
		criteria.addOrder(Order.desc("postTime"));
		List<Comment> comments = getHibernateTemplate().findByCriteria(criteria, -1, size);
		for (Comment comment:comments) {
			Hibernate.initialize(comment.getEntry());
			log.debug("[myblog]:force initialize comment's entry.");
		}
		return comments;
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.CommentDao#getCommentListByEntryId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getCommentListByEntryId(String entryId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Comment.class);
		criteria.createCriteria("entry").add(Restrictions.eq("id", entryId));
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.CommentDao#getCommentListByEntryIdAndStatus(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getCommentListByEntryIdAndStatus(String entryId, String status) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Comment.class);
		criteria.add(Restrictions.eq("status", status));
		criteria.addOrder(Order.asc("postTime"));
		criteria.createCriteria("entry").add(Restrictions.eq("id", entryId));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.dao.CommentDao#getSubscribeEntryCommentEmails(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSubscribeEntryCommentEmails(String entryId) {
		return getHibernateTemplate()
				.find("select distinct (comment.authorMail)from Comment comment where comment.entry.id=? and comment.isSubscribe = ?",
						new Object[] { entryId, true });
	}
}
