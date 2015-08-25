/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		ConfiguredBeanProcessorFactory.java
 * Class:			ConfiguredBeanProcessorFactory
 * Date:			Oct 11, 2007 11:19:40 PM
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

import java.util.HashMap;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory.StatelessProcessorFactoryFactory;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Oct 11, 2007 11:19:40 PM
 * @version $Id ConfiguredBeanProcessorFactory.java$
 */
public class ConfiguredBeanProcessorFactory extends
		StatelessProcessorFactoryFactory {

	@SuppressWarnings("unchecked")
	private HashMap classBeanMap = new HashMap();

    @SuppressWarnings("unchecked")
	protected Object getRequestProcessor(Class cls)
    throws XmlRpcException {
        Object o = getClassBeanMap().get(cls);
        if (o == null)
            o = getClassBeanMap().get(cls.getName());
        if (o == null)
            throw new XmlRpcException(
                    "Handler bean not found for: " + cls);
        return o;
    }

    @SuppressWarnings("unchecked")
	public HashMap getClassBeanMap() {
        return classBeanMap;
    }

    @SuppressWarnings("unchecked")
	public void setClassBeanMap(HashMap classBeanMap) {
        this.classBeanMap = classBeanMap;
    }
}
