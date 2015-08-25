/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		XmlRpcServerController.java
 * Class:			XmlRpcServerController
 * Date:			Oct 11, 2007 10:21:08 PM
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

import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping.AuthenticationHandler;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.support.WebContentGenerator;


/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Oct 11, 2007 10:21:08 PM
 * @version $Id XmlRpcServerController.java$
 */
public class XmlRpcServerController extends AbstractController implements
		InitializingBean {

	private XmlRpcServletServer server = new XmlRpcServletServer();
	private PropertiesHandlerMapping mapping = new PropertiesHandlerMapping();
	private RequestProcessorFactoryFactory factory;
	private Properties mappings;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (WebContentGenerator.METHOD_GET.equals(request.getMethod())) {
			PrintWriter out = response.getWriter();
			out.println("Not support get method.");
			out.flush();
			return null;
		}
		try {
			server.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		XmlRpcServerConfigImpl cfg = (XmlRpcServerConfigImpl) server
				.getConfig();
		cfg.setEnabledForExtensions(true);
		cfg.setEnabledForExceptions(false);
		mapping.setRequestProcessorFactoryFactory(getFactory());
		mapping.load(getMappings());
		mapping.setAuthenticationHandler(new AuthenticationHandler() {
			public boolean isAuthorized(XmlRpcRequest pRequest)
					throws XmlRpcException {
				return true;
			}
		});
		server.setHandlerMapping(mapping);
	}

	public RequestProcessorFactoryFactory getFactory() {
		return factory;
	}

	public void setFactory(RequestProcessorFactoryFactory factory) {
		this.factory = factory;
	}

	public Properties getMappings() {
		return mappings;
	}

	public void setMappings(Properties mappings) {
		this.mappings = mappings;
	}

}
