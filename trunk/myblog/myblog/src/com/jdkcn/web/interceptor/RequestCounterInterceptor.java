/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		RequestCounterInterceptor.java
 * Class:			RequestCounterInterceptor
 * Date:			Aug 19, 2007 12:52:56 PM
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
package com.jdkcn.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jdkcn.BlogFacade;
import com.jdkcn.domain.RequestCounter;
import com.jdkcn.util.HttpUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Aug 19, 2007 12:52:56 PM
 * @version $Id RequestCounterInterceptor.java$
 */
public class RequestCounterInterceptor extends HandlerInterceptorAdapter {
	
	private BlogFacade blogFacade;
	
	public void setBlogFacade(BlogFacade blogFacade) {
		this.blogFacade = blogFacade;
	}


	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		RequestCounter counter = new RequestCounter();
		counter.setIp(request.getRemoteAddr());
		counter.setUserAgent(request.getHeader("user-agent"));
		counter.setReferer(request.getHeader("referer"));
		counter.setRequestTime(new Date());
		counter.setUri(HttpUtil.buildOriginalGETURL(request));
		blogFacade.saveOrUpdateRequestCounter(counter);
	}
}
