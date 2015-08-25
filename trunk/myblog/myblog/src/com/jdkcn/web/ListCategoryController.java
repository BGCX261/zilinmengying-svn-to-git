/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		ListCategoryController.java
 * Class:			ListCategoryController
 * Date:			Nov 21, 2006 11:35:16 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Nov 21, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Category;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Nov 21, 2006 11:35:16 PM
 * @version $Id ListCategoryController.java$
 */
public class ListCategoryController extends BaseController {
	
	private static final Log log = LogFactory.getLog(ListCategoryController.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("list category controller.");
		List<Category> categories = blogFacade.getCategories();
		return new ModelAndView(getSuccessView(), "categories", categories);
	}

}
