/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		AddCategoryController.java
 * Class:			AddCategoryController
 * Date:			Nov 21, 2006 11:13:20 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Nov 21, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Category;
import com.jdkcn.util.Constants;
import com.jdkcn.util.TextUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Nov 21, 2006 11:13:20 PM
 * @version $Id AddCategoryController.java$
 */
public class AddCategoryController extends BaseController {
	
	private static final Log log = LogFactory.getLog(AddCategoryController.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> errors = new ArrayList<String>();
		if(StringUtils.isBlank(name)) {
			errors.add(getText("errors.required", new Object[]{getText("category.name")}));
		}else{
			model.put("name", name);
		}
		if(StringUtils.isNotBlank(description)) {
			model.put("description", description);
		}
		if(!errors.isEmpty()) {
			model.put("errors", errors);
			return new ModelAndView(getFormView(), model);
		}
		try {
			Category category = new Category();
			category.setName(TextUtil.escapeHTML(name));
			category.setDescription(description);
			category.setOrder(0);
			category.setDefaultCategory(false);
			category.setCreateTime(new Date());
			category.setSecret(false);
			blogFacade.saveOrUpdateCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("save category error",e);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName(getSuccessView());
		saveMessage(request, getText("msg.add.category.success"));
		mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		return mv;
	}

}
