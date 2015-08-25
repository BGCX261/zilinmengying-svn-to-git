/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		EditPageController.java
 * Class:			EditPageController
 * Date:			Aug 18, 2007 5:27:51 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Aug 18, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.WebContentGenerator;

import com.jdkcn.domain.Entry;
import com.jdkcn.domain.Tag;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Aug 18, 2007 5:27:51 PM
 * @version $Id EditPageController.java$
 */
public class EditPageController extends BaseController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isBlank(id)) {
			mav.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			mav.setViewName(getIndexView());
			return mav;
		}
		Entry entry = blogFacade.getEntry(id);
		if (entry==null) {
			mav.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			mav.setViewName(getIndexView());
			return mav;
		}
		if (WebContentGenerator.METHOD_GET.equalsIgnoreCase(request.getMethod())) {
			mav.addObject("entry", entry);
			mav.setViewName(getFormView());
			return mav;
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String tags = request.getParameter("tags");
		String commentStatus = request.getParameter("commentStatus");
		String name = request.getParameter("name");
		String publish = request.getParameter("publish");
		List<String> errors = new ArrayList<String>();
		if (StringUtils.isBlank(title)) {
			errors.add(getText("errors.required", new Object[] { getText("page.title") }));
		} else {
			mav.addObject("title", title);
		}
		if (StringUtils.isBlank(content)) {
			errors.add(getText("errors.required", new Object[] { getText("page.content") }));
		} else {
			mav.addObject("content", content);
		}
		if (StringUtils.isBlank(name)) {
			errors.add(getText("errors.required", new Object[] { getText("page.name") }));
		} else {
			
			mav.addObject("name", name);
		}
		if (StringUtils.isBlank(commentStatus)) {
			mav.addObject("commentStatus", "close");
		} else {
			mav.addObject("commentStatus", commentStatus);
		}
		mav.addObject("tags", tags);
		if (!errors.isEmpty()) {
			mav.setViewName(getFormView());
			mav.addObject("id", id);
			mav.addObject("errors", errors);
			return mav;
		}
		entry.setTitle(title);
		entry.setName(name);
		entry.setContent(content);
		entry.setAuthor(getLoginUser(request));
		entry.setPostIP(request.getRemoteAddr());
		entry.setModifyTime(new Date());
		if (Entry.CommentStatus.OPEN.equalsIgnoreCase(commentStatus)) {
			entry.setCommentStatus(Entry.CommentStatus.OPEN);
		} else {
			entry.setCommentStatus(Entry.CommentStatus.CLOSE);
		}
		if (StringUtils.isNotBlank(publish)) {
			entry.setEntryStatus(Entry.EntryStatus.PUBLISH);
			mav.clear();
			mav.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			saveMessage(request, getText("msg.publish.page.success"));
		} else {
			mav.clear();
			mav.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			saveMessage(request, getText("msg.edit.page.success"));
		}
		if (StringUtils.isNotBlank(tags)) {
			tags = StringUtils.replace(tags, "，", ",");
			String[] tagArray = tags.split(",");
			entry.getTags().clear();
			for(String str:tagArray) {
				Tag tag = blogFacade.getTagByName(str);
				if(tag!=null) {
					entry.getTags().add(tag);
				}else{
					Tag newTag = new Tag();
					newTag.setName(str);
					blogFacade.saveOrUpdateTag(newTag);
					entry.getTags().add(newTag);
				}
			}
		}
		blogFacade.saveOrUpdatePage(entry);
		entry.setType(Entry.Type.PAGE);
		mav.addObject("id", entry.getId());
		mav.setViewName(getSuccessView());
		return mav;
	}

}
