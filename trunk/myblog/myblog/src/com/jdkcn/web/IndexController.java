/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		WelcomeController.java
 * Class:			WelcomeController
 * Date:			Nov 26, 2006 8:20:55 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Nov 26, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Entry;
import com.jdkcn.util.Constants;
import com.jdkcn.util.PaginationSupport;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Nov 26, 2006 8:20:55 PM
 * @version $Id WelcomeController.java$
 */
public class IndexController extends BaseController {

	private static final Log log = LogFactory.getLog(IndexController.class);
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		Entry entry = new Entry();
		entry.setEntryStatus(Entry.EntryStatus.PUBLISH);
		entry.setType(Entry.Type.POST);
		PaginationSupport<Entry> ps = blogFacade.getEntryPage(entry, Constants.DEFAULT_PAGE_SIZE, (intPage-1) * Constants.DEFAULT_PAGE_SIZE, null, null);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ps", ps);
		return new ModelAndView(getSuccessView(), model);
	}

}
