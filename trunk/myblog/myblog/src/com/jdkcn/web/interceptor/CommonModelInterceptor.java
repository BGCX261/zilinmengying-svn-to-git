/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		CommonModelInterceptor.java
 * Class:			CommonModelInterceptor
 * Date:			Jan 5, 2007 11:37:28 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Jan 5, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jdkcn.BlogFacade;
import com.jdkcn.util.Constants;

/**
 * this is for inject the common models,such as
 * <pre>
 *  siteConfig, categories.
 * </pre>
 * so that another controller no need put this models each time.
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Jan 5, 2007 11:37:28 AM
 * @version $Id CommonModelInterceptor.java$
 */
public class CommonModelInterceptor extends HandlerInterceptorAdapter {
	
	private BlogFacade blogFacade;
	
	
	public void setBlogFacade(BlogFacade blogFacade) {
		this.blogFacade = blogFacade;
	}


	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		Boolean notInterceptor = null;
		if(modelAndView!=null && modelAndView.getModel()!=null){
			notInterceptor = (Boolean)modelAndView.getModel().get(Constants.DO_NOT_INTERCEPT);
		}
		if(notInterceptor!=null && notInterceptor){
			modelAndView.getModel().remove(Constants.DO_NOT_INTERCEPT);
			return;
		}
		if(modelAndView==null) {
			return;
		}
		modelAndView.addObject("siteConfig", blogFacade.getDatabaseSiteConfig());
		modelAndView.addObject("categories", blogFacade.getCategories());
		modelAndView.addObject("recommendLinks", blogFacade.getRecommendLinks());
		modelAndView.addObject("pages", blogFacade.getPublishedPages());
		modelAndView.addObject("archiveMonthList", blogFacade.getMonthList());
		modelAndView.addObject("recentEntries", blogFacade.getRecentEntries(Constants.DEFAULT_RECENT_ENTRIES_SIZE));
		modelAndView.addObject("recentComments", blogFacade.getRecentComments(Constants.DEFAULT_RECENT_COMMENTS_SIZE));
		modelAndView.addObject("hotTags", blogFacade.getHotTags(Constants.DEFAULT_HOT_TAGS_SIZE));
		
	}
}
