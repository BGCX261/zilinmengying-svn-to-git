/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		ListUserController.java
 * Class:			ListUserController
 * Date:			Nov 23, 2006 10:35:15 PM
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

import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.User;
import com.jdkcn.util.PaginationSupport;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Nov 23, 2006 10:35:15 PM
 * @version $Id ListUserController.java$
 */
public class ListUserController extends BaseController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PaginationSupport<User> page = blogFacade.getUserPage(new User(), 50, 0, "", false);
		return new ModelAndView(getSuccessView(), "page", page);
	}

}
