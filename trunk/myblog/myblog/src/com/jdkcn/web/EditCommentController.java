/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		EditCommentController.java
 * Class:			EditCommentController
 * Date:			Mar 28, 2007 2:53:23 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Mar 28, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Comment;
import com.jdkcn.util.Constants;
import com.jdkcn.util.TextUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Mar 28, 2007 2:53:23 PM
 * @version $Id EditCommentController.java$
 */
public class EditCommentController extends BaseController {

	private String listView;

	public String getListView() {
		return listView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String p = request.getParameter("p");
		String id = request.getParameter("id");
		ModelAndView mv = new ModelAndView();
		if (StringUtils.isBlank(id)) {
			mv.setViewName(getIndexView());
			return mv;
		}
		String save = request.getParameter("save");
		Comment comment = blogFacade.getComment(id);
		if (StringUtils.isNotBlank(save) && "remove".equals(save)) {
			blogFacade.removeComment(id);
			if(StringUtils.isNotBlank(p)){
				mv.addObject("p", p);
			}
			mv.setViewName(getListView());
			saveMessage(request, getText("msg.remove.comment.success"));
			mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			return mv;
		} else if (StringUtils.isNotBlank(save) && "save".equals(save)) {
			List<String> errors = new ArrayList<String>();
			String content = request.getParameter("content");
			if (StringUtils.isBlank(content)) {
				errors.add(getText("errors.required", new Object[] { getText("comment.content") }));
			} else {
				mv.addObject("content", content);
			}
			if(!errors.isEmpty()) {
				mv.addObject("errors", errors);
				mv.setViewName(getFormView());
				return mv;
			}
			comment.setContent(TextUtil.escapeHTML(content));
			blogFacade.saveOrUpdateComment(comment);
		} else {
			comment.setContent(StringEscapeUtils.unescapeHtml(comment.getContent()));
			mv.addObject("comment", comment);
			mv.setViewName(getFormView());
			return mv;
		}
		mv.addObject("id", id);
		mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		saveMessage(request, getText("msg.edit.comment.success"));
		mv.setViewName(getSuccessView());
		return mv;
	}

}
