/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, leaf.jdk.cn
 * Filename:		InvalidUsernameException.java
 * Module:			Dream
 * Class:			InvalidUsernameException
 * Date:			2006-7-6 下午10:11:39
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
 * @since 2006-7-6 下午10:11:39
 * @version $Id InvalidUsernameException.java$
 */
public class InvalidUsernameException extends Exception {

	private static final long serialVersionUID = 5583921235338288927L;
	
	public InvalidUsernameException(String msg) {
		super(msg);
	}
	
	public InvalidUsernameException(String msg, Throwable e) {
		super(msg, e);
	}
}
