/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		CategoryDaoHibernateImpl.java
 * Class:			CategoryDaoHibernateImpl
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
package com.jdkcn.dao.hibernate3;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jdkcn.dao.CategoryDao;
import com.jdkcn.domain.Category;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-9-15
 * @version $Id CategoryDaoHibernateImpl.java$
 */
public class CategoryDaoHibernateImpl extends BaseHibernateDaoSupport<Category>
		implements CategoryDao {
	private static final Log log = LogFactory.getLog(CategoryDaoHibernateImpl.class);
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.CategoryDao#getCategories()
	 */
	@SuppressWarnings("unchecked")
	public List<Category> getCategories() {
		return getHibernateTemplate().find("from Category");
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.CategoryDao#getCategoryByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Category getCategoryByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
		criteria.add(Restrictions.eq("name", name));
		List result = getHibernateTemplate().findByCriteria(criteria);
		if(result.isEmpty()){
			return null;
		}else if(result.size()>1) {
			log.error("[myblog]:there are " + result.size() + " categories found with name:" + name + ", but unique required!");
		}
		return (Category) result.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.jdkcn.dao.CategoryDao#move(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void move(String id, String from, String to) {
		Category cate = get(id);
		//move out
		Category parent = get(from);
		parent.getChildren().remove(cate);
		saveOrUpdate(parent);
		cate.setParent(null);
		//move in
		cate.setParent(get(to));
		saveOrUpdate(cate);
		Category newParent = get(to);
		newParent.getChildren().add(cate);
		saveOrUpdate(newParent);
	}
}
