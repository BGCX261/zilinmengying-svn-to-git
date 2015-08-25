/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		EntryController.java
 * Class:			EntryController
 * Date:			Nov 29, 2006 10:27:19 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Nov 29, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Comment;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.User;
import com.jdkcn.util.Constants;
import com.jdkcn.util.CookieUtil;
import com.jdkcn.util.MyblogUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Nov 29, 2006 10:27:19 PM
 * @version $Id EntryController.java$
 */
public class EntryController extends BaseController {
	
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
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			// insure that incoming data is parsed as UTF-8
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new ServletException("Can't set incoming encoding to UTF-8");
		}
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		Map<String, Object> model = new HashMap<String, Object>();
		Entry entry = null;
		String url = request.getParameter(TRACKBACK_URL_PARAM);
		String title = request.getParameter(TRACKBACK_TITLE_PARAM);
		String excerpt = request.getParameter(TRACKBACK_EXCERPT_PARAM);
		String blogName = request.getParameter(TRACKBACK_BLOG_NAME_PARAM);
		if(StringUtils.isBlank(title)) {
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
		if(StringUtils.isNotBlank(id)) {
			entry = blogFacade.getEntry(id);
		}else if(StringUtils.isNotBlank(name)){
			entry = blogFacade.getEntryByName(name);
		}
		/**
		 * if trackback.
		 */
		if(title !=null && url !=null && excerpt != null && blogName != null){
			PrintWriter pw = new PrintWriter(response.getOutputStream());
			String error = null;
			if (entry==null) {
				error = "no entry found with:[" + name + "] or [" + id + "]";
			}else {
				Comment comment = new Comment();
				comment.setAuthorName(blogName);
				comment.setPostIP(request.getRemoteAddr());
				comment.setAgent(request.getHeader("user-agent"));
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
				return null;
			}
			if (error != null) {
				pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				pw.println("<response>");
				pw.println("<error>1</error>");
				pw.println("<message>ERROR: " + error + "</message>");
				pw.println("</response>");
				pw.flush();
				return null;
			}
		}
		if (entry != null && entry.getType().equals(Entry.Type.PAGE)) {
			entry = null;
		}
		model.put("entry", entry);
		if(entry!=null) {
			/**
			 * if not the author view,update the hits.
			 */
			User loginUser = (User)request.getSession().getAttribute(Constants.AUTH_USER);
			if(loginUser==null || !loginUser.getId().equals(entry.getAuthor().getId())) {
				entry.setHits(entry.getHits()+1);
				blogFacade.saveOrUpdateEntry(entry);
			}
			entry.setSummary(MyblogUtil.truncateNicely(MyblogUtil.removeHTML(entry.getContent()), 200, 220, "..."));
			model.put("comments", blogFacade.getCommentListByEntryIdAndStatus(entry.getId(), Comment.Status.APPROVED));
			model.put("previous", blogFacade.getPreviousEntry(entry.getId()));
			model.put("next", blogFacade.getNextEntry(entry.getId()));
			model.put("authorName", CookieUtil.getCookieValue(request, Constants.AUTHORNAME_COOKIE_KEY, null));
			model.put("authorMail", CookieUtil.getCookieValue(request, Constants.AUTHORMAIL_COOKIE_KEY, null));
			model.put("authorSite", CookieUtil.getCookieValue(request, Constants.AUTHORSITE_COOKIE_KEY, null));
			model.put("relatedEntries", blogFacade.getRelatedEntries(entry, Constants.DEFAULT_RELATED_ENTRIES_SIZE));
		}
		return new ModelAndView(getSuccessView(), model);
	}

}
