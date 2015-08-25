/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		ListPageController.java
 * Class:			ListPageController
 * Date:			Aug 18, 2007 5:49:11 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Aug 18, 2007   | Rory Ye	    | Created			|
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
 * @since Aug 18, 2007 5:49:11 PM
 * @version $Id ListPageController.java$
 */
public class ListPageController extends BaseController {

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
		entry.setType(Entry.Type.PAGE);
		PaginationSupport<Entry> ps = blogFacade.getEntryPage(entry, Constants.DEFAULT_PAGE_SIZE, (currentPage-1)*Constants.DEFAULT_PAGE_SIZE, null, null);
		ModelAndView mv = new ModelAndView();
		mv.addObject("ps", ps);
		mv.addObject("p", p);
		mv.setViewName(getSuccessView());
		return mv;
	}

}
