package com.jdkcn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.util.Constants;

public class RemoveCommentController extends BaseController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("ids");
		String p = request.getParameter("p");
		ModelAndView mav = new ModelAndView();
		if (ids==null||ids.length<1) {
			mav.setViewName(getIndexView());
			mav.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			return mav;
		}
		for (String id:ids) {
			blogFacade.removeComment(id);
		}
		mav.clear();
		mav.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		mav.addObject("p", p);
		mav.setViewName(getSuccessView());
		return mav;
	}

}
