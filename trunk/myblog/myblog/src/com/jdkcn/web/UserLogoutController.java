/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		UserLogoutController.java
 * Class:			UserLogoutController
 * Date:			Nov 26, 2006 8:09:17 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Nov 26, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.User;
import com.jdkcn.util.Constants;
import com.jdkcn.util.CookieUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Nov 26, 2006 8:09:17 PM
 * @version $Id UserLogoutController.java$
 */
public class UserLogoutController extends BaseController {
	
	private static final Log log = LogFactory.getLog(UserLogoutController.class);
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute(Constants.AUTH_USER);
		CookieUtil.setCookie(response, "username", "", 0);
		CookieUtil.setCookie(response, "password", "", 0);
		if(loginUser==null) {
			if(log.isDebugEnabled()) {
				log.debug("No user login,No need for logout!");
			}
		}else{
			if(log.isDebugEnabled()) {
				log.debug("User:"+loginUser.getUsername() + " logout!");
			}
			try {
				session.invalidate();
			} catch (Exception e) {
				log.error("session.invalidate() exception in UserLogoutController.", e);
			}
		}
		return new ModelAndView(getSuccessView()).addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
	}

}
