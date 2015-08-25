/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		EditLinkController.java
 * Class:			EditLinkController
 * Date:			Mar 29, 2007 3:47:46 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Mar 29, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.WebContentGenerator;

import com.jdkcn.domain.Link;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Mar 29, 2007 3:47:46 PM
 * @version $Id EditLinkController.java$
 */
public class EditLinkController extends BaseController {
	
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
		String id = request.getParameter("id");
		ModelAndView mv = new ModelAndView();
		if (StringUtils.isBlank(id)) {
			mv.setViewName(getIndexView());
			return mv;
		}else{
			mv.addObject("id", id);
		}
		String save = request.getParameter("save");
		Link link = blogFacade.getLink(id);
		if(link==null) {
			mv.setViewName(getIndexView());
			return mv;
		}
		if (WebContentGenerator.METHOD_GET.equals(request.getMethod())&&!"remove".equals(save)) {
			mv.addObject("link", link);
			mv.setViewName(getFormView());
			return mv;
		}
		if ("save".equals(save)) {
			List<String> errors = new ArrayList<String>();
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String url = request.getParameter("url");
			String imgUrl = request.getParameter("imgUrl");
			String order = request.getParameter("order");
			String recommend = request.getParameter("recommend");
			if(StringUtils.isBlank(name)) {
				errors.add(getText("errors.required", new Object[]{getText("link.name")}));
			} else {
				mv.addObject("name", name);
			}
			if(StringUtils.isBlank(url)) {
				errors.add(getText("errors.required", new Object[]{getText("link.url")}));
			} else {
				mv.addObject("url", url);
			}
			mv.addObject("description", description);
			mv.addObject("imgUrl", imgUrl);
			mv.addObject("order", order);
			mv.addObject("recommend", recommend);
			if(StringUtils.isNotBlank(order) && !NumberUtils.isNumber(order)) {
				errors.add(getText("errors.integer", new Object[] {getText("link.order")}));
			}
			if(!errors.isEmpty()){
				mv.addObject("errors", errors);
				mv.setViewName(getFormView());
				return mv;
			}
			link.setName(name);
			link.setDescription(description);
			link.setURL(url);
			if (StringUtils.isNotBlank(imgUrl)) {
				link.setImgURL(imgUrl);
			}else{
				link.setImgURL(null);
			}
			if (NumberUtils.isNumber(order)) {
				link.setOrder(Integer.parseInt(order));
			}
			link.setRecommend(Boolean.valueOf(recommend));
			blogFacade.saveOrUpdateLink(link);
		} else if ("remove".equals(save)) {
			blogFacade.removeLink(id);
			mv.setViewName(getListView());
			mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			saveMessage(request, getText("msg.remove.link.success"));
			return mv;
		}
		mv.clear();
		mv.addObject("id", id);
		saveMessage(request, getText("msg.edit.link.success"));
		mv.setViewName(getSuccessView());
		return mv;
	}

}
