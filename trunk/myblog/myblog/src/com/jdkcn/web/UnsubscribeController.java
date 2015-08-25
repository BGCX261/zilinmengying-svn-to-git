/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		UnsubscribeController.java
 * Class:			UnsubscribeController
 * Date:			Sep 25, 2007 10:22:08 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Sep 25, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Comment;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 25, 2007 10:22:08 PM
 * @version $Id UnsubscribeController.java$
 */
public class UnsubscribeController extends BaseController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		List<String> errors = new ArrayList<String>();
		ModelAndView mav = new ModelAndView(getSuccessView());
		if (StringUtils.isBlank(id)||StringUtils.isBlank(email)) {
			errors.add(getText("errors.missing.parameters"));
			mav.addObject("errors", errors);
		} else {
			List<Comment> comments = blogFacade.getCommentListByEntryId(id);
			for (Comment comment:comments) {
				if (comment.getAuthorMail().equals(email)) {
					comment.setIsSubscribe(Boolean.FALSE);
					blogFacade.saveOrUpdateComment(comment);
				}
			}
			mav.addObject("msg", getText("msg.unsubscribe.success"));
		}
		return mav;
	}

}
