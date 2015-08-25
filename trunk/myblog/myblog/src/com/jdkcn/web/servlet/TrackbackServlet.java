/*
 */
package com.jdkcn.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jdkcn.BlogFacade;
import com.jdkcn.domain.Comment;
import com.jdkcn.domain.Entry;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-6-2 10:44:10
 */
public class TrackbackServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -106557282263289271L;

	/** Request parameter to indicate a trackback "tb" */
	// private static final String TRACKBACK_PARAM = "tb";
	/** Request parameter for the trackback "title" */
	private static final String TRACKBACK_TITLE_PARAM = "title";

	/** Request parameter for the trackback "excerpt" */
	private static final String TRACKBACK_EXCERPT_PARAM = "excerpt";

	/** Request parameter for the trackback "url" */
	private static final String TRACKBACK_URL_PARAM = "url";

	/** Request parameter for the trackback "blog_name" */
	private static final String TRACKBACK_BLOG_NAME_PARAM = "blog_name";

	/**
	 * Key under which the trackback return code will be placed (example: on the
	 * request for the JSPDispatcher)
	 */
	public static final String BLOJSOM_TRACKBACK_RETURN_CODE = "BLOJSOM_TRACKBACK_RETURN_CODE";

	/**
	 * Key under which the trackback error message will be placed (example: on
	 * the request for the JSPDispatcher)
	 */
	public static final String BLOJSOM_TRACKBACK_MESSAGE = "BLOJSOM_TRACKBACK_MESSAGE";

	/** Trackback success page */
	// private static final String TRACKBACK_SUCCESS_PAGE = "trackback-success";
	/** Trackback failure page */
	// private static final String TRACKBACK_FAILURE_PAGE = "trackback-failure";
	/**
	 * Constructor.
	 */
	public TrackbackServlet() {
		super();
	}

	/**
	 * POSTing to this Servlet will add a Trackback to a Weblog Entrty.
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * POSTing to this Servlet will add a Trackback to a Weblog Entrty.
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			// insure that incoming data is parsed as UTF-8
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new ServletException("Can't set incoming encoding to UTF-8");
		}
		
		String url = req.getParameter(TRACKBACK_URL_PARAM);
		String title = req.getParameter(TRACKBACK_TITLE_PARAM);
		String excerpt = req.getParameter(TRACKBACK_EXCERPT_PARAM);
		String blogName = req.getParameter(TRACKBACK_BLOG_NAME_PARAM);

		if ((title == null) || "".equals(title)) {
			title = url;
		}
		if (excerpt == null) {
			excerpt = "";
		} else {
			if (excerpt.length() >= 255) {
				excerpt = excerpt.substring(0, 252);
				excerpt += "...";
			}
		}
		String error = null;
		
		String uri = req.getRequestURI();
		int index = uri.lastIndexOf('/');
		if (index == -1 || uri.substring(index +1).length()!=32) {
			error = "parameter error,no entry id";
		}
		PrintWriter pw = new PrintWriter(res.getOutputStream());
		try {
			if (title == null || url == null || excerpt == null
					|| blogName == null) {
				error = "title, url, excerpt, and blog_name not specified.";
			} else {
				
				BlogFacade blogFacade = (BlogFacade)WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("blogFacade");
				Entry entry = blogFacade.getEntry(uri.substring(index+1));
				
				if (entry==null) {
					error = "no entry found with:" + uri.substring(index +1 );
				}else {
					Comment comment = new Comment();
					comment.setAuthorName(blogName);
					comment.setPostIP(req.getRemoteAddr());
					comment.setAgent(req.getHeader("user-agent"));
					comment.setAuthorSite(url);
					comment.setContent("<strong>" + title + "#8230;</strong><p>" + excerpt + "</p>");
					comment.setType(Comment.Type.TRACKBACK);
					comment.setStatus(Comment.Status.WAIT_FOR_APPROVE);
					comment.setPostTime(new Date());
					comment.setEntry(entry);
					blogFacade.saveOrUpdateComment(comment);
					pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					pw.println("<response>");
					pw.println("<error>0</error>");
					pw.println("</response>");
					pw.flush();
				}
			}

		} catch (Exception e) {
			error = e.getMessage();
			if (error == null) {
				error = e.getClass().getName();
			}
		}

		if (error != null) {
			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			pw.println("<response>");
			pw.println("<error>1</error>");
			pw.println("<message>ERROR: " + error + "</message>");
			pw.println("</response>");
			pw.flush();
		}
		res.flushBuffer();

	}

}
