/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, leaf.jdk.cn
 * Filename:		ServiceException.java
 * Module:			Dream
 * Class:			ServiceException
 * Date:			2006-7-6 下午10:07:10
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| 2006-7-6   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.exception;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-7-6 下午10:07:10
 * @version $Id ServiceException.java$
 */
public class BlogException extends RuntimeException {

	private static final long serialVersionUID = -6396167472571104679L;
	
	public BlogException() {
		super();
	}
	
	public BlogException(String msg) {
		super(msg);
	}
	
	public BlogException(Throwable e) {
		super(e);
	}
	
	public BlogException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
