/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		ETagTokenFactory.java
 * Class:			ETagTokenFactory
 * Date:			Aug 9, 2007 8:52:35 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Aug 9, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web.interceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Aug 9, 2007 8:52:35 PM
 * @version $Id ETagTokenFactory.java$
 */
public interface ETagTokenFactory {
	String getToken(HttpServletRequest request);
}
