/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		CategoryController.java
 * Class:			CategoryController
 * Date:			Nov 30, 2006 10:37:39 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Nov 30, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Category;
import com.jdkcn.domain.Entry;
import com.jdkcn.util.Constants;
import com.jdkcn.util.PaginationSupport;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Nov 30, 2006 10:37:39 AM
 * @version $Id CategoryController.java$
 */
public class CategoryController extends BaseController {
	private static final Log log = LogFactory.getLog(CategoryController.class);
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String currentPage = request.getParameter("p");
		int intPage = 1;
		if(StringUtils.isNotBlank(currentPage)){
			try {
				intPage = Integer.parseInt(currentPage);
			} catch (Exception e) {
				log.error("parameter error: page must a number", e);
			}
		}
		if(intPage<1){
			intPage = 1;
		}
		Map<String, Object> model = new HashMap<String, Object>();
		PaginationSupport<Entry> ps = null;
		Category category = null;
		if(StringUtils.isNotBlank(id)) {
			ps = blogFacade.getEntryPageByCategoryId(id, Constants.DEFAULT_PAGE_SIZE, (intPage-1) * Constants.DEFAULT_PAGE_SIZE, null, null);
			category = blogFacade.getCategory(id);
		}else if(StringUtils.isNotBlank(name)) {
			ps = blogFacade.getEntryPageByCategoryName(name, Constants.DEFAULT_PAGE_SIZE, (intPage-1) * Constants.DEFAULT_PAGE_SIZE, null, null);
			category = blogFacade.getCategoryByName(name);
		}
		model.put("category", category);
		model.put("ps", ps);
		if(category!=null) {
			model.put("id", category.getId());
		}else{
			model.put("id", "");
		}
		model.put("name", name);
		return new ModelAndView(getSuccessView(), model);
	}

}
