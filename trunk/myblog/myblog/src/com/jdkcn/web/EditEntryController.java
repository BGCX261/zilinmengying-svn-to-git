/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		EditEntryController.java
 * Class:			EditEntryController
 * Date:			Dec 2, 2006 9:31:16 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 2, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Category;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.Tag;
import com.jdkcn.util.Constants;
import com.jdkcn.util.MyblogUtil;
import com.jdkcn.util.TextUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 2, 2006 9:31:16 PM
 * @version $Id EditEntryController.java$
 */
public class EditEntryController extends BaseController {
	
	public String index;
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String p = request.getParameter("p");
		String id = request.getParameter("id");
		String publish = request.getParameter("publish");
		if(StringUtils.isBlank(id)) {
			return new ModelAndView(getIndex());
		}
		String save = request.getParameter("save");
		Entry entry = blogFacade.getEntry(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		if(StringUtils.isNotBlank(save) && "remove".equals(save)) {
			blogFacade.removeEntry(id);
			if(StringUtils.isNotBlank(p)) {
				mv.addObject("p", p);
			}
			mv.setViewName(getIndexView());
			saveMessage(request, getText("msg.remove.entry.success"));
			mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			return mv;
		}else if(StringUtils.isNotBlank(save) && "save".equals(save)){
			List<String> errors = new ArrayList<String>();
			String title = request.getParameter("title");
			String comeFrom = request.getParameter("comeFrom");
			String comeFromURL = request.getParameter("comeFromURL");
			String tags = request.getParameter("tags");
			String name = request.getParameter("name");
			String[] cates = request.getParameterValues("cates");
			String content = request.getParameter("content");
			String summary = request.getParameter("summary");
			if(StringUtils.isBlank(title)) {
				errors.add(getText("errors.required", new Object[]{getText("entry.title")}));
			}else{
				mv.addObject("title", title);
			}
			if(StringUtils.isBlank(content)) {
				errors.add(getText("errors.required", new Object[]{getText("entry.content")}));
			} else{
				mv.addObject("content", content);
			}
			if(cates==null || cates.length<1) {
				errors.add(getText("errors.required", new Object[]{getText("entry.category")}));
			}else{
				mv.addObject("cates", cates);
			}
			if(StringUtils.isNotBlank(comeFrom)) {
				mv.addObject("comeFrom", comeFrom);
			}
			if(StringUtils.isNotBlank(comeFromURL)) {
				mv.addObject("comeFromURL", comeFromURL);
			}
			if(StringUtils.isNotBlank(tags)) {
				mv.addObject("tags", tags);
			}
			if (StringUtils.isNotBlank(summary)) {
				mv.addObject("summary", summary);
			}
			if(StringUtils.isNotBlank(name)) {
				mv.addObject("name", name);
				if(!name.equals(entry.getName())) {
					Entry exists = blogFacade.getEntryByName(name);
					if(exists!=null) {
						errors.add(getText("errors.entryname.exists", new Object[]{name}));
					}
				}
			}else{
				name = null;
			}
			if(!errors.isEmpty()){
				mv.addObject("errors", errors);
				mv.addObject("categories", blogFacade.getCategories());
				mv.setViewName(getFormView());
				return mv;
			}
			try {
				entry.setTitle(title);
				entry.setComeFrom(comeFrom);
				entry.setComeFromURL(comeFromURL);
				entry.setContent(content);
				entry.setModifyTime(new Date());
				entry.setName(name);
				if (StringUtils.isNotBlank(summary)) {
					entry.setSummary(summary);
				} else {
					entry.setSummary(MyblogUtil.truncateNicely(MyblogUtil.removeHTML(entry.getContent()), blogFacade.getDatabaseSiteConfig().getLimitLength(), blogFacade.getDatabaseSiteConfig().getLimitLength()+20, "..."));
				}
				Set<Tag> oldTags = new HashSet<Tag>(); 
				oldTags.addAll(entry.getTags());
				if(StringUtils.isNotBlank(tags)) {
					tags = StringUtils.replace(tags, "，", ",");
					String[] ts = StringUtils.split(tags, ",");
					List<Tag> tagList = blogFacade.getTags();
					entry.getTags().clear();
					for(String str:ts) {
						boolean isNew = true;
						if(StringUtils.isNotBlank(str)){
							for(Tag t:tagList){
								if(str.toLowerCase().equalsIgnoreCase(StringEscapeUtils.unescapeHtml(t.getName()).toLowerCase())){
									isNew = false;
									if (oldTags.contains(t)) {
										oldTags.remove(t);
									} else {
										t.setCount(t.getCount()+1);
										blogFacade.saveOrUpdateTag(t);
									}
									entry.getTags().add(t);
								}
							}
							if(isNew) {
								Tag tag = new Tag();
								tag.setName(TextUtil.escapeHTML(str));
								tag.setCount(1);
								blogFacade.saveOrUpdateTag(tag);
								entry.getTags().add(tag);
							}
						}
					}
				} else {
					entry.getTags().clear();
				}
				for (Tag t:oldTags) {
					if (t.getCount()>0) {
						t.setCount(t.getCount()-1);
						blogFacade.saveOrUpdateTag(t);
					}
				}
				Set<Category> oldCategories = new HashSet<Category>();
				oldCategories.addAll(entry.getCategories());
				entry.getCategories().clear();
				for(String str:cates) {
					Category cate = blogFacade.getCategory(str);
					if (oldCategories.contains(cate)) {
						oldCategories.remove(cate);
					} else {
						cate.setCount(cate.getCount()+1);
						blogFacade.saveOrUpdateCategory(cate);
					}
					entry.getCategories().add(cate);
				}
				for (Category cate:oldCategories) {
					if (cate.getCount()>0) {
						cate.setCount(cate.getCount()-1);
						blogFacade.saveOrUpdateCategory(cate);
					}
				}
				if(StringUtils.isNotBlank(publish)&&entry.getEntryStatus().equals(Entry.EntryStatus.DRAFT)) {
					entry.setEntryStatus(Entry.EntryStatus.PUBLISH);
				}
				entry.setType(Entry.Type.POST);
				blogFacade.saveOrUpdateEntry(entry);
			} catch (Exception e) {
				e.printStackTrace();
				errors.add(e.getMessage());
				mv.addObject("errors", errors);
				mv.setViewName(getFormView());
				return mv;
			}
		}else{
			/**
			 * return to modify unescape html the title
			 */
			entry.setTitle(StringEscapeUtils.unescapeHtml(entry.getTitle()));
			mv.addObject("entry", entry);
			mv.addObject("categories", blogFacade.getCategories());
			mv.setViewName(getFormView());
			return mv;
		}
		mv.getModel().clear();
		mv.addObject("id", id);
		saveMessage(request, getText("msg.edit.entry.success"));
		mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		mv.setViewName(getSuccessView());
		return mv;
	}

}
