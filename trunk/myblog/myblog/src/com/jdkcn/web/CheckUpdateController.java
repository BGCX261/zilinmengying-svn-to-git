/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		CheckUpdateController.java
 * Class:			CheckUpdateController
 * Date:			Sep 26, 2007 9:47:35 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Sep 26, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;


/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 26, 2007 9:47:35 PM
 * @version $Id CheckUpdateController.java$
 */
public class CheckUpdateController extends BaseController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = "http://jdkcn.com/checkUpdateNew.jsp?ver=" + blogFacade.getDatabaseSiteConfig().getAppVersion();
		response.setCharacterEncoding("UTF-8");
		URLConnection connection = new URL(url).openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line!=null) {
			sb.append(line).append("\r\n");
			line = reader.readLine();
		}
		response.getWriter().println(sb.toString());
		return null;
	}

}
