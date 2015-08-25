/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		UserLoginController.java
 * Class:			UserLoginController
 * Date:			2006-9-14
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| 2006-9-14   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.WebContentGenerator;

import com.jdkcn.domain.User;
import com.jdkcn.exception.InvalidPasswordException;
import com.jdkcn.exception.InvalidUsernameException;
import com.jdkcn.util.Constants;
import com.jdkcn.util.CookieUtil;
import com.jdkcn.util.SHA;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-9-14
 * @version $Id UserLoginController.java$
 */
public class UserLoginController extends BaseController {
	
	private static final Log log = LogFactory.getLog(UserLoginController.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute(Constants.AUTH_USER)!=null) {
			return new ModelAndView(getSuccessView()).addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		} else {
			String username = CookieUtil.getCookieValue(request, "username", "");
			String password = CookieUtil.getCookieValue(request, "password", "");
			if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(username)) {
				try {
					User user = blogFacade.cookieLogin(username, password);
					session.setAttribute(Constants.AUTH_USER, user);
					return new ModelAndView(getSuccessView()).addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
				} catch (InvalidUsernameException ex) {
					// do noting.
				} catch (InvalidPasswordException ex) {
					// do noting.
				}
			}
		}
		if(WebContentGenerator.METHOD_GET.equals(request.getMethod())){
			return new ModelAndView(getFormView());
		}
		String sessionCode = (String)session.getAttribute(Constants.IDENTIFYING_CODE_SESSION_NAME);
		String redirectUrl = (String)session.getAttribute(Constants.ORIGINAL_URL);
        
		String code = request.getParameter("code");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberme = request.getParameter("rememberme");
		
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> errors = new ArrayList<String>();
		if (StringUtils.isBlank(code)) {
			errors.add(getText("errors.required", new Object[]{getText("login.code")}));
		}else {
			if (!code.equals(sessionCode)) {
				errors.add(getText("errors.codeerror"));
			}
			model.put("code", code);
		}
		if (StringUtils.isBlank(username)) {
			errors.add(getText("errors.required", new Object[] { getText("login.username")}));
		}else {
			model.put("username", username);
		}
		if (StringUtils.isBlank(password)) {
			errors.add(getText("errors.required", new Object[] { getText("login.password") }));
		}else{
			model.put("password", password);
		}
		if (StringUtils.isNotBlank(rememberme)) {
			model.put("rememberme", rememberme);
		}
		if (!errors.isEmpty()) {
			session = request.getSession(true);
			session.setAttribute(Constants.ORIGINAL_URL, redirectUrl);
			model.put("errors", errors);
			return new ModelAndView(getFormView(), model);
		}
		try {
			User user = blogFacade.login(username, password);
			session = request.getSession(true);
			session.setAttribute(Constants.AUTH_USER, user);
			if (log.isDebugEnabled()) {
				log.debug("User:" + user.getUsername() + "Logined.");
			}
			if ("forever".equalsIgnoreCase(rememberme)) {
				CookieUtil.setCookie(response, "username", username, Integer.MAX_VALUE);
				CookieUtil.setCookie(response, "password", SHA.hashPassword(user.getPassword()), Integer.MAX_VALUE);
			}
		} catch (InvalidUsernameException ex) {
			errors.add(getText("errors.invalid.usernameorpassword"));
		} catch (InvalidPasswordException e) {
			errors.add(getText("errors.invalid.usernameorpassword"));
		}
		if (!errors.isEmpty()) {
			model.put("errors", errors);
			session = request.getSession(true);
			session.setAttribute(Constants.ORIGINAL_URL, redirectUrl);
			return new ModelAndView(getFormView(), model);
		}
		if(StringUtils.isNotBlank(redirectUrl)) {
			response.sendRedirect(redirectUrl);
			return null;
		}
		ModelAndView mv = new ModelAndView(getSuccessView());
		mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		return mv;
	}

}
