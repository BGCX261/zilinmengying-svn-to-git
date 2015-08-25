/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, leaf.jdk.cn
 * Filename:		UserLoginInterceptor.java
 * Module:			Dream
 * Class:			UserLoginInterceptor
 * Date:			2006-7-9 下午03:32:04
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| 2006-7-9   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jdkcn.BlogFacade;
import com.jdkcn.domain.User;
import com.jdkcn.exception.InvalidPasswordException;
import com.jdkcn.exception.InvalidUsernameException;
import com.jdkcn.util.Constants;
import com.jdkcn.util.CookieUtil;
import com.jdkcn.util.HttpUtil;


/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-7-9 03:32:04 PM
 * @version $Id UserLoginInterceptor.java$
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter {
	
	private String loginView;
	
	private BlogFacade blogFacade;
	
	public void setLoginView(String loginView) {
		this.loginView = loginView;
	}
	
	public void setBlogFacade(BlogFacade blogFacade) {
		this.blogFacade = blogFacade;
	}
	
	public String getLoginView() {
		return loginView;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		if (session != null) {
			User loginUser = (User)session.getAttribute(Constants.AUTH_USER);
			if(loginUser!=null) {
				return true;
			} else {
				// check cookie,if remember login.
				String username = CookieUtil.getCookieValue(request, "username", "");
				String password = CookieUtil.getCookieValue(request, "password", "");
				if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(username)) {
					try{
						User user = blogFacade.cookieLogin(username, password);
						session.setAttribute(Constants.AUTH_USER, user);
						return true;
					} catch (InvalidUsernameException ex){
						throw new ModelAndViewDefiningException(new ModelAndView(getLoginView()));
					} catch (InvalidPasswordException ex) {
						throw new ModelAndViewDefiningException(new ModelAndView(getLoginView()));
					}
				}
			}
			session.setAttribute(Constants.ORIGINAL_URL, HttpUtil.buildOriginalGETURL(request));
		}
		throw new ModelAndViewDefiningException(new ModelAndView(getLoginView()));
	}
}
