/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		PostCommentController.java
 * Class:			PostCommentController
 * Date:			Dec 3, 2006 11:07:52 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 3, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Comment;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.Mail;
import com.jdkcn.domain.User;
import com.jdkcn.util.Constants;
import com.jdkcn.util.CookieUtil;
import com.jdkcn.util.MyblogUtil;
import com.jdkcn.util.TextUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 3, 2006 11:07:52 PM
 * @version $Id PostCommentController.java$
 */
public class PostCommentController extends BaseController {
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String entryId = request.getParameter("entryId");
		String authorName = request.getParameter("authorName");
		String authorMail = request.getParameter("authorMail");
		String authorSite = request.getParameter("authorSite");
		String content = request.getParameter("content");
		String code = request.getParameter("code");
		String sessionCode = (String)request.getSession().getAttribute(Constants.IDENTIFYING_CODE_SESSION_NAME);
		User loginUser = getLoginUser(request);
		if(loginUser!=null) {
			authorName = loginUser.getNickname();
			authorMail = loginUser.getMail();
			authorSite = loginUser.getSite();
		}else{
			authorName = TextUtil.escapeHTML(authorName);
		}
		List<String> errors = new ArrayList<String>();
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isBlank(entryId)) {
			mv.setViewName(getIndexView());
			return mv;
		}
		Entry entry = blogFacade.getEntry(entryId);
		if(StringUtils.isBlank(code)){
			errors.add(getText("errors.required", new Object[]{getText("comment.code")}));
		}else{
			if(!code.equals(sessionCode)) {
				errors.add(getText("errors.codeerror"));
			}
			mv.addObject("code", code);
		}
		if (StringUtils.isBlank(authorName)) {
			errors.add(getText("errors.required", new Object[]{getText("comment.authorName")}));
		}else{
			mv.addObject("authorName", authorName);
		}
		if(StringUtils.isBlank(authorMail)) {
			errors.add(getText("errors.required", new Object[]{getText("comment.authorMail")}));
		}else{
			if(!EmailValidator.getInstance().isValid(authorMail)) {
				errors.add(getText("errors.email", new Object[]{authorMail}));
			}
			mv.addObject("authorMail", authorMail);
		}
		if(StringUtils.isBlank(content)) {
			errors.add(getText("errors.required", new Object[]{getText("comment.content")}));
		}else{
			mv.addObject("content", content);
		}
		if(StringUtils.isNotBlank(authorSite)){
			mv.addObject("authorSite", authorSite);
			if(!(authorSite.startsWith("http://")||authorSite.startsWith("https://"))) {
				errors.add(getText("errors.url", new Object[]{authorSite}));
			}
		}
		if(!errors.isEmpty()){
			entry.setSummary(MyblogUtil.truncateNicely(MyblogUtil.removeHTML(entry.getContent()), 200, 220, "..."));
			mv.addObject("errors", errors);
			mv.addObject("entry", entry);
			mv.setViewName(getFormView());
			return mv;
		}
		Comment comment = new Comment();
		comment.setAuthorName(authorName);
		comment.setAuthorMail(authorMail);
		comment.setAuthorSite(authorSite);
		comment.setContent(TextUtil.autoformat(TextUtil.escapeHTML(content)));
		comment.setEntry(entry);
		comment.setIsSubscribe(true);
		comment.setPostIP(request.getRemoteAddr());
		comment.setPostTime(new Date());
		comment.setStatus(Comment.Status.APPROVED);
		if(loginUser!=null)
			comment.setUserId(loginUser.getId());
		if(entry.getCommentSize()!=null)
			entry.setCommentSize(entry.getCommentSize()+1);
		else
			entry.setCommentSize(1);
		blogFacade.saveOrUpdateEntry(entry);
		
		blogFacade.saveOrUpdateComment(comment);
		Mail mail = new Mail();
		mail.setSubject(entry.getTitle() + " 's replay");
		StringBuffer sb = new StringBuffer();
		sb.append(comment.getAuthorName()).append(" says: <br />").append(comment.getContent());
		if (StringUtils.isNotBlank(entry.getName())) {
			sb.append("<br /><p>").append(getText("msg.post.comment.noreplay")).append("</p><br />\n<a href=\"").append(blogFacade.getDatabaseSiteConfig().getSiteURL())
		      .append("/entry/").append(entry.getName()).append(".html#c_").append(comment.getId()).append("\">").append(entry.getTitle()).append("</a>");
		} else {
			sb.append("<br /><p>").append(getText("msg.post.comment.noreplay")).append("</p><br />\n<a href=\"").append(blogFacade.getDatabaseSiteConfig().getSiteURL())
		      .append("/entry/id/").append(entry.getId()).append(".html#c_").append(comment.getId()).append("\">").append(entry.getTitle()).append("</a>");
		}
		sb.append("<p><a href=\"").append(blogFacade.getDatabaseSiteConfig().getSiteURL())
		  .append("/unsubscribe.jhtml?id=").append(entry.getId()).append("&amp;email=");
		mail.setContent(sb.toString());
		List<String> mailList = blogFacade.getSubscribeEntryCommentEmails(entry.getId());
		if (!mailList.contains(entry.getAuthor().getMail())) {
			mailList.add(entry.getAuthor().getMail());
		}
		if (mailList.contains(comment.getAuthorMail())) {
			mailList.remove(comment.getAuthorMail());
		}
		String path = request.getContextPath();
		if ("/".equals(path)) {
			path = "/";
		} else {
			path = path + "/";
		}
		if (StringUtils.isNotBlank(entry.getName())) {
			path = path + "entry/" + entry.getName() + ".html#c_" + comment.getId();
		} else {
			path = path + "entry/id/" + entry.getId() + ".html#c_" + comment.getId();
		}
		
		for (String email:mailList) {
			String tmp = sb.toString();
			Mail m = new Mail();
			m.setSubject(mail.getSubject());
			m.setContent(tmp + URLEncoder.encode(email, "UTF-8") + "\">" + getText("msg.unsubscribe.title") + "</a></p>");
			m.setTo(new String[]{email});
			blogFacade.sendMail(m);
		}
		
		
		//remember the author's infomation,save cookie.
		CookieUtil.setCookie(response, Constants.AUTHORNAME_COOKIE_KEY, authorName, 60*60*24*365);
		CookieUtil.setCookie(response, Constants.AUTHORMAIL_COOKIE_KEY, authorMail, 60*60*24*365);
		if(StringUtils.isNotBlank(authorSite)) {
			CookieUtil.setCookie(response, Constants.AUTHORSITE_COOKIE_KEY, authorSite, 60*60*24*365);
		}
		response.sendRedirect(path);
		return null;
	}

}
