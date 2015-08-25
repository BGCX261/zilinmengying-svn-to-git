/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		BaseAPIHandler.java
 * Class:			BaseAPIHandler
 * Date:			Oct 7, 2007 10:40:05 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Oct 7, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.xmlrpc;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcException;

import com.jdkcn.BlogFacade;
import com.jdkcn.domain.SiteConfig;
import com.jdkcn.domain.User;
import com.jdkcn.exception.InvalidPasswordException;
import com.jdkcn.exception.InvalidUsernameException;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Oct 7, 2007 10:40:05 PM
 * @version $Id BaseAPIHandler.java$
 */
public class BaseAPIHandler implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2720333600057861346L;

	private Log log = LogFactory.getLog(BaseAPIHandler.class);
    
	public static final int AUTHORIZATION_EXCEPTION = 0001;
    
	public static final String AUTHORIZATION_EXCEPTION_MSG =
            "Invalid Username and/or Password";
    
    public static final int UNKNOWN_EXCEPTION = 1000;
    
    public static final String UNKNOWN_EXCEPTION_MSG =
            "An error occured processing your request";
    
    public static final int BLOGGERAPI_INCOMPLETE_POST = 1006;
    
    public static final String BLOGGERAPI_INCOMPLETE_POST_MSG =
            "Incomplete weblog entry";
    
	protected BlogFacade blogFacade;
	
	public void setBlogFacade(BlogFacade blogFacade) {
		this.blogFacade = blogFacade;
	}

	public BaseAPIHandler(){
	}
	
	/**
	 * validate the user login from xmlrpc.ignore the blogId.
	 * @param blogId
	 * @param username
	 * @param password
	 * @return
	 * @throws InvalidUsernameException
	 * @throws InvalidPasswordException
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	protected SiteConfig validate(String blogId, String username, String password) throws Exception{
		try{
			User user = blogFacade.login(username, password);
			log.debug( "[myblog]" + user.getUsername() + " Login from xmlrpc");
			return blogFacade.getDatabaseSiteConfig();
		} catch (InvalidUsernameException ex) {
			throw new XmlRpcException(AUTHORIZATION_EXCEPTION, AUTHORIZATION_EXCEPTION_MSG);
		} catch (InvalidPasswordException ex) {
			throw new XmlRpcException(AUTHORIZATION_EXCEPTION, AUTHORIZATION_EXCEPTION_MSG);
		}
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws InvalidPasswordException
	 * @throws InvalidUsernameException
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	protected boolean validateUser(String username, String password) throws InvalidPasswordException, InvalidUsernameException {
		User user = blogFacade.login(username, password);
		log.debug( "[myblog]" + user.getUsername() + " Login from xmlrpc");
		return true;
	}
}
