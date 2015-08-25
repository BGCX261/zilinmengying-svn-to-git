/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		NoCacheHeaderInterceptor.java
 * Class:			NoCacheHeaderInterceptor
 * Date:			Mar 31, 2007 8:27:34 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Mar 31, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Mar 31, 2007 8:27:34 PM
 * @version $Id NoCacheHeaderInterceptor.java$
 */
public class NoCacheHeaderInterceptor extends HandlerInterceptorAdapter {
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		response.setHeader("Pragma","No-Cache");
		response.setHeader("Cache-Control","No-Cache");
		response.setDateHeader("Expires",0);
	}
	
}
