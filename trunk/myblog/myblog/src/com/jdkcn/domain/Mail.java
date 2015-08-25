/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		Mail.java
 * Class:			Mail
 * Date:			2006-6-26 23:31:36
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| 2006-6-26   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.domain;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-6-26 23:31:36
 * @version $Id Mail.java$
 */
public class Mail {
	
	private String[] from;
	
	private String[] personal;
	
	private String[] to;
	
	private String[] cc;
	
	private String[] bcc;
	
	private String subject;
	
	private String content;

	public String[] getPersonal() {
		return personal;
	}

	public void setPersonal(String[] personal) {
		this.personal = personal;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getFrom() {
		return from;
	}

	public void setFrom(String[] from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}
	
}
