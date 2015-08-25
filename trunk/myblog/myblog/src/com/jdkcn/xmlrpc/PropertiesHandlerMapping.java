/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		PropertiesHandlerMapping.java
 * Class:			PropertiesHandlerMapping
 * Date:			Oct 11, 2007 10:24:59 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Oct 11, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.xmlrpc;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Oct 11, 2007 10:24:59 PM
 * @version $Id PropertiesHandlerMapping.java$
 */
public class PropertiesHandlerMapping extends PropertyHandlerMapping{
	
	@SuppressWarnings("unchecked")
	public void load(Properties props) throws XmlRpcException {
        for (Iterator i = props.entrySet().iterator();  i.hasNext();  ) {
            Map.Entry entry = (Map.Entry) i.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            Class c = newHandlerClass(
                    getClass().getClassLoader(), value);
            registerPublicMethods(key, c);
        }
    }
	
}
