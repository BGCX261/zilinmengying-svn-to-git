/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		TagController.java
 * Class:			TagController
 * Date:			Dec 29, 2006 2:19:27 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 29, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Entry;
import com.jdkcn.domain.Tag;
import com.jdkcn.util.Constants;
import com.jdkcn.util.PaginationSupport;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 29, 2006 2:19:27 PM
 * @version $Id TagController.java$
 */
public class TagController extends BaseController {
	
	private static final Log log = LogFactory.getLog(TagController.class);
	
	private String tagsView;
	
	public void setTagsView(String tagsView) {
		this.tagsView = tagsView;
	}
	
	public String getTagsView() {
		return tagsView;
	}
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isNotBlank(id)|| StringUtils.isNotBlank(name)) {
			String currentPage = request.getParameter("p");
			int intPage = 1;
			if(NumberUtils.isNumber(currentPage)) {
				intPage = Integer.parseInt(currentPage);
			}
			if(intPage<1){
				intPage = 1;
			}
			PaginationSupport<Entry> ps = null;
			if(StringUtils.isBlank(name)) {
				mv.addObject("tag", blogFacade.getTag(id));
				mv.addObject("id", id);
				ps = blogFacade.getEntryPageByTagId(id, Constants.DEFAULT_PAGE_SIZE, (intPage-1) * Constants.DEFAULT_PAGE_SIZE, null, null);
			}else{
				log.debug("[myblog]tag name:" + name);
				mv.addObject("tag", blogFacade.getTagByName(name));
				mv.addObject("name", name);
				ps = blogFacade.getEntryPageByTagName(name, Constants.DEFAULT_PAGE_SIZE, (intPage-1) * Constants.DEFAULT_PAGE_SIZE, null, null);
			}
			mv.addObject("ps", ps);
			mv.setViewName(getSuccessView());
			return mv;
		}else{
			List<Tag> tags = blogFacade.getTags();
			int totalCount = 0;
			for (Tag tag:tags) {
				totalCount += tag.getCount();
			}
			mv.addObject("tags", tags);
			mv.addObject("totalCount", totalCount);
			mv.setViewName(getTagsView());
			return mv;
		}
	}

}
