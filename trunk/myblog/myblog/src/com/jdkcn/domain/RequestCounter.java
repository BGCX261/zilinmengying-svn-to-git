/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		RequestCounter.java
 * Class:			RequestCounter
 * Date:			Dec 6, 2006 11:02:18 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 6, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.domain;

import java.util.Date;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 6, 2006 11:02:18 AM
 * @version $Id RequestCounter.java$
 */
public class RequestCounter extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7150933605758463438L;

	private String userAgent;

	private String referer;

	private String ip;

	private Date requestTime;
	
	private String uri;
	
	public RequestCounter(){
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}
