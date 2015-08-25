/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		ListLinkController.java
 * Class:			ListLinkController
 * Date:			Mar 29, 2007 3:30:01 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Mar 29, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Mar 29, 2007 3:30:01 PM
 * @version $Id ListLinkController.java$
 */
public class ListLinkController extends BaseController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("links", blogFacade.getLinks());
		mv.setViewName(getSuccessView());
		return mv;
	}

}
