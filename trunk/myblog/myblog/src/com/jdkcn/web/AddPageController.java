/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		AddPageController.java
 * Class:			AddPageController
 * Date:			Aug 18, 2007 12:37:17 PM
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

import com.jdkcn.domain.Entry;
import com.jdkcn.domain.Tag;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Aug 18, 2007 12:37:17 PM
 * @version $Id AddPageController.java$
 */
public class AddPageController extends BaseController {

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
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		if (METHOD_GET.equalsIgnoreCase(request.getMethod())) {
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
		mav.addObject("tags", tags);
		if (StringUtils.isBlank(commentStatus)) {
			mav.addObject("commentStatus", "close");
		} else {
			mav.addObject("commentStatus", commentStatus);
		}
		if (!errors.isEmpty()) {
			mav.setViewName(getFormView());
			mav.addObject("errors", errors);
			return mav;
		}
		Entry entry = new Entry();
		entry.setTitle(title);
		entry.setName(name);
		entry.setContent(content);
		entry.setAuthor(getLoginUser(request));
		entry.setPostIP(request.getRemoteAddr());
		entry.setPostTime(new Date());
		entry.setHits(0);
		entry.setCommentSize(0);
		entry.setTrackbackSize(0);
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
			mav.setViewName(getListView());
		} else {
			entry.setEntryStatus(Entry.EntryStatus.DRAFT);
			mav.clear();
			mav.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			saveMessage(request, getText("msg.add.page.success"));
			mav.setViewName(getSuccessView());
		}
		if (StringUtils.isNotBlank(tags)) {
			tags = StringUtils.replace(tags, "，", ",");
			String[] tagArray = tags.split(",");
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
		entry.setType(Entry.Type.PAGE);
		blogFacade.saveOrUpdatePage(entry);
		mav.addObject("id", entry.getId());
		return mav;
	}

}
