/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		HtmlPropertyConverter.java
 * Class:			HtmlPropertyConverter
 * Date:			Sep 5, 2007 10:26:00 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Sep 5, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.compass;

import org.compass.core.converter.ConversionException;
import org.compass.core.converter.basic.AbstractBasicConverter;
import org.compass.core.mapping.ResourcePropertyMapping;

import com.jdkcn.util.MyblogUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 5, 2007 10:26:00 AM
 * @version $Id HtmlPropertyConverter.java$
 */
public class HtmlPropertyConverter extends AbstractBasicConverter {

	/* (non-Javadoc)
	 * @see org.compass.core.converter.ResourcePropertyConverter#fromString(java.lang.String, org.compass.core.mapping.ResourcePropertyMapping)
	 */
	public Object fromString(String str,
			ResourcePropertyMapping resourcePropertyMapping)
			throws ConversionException {
		return MyblogUtil.removeHTML(str);
	}
	
	/* (non-Javadoc)
	 * @see org.compass.core.converter.basic.AbstractBasicConverter#toString(java.lang.Object, org.compass.core.mapping.ResourcePropertyMapping)
	 */
	@Override
	public String toString(Object o,
			ResourcePropertyMapping resourcePropertyMapping) {
		if (o instanceof String) {
			String str = (String) o;
			str = MyblogUtil.removeHTML(str);
			return str;
		}
		return super.toString(o, resourcePropertyMapping);
	}
}
