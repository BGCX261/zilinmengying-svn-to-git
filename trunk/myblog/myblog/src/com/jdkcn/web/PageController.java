/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		PageController.java
 * Class:			PageController
 * Date:			Aug 19, 2007 11:13:45 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Aug 19, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Entry;
import com.jdkcn.domain.User;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Aug 19, 2007 11:13:45 AM
 * @version $Id PageController.java$
 */
public class PageController extends BaseController {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isBlank(id) && StringUtils.isBlank(name)) {
			mav.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			mav.setViewName(getIndexView());
			return mav;
		}
		Entry page = null;
		if (StringUtils.isNotBlank(id)) {
			page = blogFacade.getEntry(id);
		}
		if (StringUtils.isNotBlank(name)) {
			page = blogFacade.getEntryByName(name);
		}
		if (page.getType().equals(Entry.Type.POST)) {
			page = null;
		}
		if (page!=null) {
			/**
			 * if not the author view,update the hits.
			 */
			User loginUser = getLoginUser(request);
			if(loginUser==null || !loginUser.getId().equals(page.getAuthor().getId())) {
				page.setHits(page.getHits()+1);
				blogFacade.saveOrUpdatePage(page);
			}
			mav.addObject("page", page);
		}
		mav.setViewName(getSuccessView());
		return mav;
	}

}
