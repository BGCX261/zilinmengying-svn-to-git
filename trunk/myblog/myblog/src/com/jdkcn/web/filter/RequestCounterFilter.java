/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		RequestCounterFilter.java
 * Class:			RequestCounterFilter
 * Date:			Dec 6, 2006 1:41:26 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 6, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jdkcn.BlogFacade;
import com.jdkcn.domain.RequestCounter;
import com.jdkcn.util.HttpUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 6, 2006 1:41:26 PM
 * @version $Id RequestCounterFilter.java$
 */
public class RequestCounterFilter extends OncePerRequestFilter {

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		RequestCounter counter = new RequestCounter();
		counter.setIp(request.getRemoteAddr());
		counter.setUserAgent(request.getHeader("user-agent"));
		counter.setReferer(request.getHeader("referer"));
		counter.setRequestTime(new Date());
		counter.setUri(HttpUtil.buildOriginalGETURL(request));
		if(!counter.getUri().contains("/feed/"))
			getBlogFacade().saveOrUpdateRequestCounter(counter);
		filterChain.doFilter(request, response);
	}
	
	private BlogFacade getBlogFacade(){
		return (BlogFacade)WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("blogFacade");
	}
}
