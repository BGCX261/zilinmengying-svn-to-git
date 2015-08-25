/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, leaf.jdk.cn
 * Filename:		InvalidPasswordException.java
 * Module:			Dream
 * Class:			InvalidPasswordException
 * Date:			2006-7-6 下午10:13:51
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
 * @since 2006-7-6 下午10:13:51
 * @version $Id InvalidPasswordException.java$
 */
public class InvalidPasswordException extends Exception {

	private static final long serialVersionUID = 4679889729440800814L;
	
	public InvalidPasswordException(String msg) {
		super(msg);
	}
	
	public InvalidPasswordException(String msg, Throwable e) {
		super(msg, e);
	}
}
