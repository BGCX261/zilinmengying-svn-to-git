/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		MailController.java
 * Class:			MailController
 * Date:			Dec 28, 2006 8:44:13 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 28, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Mail;
import com.jdkcn.service.MailService;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 28, 2006 8:44:13 AM
 * @version $Id MailController.java$
 */
public class MailController extends BaseController {
	
	private MailService mailService;
	
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}


	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String save = request.getParameter("save");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String content = request.getParameter("content");
		String subject = request.getParameter("subject");
		List<String> errors = new ArrayList<String>();
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isBlank(save)) {
			mv.setViewName(getFormView());
			return mv;
		}else if("schedulerSend".equalsIgnoreCase(save)){
			mailService.schedulerSend();
			mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			mv.setViewName(getSuccessView());
			return mv;
		}else{
			if(StringUtils.isBlank(from)) {
				errors.add(getText("errors.required", new Object[]{getText("mail.from")}));
			}else{
				mv.addObject("from", from);
			}
			if(StringUtils.isBlank(to)) {
				errors.add(getText("errors.required", new Object[]{getText("mail.to")}));
			}else{
				mv.addObject("to", to);
			}
			if(StringUtils.isBlank(subject)) {
				errors.add(getText("errors.required", new Object[]{getText("mail.subject")}));
			}else{
				mv.addObject("subject", subject);
			}
			if(StringUtils.isBlank(content)) {
				errors.add(getText("errors.required", new Object[]{getText("mail.content")}));
			}else{
				mv.addObject("content", content);
			}
			if(!errors.isEmpty()){
				mv.addObject("errors", errors);
				mv.setViewName(getFormView());
			}else{
				Mail mail = new Mail();
				mail.setFrom(new String[]{from});
				mail.setTo(new String[]{to});
				mail.setSubject(subject);
				StringBuffer sb = new StringBuffer();
				sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">")
				  .append("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"zh-CN\" xml:lang=\"zh-CN\"><head>")
				  .append("<meta http-equiv=\"Content-Type\" content=\"application/xhtml-xml; charset=utf-8\" /></head><body>")
				  .append(content).append("</body></html>");
				mail.setContent(sb.toString());
				mailService.send(mail);
				mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
				mv.setViewName(getSuccessView());
				return mv;
			}
		}
		return null;
	}

}
