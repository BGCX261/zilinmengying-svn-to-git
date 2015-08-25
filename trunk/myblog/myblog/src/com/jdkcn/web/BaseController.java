/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		_BaseController.java
 * Class:			_BaseController
 * Date:			Oct 7, 2006
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Oct 7, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.util.WebUtils;

import com.jdkcn.BlogFacade;
import com.jdkcn.domain.User;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Oct 7, 2006
 * @version $Id _BaseController.java$
 */
public abstract class BaseController extends AbstractController {
	
	private String formView;

	private String successView;
	
	private String indexView;
	
	protected BlogFacade blogFacade;
	
	public void setBlogFacade(BlogFacade blogFacade) {
		this.blogFacade = blogFacade;
	}

	public String getFormView() {
		return formView;
	}

	public void setFormView(String formView) {
		this.formView = formView;
	}

	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}
	
	public String getIndexView() {
		return indexView;
	}

	public void setIndexView(String indexView) {
		this.indexView = indexView;
	}

	/**
	 * Convenience method for getting a i18n key's value. Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable is
	 * not set in unit tests b/c there's no DispatchServlet Request.
	 * 
	 * @param msgKey
	 * @return
	 */
	public String getText(String msgKey) {
		return getMessageSourceAccessor().getMessage(msgKey);
	}

	/**
	 * Convenient method for getting a i18n key's value with a single string
	 * argument.
	 * 
	 * @param msgKey
	 * @param arg
	 * @return
	 */
	public String getText(String msgKey, String arg) {
		return getText(msgKey, new Object[] { arg });
	}

	/**
	 * Convenience method for getting a i18n key's value with arguments.
	 * 
	 * @param msgKey
	 * @param args
	 * @return
	 */
	public String getText(String msgKey, Object[] args) {
		return getMessageSourceAccessor().getMessage(msgKey, args);
	}
	
	/**
	 * get the login user.
	 * @param request
	 * @return the logined user or null when no user login.
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	protected User getLoginUser(HttpServletRequest request){
		if(request.getSession()==null || request.getSession().getAttribute(Constants.AUTH_USER)==null) {
			return null;
		}else{
			return (User)request.getSession().getAttribute(Constants.AUTH_USER);
		}
	}
	
	/**
	 * 向View层传递message时将message放入httpSession的messages变量中.
	 * 放在session中能保证message即使Redirect也不会消失。需配合{@link com.bidlink.core.web.MessageFilter MessageFilter}使用
	 */
	@SuppressWarnings("unchecked")
	protected void saveMessage(HttpServletRequest request, String message) {
		if (StringUtils.isNotBlank(message)) {
			List<String> messages = (List<String>) WebUtils.getOrCreateSessionAttribute(request
					.getSession(), "messages", ArrayList.class);
			messages.add(message);
		}
	}
	
	/**
	 * 如果value不为空，加入model里面。
	 * @param parameterName
	 * @param parameterValue
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	protected void addModel(String parameterName, String parameterValue, Map<String,Object> model){
		if (StringUtils.isNotBlank(parameterValue)) {
			model.put(parameterName, parameterValue);
		}
	}
}
