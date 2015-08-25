/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		EditCategoryController.java
 * Class:			EditCategoryController
 * Date:			Jan 23, 2007 9:46:49 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Jan 23, 2007   | Rory Ye	    | Created			|
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

import com.jdkcn.domain.Category;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Jan 23, 2007 9:46:49 PM
 * @version $Id EditCategoryController.java$
 */
public class EditCategoryController extends BaseController {

	private String listView;
	
	public void setListView(String listView) {
		this.listView = listView;
	}

	public String getListView() {
		return listView;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)) {
			return new ModelAndView(getIndexView());
		}
		String save = request.getParameter("save");
		Category category = blogFacade.getCategory(id);
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isNotBlank(save) && "remove".equals(save)){
			blogFacade.removeCategory(id);
			mv.setViewName(getListView());
			mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			saveMessage(request, getText("msg.remove.category.success"));
			return mv;
		}else if(StringUtils.isNotBlank(save)&& "save".equals(save)){
			List<String> errors = new ArrayList<String>();
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			if(StringUtils.isBlank(name)) {
				errors.add(getText("errors.required", new Object[]{getText("category.name")}));
			}else{
				mv.addObject("name", name);
			}
			if(!errors.isEmpty()) {
				mv.addObject("errors", errors);
				mv.setViewName(getFormView());
				return mv;
			}
			try {
				category.setName(name);
				category.setDescription(description);
				blogFacade.saveOrUpdateCategory(category);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			category.setName(StringEscapeUtils.unescapeHtml(category.getName()));
			mv.addObject("category", category);
			mv.setViewName(getFormView());
			return mv;
		}
		mv.addObject("id", id);
		saveMessage(request, getText("msg.edit.category.success"));
		mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		mv.setViewName(getSuccessView());
		return mv;
	}

}
