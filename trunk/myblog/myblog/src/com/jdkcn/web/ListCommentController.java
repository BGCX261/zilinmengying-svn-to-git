/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		ListCommentController.java
 * Class:			ListCommentController
 * Date:			Mar 27, 2007 6:10:45 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Mar 27, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Comment;
import com.jdkcn.util.Constants;
import com.jdkcn.util.PaginationSupport;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Mar 27, 2007 6:10:45 PM
 * @version $Id ListCommentController.java$
 */
public class ListCommentController extends BaseController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String p = request.getParameter("p");
		int currentPage = 1;
		if(StringUtils.isNotBlank(p)&&NumberUtils.isNumber(p)){
			currentPage = Integer.parseInt(p);
		}
		if(currentPage<1){
			currentPage = 1;
		}
		PaginationSupport<Comment> ps = blogFacade.getCommentPage(new Comment(), Constants.DEFAULT_PAGE_SIZE, (currentPage-1) * Constants.DEFAULT_PAGE_SIZE, null, null);
		ModelAndView mv = new ModelAndView();
		mv.addObject("ps", ps);
		mv.addObject("p", p);
		mv.setViewName(getSuccessView());
		return mv;
	}

}
