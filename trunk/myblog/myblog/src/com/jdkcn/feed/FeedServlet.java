/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		FeedServlet.java
 * Class:			FeedServlet
 * Date:			Dec 5, 2006 1:28:05 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 5, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.feed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 5, 2006 1:28:05 PM
 * @version $Id FeedServlet.java$
 */
public class FeedServlet extends HttpServlet {
	
	private static final Log log = LogFactory.getLog(FeedServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -7046613043831432514L;

	private static final String DEFAULT_FEED_TYPE = "default.feed.type";

	private static final String FEED_TYPE = "type";
	
	private static final String MIME_TYPE = "application/xml; charset=UTF-8";
	
	private static final String COULD_NOT_GENERATE_FEED_ERROR = "Could not generate feed";
	
	private String defaultFeedType;
	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		defaultFeedType = getServletConfig().getInitParameter(DEFAULT_FEED_TYPE);
		defaultFeedType = defaultFeedType!=null ? defaultFeedType : "rss_2.0";
	}
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SyndFeed feed = getFeedWriter().getFeed(DEFAULT_FEED_TYPE);
			String feedType = request.getParameter(FEED_TYPE);
			feedType = (feedType != null) ? feedType : defaultFeedType;
			feed.setFeedType(feedType);
		
			response.setContentType(MIME_TYPE);
			SyndFeedOutput output = new SyndFeedOutput();
			response.setHeader("Pragma","No-Cache");
			response.setHeader("Cache-Control","No-Cache");
			response.setDateHeader("Expires",0);
			output.output(feed, response.getWriter());
		} catch (FeedException e) {
			log.error(COULD_NOT_GENERATE_FEED_ERROR, e);
			log(COULD_NOT_GENERATE_FEED_ERROR, e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, COULD_NOT_GENERATE_FEED_ERROR);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private FeedWriter getFeedWriter() {
		return (FeedWriter) WebApplicationContextUtils.getWebApplicationContext(getServletConfig().getServletContext()).getBean("feedWriter");
	}
}
