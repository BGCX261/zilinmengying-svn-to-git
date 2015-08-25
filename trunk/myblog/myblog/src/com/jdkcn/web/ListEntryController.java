/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		ListEntryController.java
 * Class:			ListEntryController
 * Date:			Nov 23, 2006 10:38:18 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Nov 23, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Entry;
import com.jdkcn.util.Constants;
import com.jdkcn.util.PaginationSupport;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Nov 23, 2006 10:38:18 PM
 * @version $Id ListEntryController.java$
 */
public class ListEntryController extends BaseController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String p = request.getParameter("p");
		int currentPage = 1;
		if(NumberUtils.isNumber(p)){
			currentPage = Integer.parseInt(p);
		}
		if(currentPage<1){
			currentPage = 1;
		}
		Entry entry = new Entry();
		entry.setType(Entry.Type.POST);
		PaginationSupport<Entry> page = blogFacade.getEntryPage(entry, Constants.DEFAULT_PAGE_SIZE, (currentPage-1)*Constants.DEFAULT_PAGE_SIZE, null, null);
		ModelAndView mv = new ModelAndView();
		mv.addObject("page", page);
		mv.addObject("p", p);
		mv.setViewName(getSuccessView());
		return mv;
	}

}
