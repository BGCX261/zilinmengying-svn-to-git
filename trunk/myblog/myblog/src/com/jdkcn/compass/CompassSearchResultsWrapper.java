/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		CompassSearchResultsWrapper.java
 * Class:			CompassSearchResultsWrapper
 * Date:			Sep 2, 2007 10:24:34 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Sep 2, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.compass;

import java.util.List;

import org.compass.core.support.search.CompassSearchResults;
import org.compass.core.support.search.CompassSearchResults.Page;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 2, 2007 10:24:34 AM
 * @version $Id CompassSearchResultsWrapper.java$
 */
public class CompassSearchResultsWrapper {
	
	private CompassSearchResults searchResults;
	
	private List<CompassHitWapper> hits;
	
	public CompassSearchResultsWrapper(CompassSearchResults searchResults) {
		this.searchResults = searchResults;
	}
	
	public List<CompassHitWapper> getHits() {
		return hits;
	}
	
	public void setHits(List<CompassHitWapper> hits) {
		this.hits = hits;
	}
	
	public long getSearchTime() {
		return this.searchResults.getSearchTime();
	}
	
	public int getTotalHits() {
		return this.searchResults.getTotalHits();
	}
	
	public Page[] getPages() {
		return this.searchResults.getPages();
	}
	
	
}
