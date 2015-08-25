/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		RemovePageController.java
 * Class:			RemovePageController
 * Date:			Aug 18, 2007 8:15:33 PM
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

import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Aug 18, 2007 8:15:33 PM
 * @version $Id RemovePageController.java$
 */
public class RemovePageController extends BaseController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("ids");
		ModelAndView mav = new ModelAndView();
		if(ids==null||ids.length<1) {
			mav.setViewName(getIndexView());
			mav.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			return mav;
		}
		for (String id:ids) {
			blogFacade.removePage(id);
		}
		mav.clear();
		mav.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		mav.setViewName(getSuccessView());
		return mav;
	}

}
