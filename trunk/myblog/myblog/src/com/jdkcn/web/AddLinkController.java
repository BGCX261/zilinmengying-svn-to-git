/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		AddLinkController.java
 * Class:			AddLinkController
 * Date:			Mar 29, 2007 11:24:00 AM
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
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Link;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Mar 29, 2007 11:24:00 AM
 * @version $Id AddLinkController.java$
 */
public class AddLinkController extends BaseController {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String url = request.getParameter("url");
		String imgUrl = request.getParameter("imgUrl");
		String order = request.getParameter("order");
		String recommend = request.getParameter("recommend");
		ModelAndView mv = new ModelAndView();
		List<String> errors = new ArrayList<String>();
		if (METHOD_GET.equals(request.getMethod())) {
			mv.setViewName(getFormView());
			return mv;
		}
		if (StringUtils.isBlank(name)) {
			errors.add(getText("errors.required", new Object[] { getText("link.name") }));
		} else {
			mv.addObject("name", name);
		}
		if (StringUtils.isBlank(url)) {
			errors.add(getText("errors.required", new Object[] { getText("link.url") }));
		} else {
			mv.addObject("url", url);
		}
		if (StringUtils.isNotBlank(order) && !NumberUtils.isNumber(order)) {
			errors.add(getText("errors.integer", new Object[] {getText("link.order")}));
		}
		mv.addObject("imgUrl", imgUrl);
		mv.addObject("description", description);
		mv.addObject("order", order);
		mv.addObject("recommend", recommend);
		if (!errors.isEmpty()) {
			mv.addObject("errors", errors);
			mv.setViewName(getFormView());
			return mv;
		}
		Link link = new Link();
		link.setName(name);
		link.setURL(url);
		if (StringUtils.isNotBlank(imgUrl)) {
			link.setImgURL(imgUrl);
		}
		link.setCreateTime(new Date());
		link.setDescription(description);
		if (NumberUtils.isNumber(order)) {
			link.setOrder(Integer.parseInt(order));
		}
		link.setRecommend(Boolean.valueOf(recommend));
		blogFacade.saveOrUpdateLink(link);
		saveMessage(request, getText("msg.add.link.success"));
		mv.clear();
		mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		mv.setViewName(getSuccessView());
		return mv;
	}

}
