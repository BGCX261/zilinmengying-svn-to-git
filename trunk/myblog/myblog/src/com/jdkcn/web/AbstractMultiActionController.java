/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		AbstractMultiActionController.java
 * Class:			AbstractMultiActionController
 * Date:			Jan 8, 2007 10:55:24 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Jan 8, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import com.jdkcn.util.DateUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Jan 8, 2007 10:55:24 PM
 * @version $Id AbstractMultiActionController.java$
 */
public abstract class AbstractMultiActionController extends MultiActionController {
	/**
	 * 不设置任何action参数时的默认 Action. 该函数名由xxx-servlet.xml配置文件中的
	 * methodNameResolver节点配置.
	 */
	public abstract ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * 初始化binder的回调函数. 默认以DateUtil中的日期格式设置DateEditor及允许Integer,Double的字符串为空.
	 * 
	 * @see MultiActionController#createBinder(HttpServletRequest,Object)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil
				.getDatePattern());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(
				Long.class, true));
	}

	/**
	 * 从Request中绑定对象并进行校验. Spring MVC中的Bind函数未完全符合需求,因此参考原代码进行了扩展
	 * 
	 * @return 校验错误
	 * @see #preBind(HttpServletRequest,Object,ServletRequestDataBinder)
	 */
	protected BindException bindObject(HttpServletRequest request,
			Object command) throws Exception {
		Assert.notNull(command);

		// 创建Binder
		ServletRequestDataBinder binder = createBinder(request, command);
		// 回调函数，供子类扩展对binder做出更进一步设置,并进行不能由binder自动完成的绑定
		preBind(request, command, binder);

		// 绑定
		binder.bind(request);

		// 校验
		Validator[] validators = getValidators();
		if (validators != null) {
			for (int i = 0; i < validators.length; i++) {
				Validator validator = validators[i];
				if (validator.supports(command.getClass())) {
					ValidationUtils.invokeValidator(validator, command, binder
							.getErrors());
				}
			}
		}
		return binder.getErrors();
	}

	/**
	 * 回调函数，在BindObject的最开始调用。负责 1.继续对binder进行设置
	 * 2.绑定一些不能由Binder自动绑定的属性.这些属性通常是需要查询数据库来获得对象的绑定. 要注意设置这些属性为disallow. eg.
	 * 
	 * <pre>
	 * binder.setDisallowedFields(new String[] { &quot;image&quot;, &quot;category&quot; });
	 * </pre>
	 * 
	 * @see #bindObject(HttpServletRequest, Object)
	 * @see org.springframework.web.bind.ServletRequestDataBinder#setDisallowedFields(String[])
	 */
	protected void preBind(HttpServletRequest request, Object object,
			ServletRequestDataBinder binder) throws Exception {
		// 在子类实现
	}

	/**
	 * 回调函数，声明CommandName--对象的名字,默认为首字母小写的类名.
	 * 
	 * @see #bindObject(HttpServletRequest, Object)
	 */
	protected String getCommandName(Object command) {
		return StringUtils.uncapitalize(command.getClass().getSimpleName());
	}

	/**
	 * 增加validator. 除了Spring配置文件注入的validators数组外,可以用此函数在子类的代码里再添加新的validator.
	 */
	protected void addValidator(Validator validator) {
		ArrayUtils.add(getValidators(), validator);
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
	 * 直接输出纯字符串
	 */
	public void renderText(HttpServletResponse response, String content) {
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 直接输出纯HTML
	 */
	public void renderHtml(HttpServletResponse response, String content) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 直接输出纯XML
	 */
	public void renderXML(HttpServletResponse response, String content) {
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			logger.error(e);
		}
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
}
