package com.jdkcn.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.util.Constants;

public class ArchiveController extends BaseController {
	
	private Log log = LogFactory.getLog(ArchiveController.class);
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String month = request.getParameter("m");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String currentPage = request.getParameter("p");
		int intPage = 1;
		if(StringUtils.isNotBlank(currentPage)){
			try {
				intPage = Integer.parseInt(currentPage);
			} catch (Exception e) {
				log.error("parameter error: page must a number", e);
			}
		}
		if(intPage<1){
			intPage = 1;
		}
		Date date = null;
		ModelAndView mav = new ModelAndView();
		try {
			date = sdf.parse(month);
		} catch (ParseException ex) {
			log.error("parse date error:", ex);
			mav.setViewName(getSuccessView());
			List<String> errors = new ArrayList<String>();
			errors.add(getText("errors.date",month));
			mav.addObject("errors", errors);
			return mav;
		}
		mav.addObject("ps", blogFacade.getEntryPageByMonth(date, Constants.DEFAULT_PAGE_SIZE, (intPage-1)*Constants.DEFAULT_PAGE_SIZE, null, null));
		mav.addObject("month", month);
		mav.setViewName(getSuccessView());
		return mav;
	}

}
