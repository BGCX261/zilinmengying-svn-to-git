/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		AddEntryController.java
 * Class:			AddEntryController
 * Date:			Nov 23, 2006 1:22:53 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Nov 23, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Category;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.Tag;
import com.jdkcn.domain.User;
import com.jdkcn.util.Constants;
import com.jdkcn.util.MyblogUtil;
import com.jdkcn.util.TextUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Nov 23, 2006 1:22:53 PM
 * @version $Id AddEntryController.java$
 */
public class AddEntryController extends BaseController {
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
		String save = request.getParameter("save");
		String publish = request.getParameter("publish");
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> errors = new ArrayList<String>();
		model.put("categories", blogFacade.getCategories());
		if (METHOD_GET.equals(request.getMethod())) {
			return new ModelAndView(getFormView(), model);
		}
		String title = request.getParameter("title");
		String comeFrom = request.getParameter("comeFrom");
		String comeFromURL = request.getParameter("comeFromURL");
		String tags = request.getParameter("tags");
		String name = request.getParameter("name");
		String[] cates = request.getParameterValues("cates");
		String content = request.getParameter("content");
		String summary = request.getParameter("summary");
		String quotes = request.getParameter("quotes");
		String postTime = request.getParameter("postTime");
		String id = null;
		if (StringUtils.isBlank(title)) {
			errors.add(getText("errors.required",
					new Object[] { getText("entry.title") }));
		} else {
			model.put("title", title);
		}
		if (StringUtils.isBlank(content)) {
			errors.add(getText("errors.required",
					new Object[] { getText("entry.content") }));
		} else {
			model.put("content", content);
		}
		if (cates == null || cates.length < 1) {
			errors.add(getText("errors.required",
					new Object[] { getText("entry.category") }));
		} else {
			model.put("cates", cates);
		}
		addModel("comeFrom", comeFrom, model);
		addModel("comeFromURL", comeFromURL, model);
		addModel("tags", tags, model);
		addModel("summary", summary, model);
		if (StringUtils.isNotBlank(name)) {
			model.put("name", name);
			Entry entry = blogFacade.getEntryByName(name);
			if (entry != null) {
				errors.add(getText("errors.entryname.exists",
						new Object[] { name }));
			}
		} else {
			name = null;
		}
		Date pDate = null;
		if (StringUtils.isNotBlank(postTime)) {
			model.put("postTime", postTime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try{
				pDate = sdf.parse(postTime);
			} catch (ParseException ex) {
				errors.add(getText("errors.date", new Object[]{postTime}));
			}
		}
		if (!errors.isEmpty()) {
			model.put("errors", errors);
			return new ModelAndView(getFormView(), model);
		}
		try {
			User loginUser = (User) request.getSession().getAttribute(
					Constants.AUTH_USER);
			Entry entry = new Entry();
			entry.setTitle(TextUtil.escapeHTML(title));
			entry.setComeFrom(comeFrom);
			entry.setComeFromURL(comeFromURL);
			entry.setContent(content);
			entry.setAuthor(loginUser);
			entry.setPostIP(request.getRemoteAddr());
			if (pDate!=null) {
				entry.setPostTime(pDate);
			} else {
				entry.setPostTime(new Date());
			}
			entry.setTrackbackSize(0);
			entry.setCommentSize(0);
			entry.setName(name);
			entry.setHits(0);
			if (StringUtils.isNotBlank(summary)) {
				entry.setSummary(summary);
			} else {
				entry.setSummary(MyblogUtil.truncateNicely(MyblogUtil.removeHTML(entry.getContent()), blogFacade.getDatabaseSiteConfig().getLimitLength(), blogFacade.getDatabaseSiteConfig().getLimitLength()+20, "..."));
			}
			if (StringUtils.isNotBlank(quotes)) {
				quotes = StringUtils.replace(quotes, "，", ",");
				String[] qs = StringUtils.split(quotes, ",");
				entry.setQuotes(Arrays.asList(qs));
			}
			if (StringUtils.isNotBlank(tags)) {
				tags = StringUtils.replace(tags, "，", ",");
				String[] ts = StringUtils.split(tags, ",");
				List<Tag> tagList = blogFacade.getTags();
				for (String str : ts) {
					boolean isNew = true;
					if (StringUtils.isNotBlank(str)) {
						for (Tag t : tagList) {
							if (str.toLowerCase().equalsIgnoreCase(StringEscapeUtils.unescapeHtml(t.getName()).toLowerCase())) {
								isNew = false;
								t.setCount(t.getCount()+1);
								blogFacade.saveOrUpdateTag(t);
								entry.getTags().add(t);
							}
						}
						if (isNew) {
							Tag tag = new Tag();
							tag.setCount(1);
							tag.setName(TextUtil.escapeHTML(str));
							blogFacade.saveOrUpdateTag(tag);
							entry.getTags().add(tag);
						}
					}
				}
			}
			for (String str : cates) {
				Category cate = blogFacade.getCategory(str);
				cate.setCount(cate.getCount()+1);
				blogFacade.saveOrUpdateCategory(cate);
				entry.getCategories().add(cate);
			}
			if(StringUtils.isNotBlank(save)&&StringUtils.isBlank(publish)) {
				entry.setEntryStatus(Entry.EntryStatus.DRAFT);
			} else {
				entry.setEntryStatus(Entry.EntryStatus.PUBLISH);
			}
			entry.setType(Entry.Type.POST);
			blogFacade.saveOrUpdateEntry(entry);
			id = entry.getId();
			// send trackback.
			if (entry.getQuotes() != null && !entry.getQuotes().isEmpty()) {
				blogFacade.sendTrackback(entry.getId(), entry.getQuotes());
			}
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			model.put("errors", errors);
			return new ModelAndView(getFormView(), model);
		}
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isNotBlank(save)&&StringUtils.isBlank(publish)) {
			mv.setViewName(getSuccessView());
			mv.addObject("id", id);
			saveMessage(request, getText("msg.add.entry.success"));
			mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		}else{
			mv.setViewName(getListView());
			saveMessage(request, getText("msg.add.entry.success"));
			mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		}
		return mv;
	}

}
