/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		CompassHitWapper.java
 * Class:			CompassHitWapper
 * Date:			Sep 1, 2007 10:20:49 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Sep 1, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.compass;

import org.apache.commons.lang.StringUtils;
import org.compass.core.CompassHit;
import org.compass.core.Resource;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 1, 2007 10:20:49 PM
 * @version $Id CompassHitWapper.java$
 */
public class CompassHitWapper {
	
	private CompassHit compassHit;
	
	public CompassHitWapper(CompassHit compassHit) {
		this.compassHit = compassHit;
	}
	
	public String getAlias() {
		return compassHit.getAlias();
	}
	
	public Object getData() {
		return compassHit.getData();
	}
	
	public Resource getResource() {
		return compassHit.getResource();
	}
	
	public float getScore() {
		return compassHit.getScore();
	}
	
	public String getHighlightedText(String propertyName) {
		String text = "";
		if (compassHit.getHighlightedText()!=null) {
			text = compassHit.getHighlightedText().getHighlightedText(propertyName);
		}
		if (StringUtils.isBlank(text)) {
			return "";
		}
		//text = text.replaceAll("<span class=\"highlight\">([^<>]+)</span>", "_@$1@_");
		//text = MyblogUtil.removeHTML(text);
		//text = TextUtil.escapeHTML(text);
		return text;
		//return text.replaceAll("_@([^<>@]+)@_", "<span class=\"highlight\">$1</span>");
	}
}

