/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		SearchController.java
 * Class:			SearchController
 * Date:			Apr 28, 2007 11:47:45 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Apr 28, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.compass.core.Compass;
import org.compass.core.CompassTemplate;
import org.compass.core.engine.SearchEngineQueryParseException;
import org.compass.core.support.search.CompassSearchCommand;
import org.compass.core.support.search.CompassSearchHelper;
import org.compass.core.support.search.CompassSearchResults;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.compass.CompassHitWapper;
import com.jdkcn.compass.CompassSearchResultsWrapper;
import com.jdkcn.util.TextUtil;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Apr 28, 2007 11:47:45 AM
 * @version $Id SearchController.java$
 */
public class SearchController extends BaseController implements InitializingBean{
	
	private Compass compass;
	
	private CompassTemplate compassTemplate;
	
    private String searchView;

    private String searchResultsView;

    private String searchResultsName = "searchResults";

    private Integer pageSize;

    private CompassSearchHelper searchHelper;

	public Compass getCompass() {
		return compass;
	}
	
	public void setCompass(Compass compass) {
		this.compass = compass;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchResultsName() {
		return searchResultsName;
	}

	public void setSearchResultsName(String searchResultsName) {
		this.searchResultsName = searchResultsName;
	}

	public String getSearchResultsView() {
		return searchResultsView;
	}

	public void setSearchResultsView(String searchResultsView) {
		this.searchResultsView = searchResultsView;
	}

	public String getSearchView() {
		return searchView;
	}

	public void setSearchView(String searchView) {
		this.searchView = searchView;
	}
	
	public void setSearchHelper(CompassSearchHelper searchHelper) {
		this.searchHelper = searchHelper;
	}

    /**
     * Returns a <code>CompassTemplate</code> that wraps the injected
     * <code>Compass</code> instance.
     * 
     * @return Compass template that wraps the injected Compass instance.
     */
    protected CompassTemplate getCompassTemplate() {
        return this.compassTemplate;
    }
    
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
        if (compass == null) {
            throw new IllegalArgumentException("Must set compass property");
        }
        this.compassTemplate = new CompassTemplate(compass);
        if (searchView == null) {
            throw new IllegalArgumentException("Must set the searchView property");
        }
        if (searchResultsView == null) {
            throw new IllegalArgumentException("Must set the serachResultsView property");
        }
        if (searchHelper == null) {
            searchHelper = new CompassSearchHelper(getCompass(), getPageSize());
        }
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String query = request.getParameter("query");
		if(StringUtils.isBlank(query)) {
			return new ModelAndView(getSearchView());
		}
		String p = request.getParameter("p");
		int currentPage = 1;
		if(NumberUtils.isNumber(p)) {
			currentPage = Integer.parseInt(p);
		}
		if(currentPage<1) {
			currentPage = 1;
		}
		CompassSearchCommand searchCommand = new CompassSearchCommand();
		searchCommand.setPage(new Integer(currentPage-1));
		searchCommand.setQuery(query);
		ModelAndView mv = new ModelAndView();
		mv.addObject("query", query);
		mv.addObject("p", currentPage);
		List<String> errors = new ArrayList<String>();
		try{
			CompassSearchResults searchResults = searchHelper.search(searchCommand);
			List<CompassHitWapper> hits = new ArrayList<CompassHitWapper>();
			for (int i = 0; i < searchResults.getHits().length; i++) {
				CompassHitWapper hit = new CompassHitWapper(searchResults.getHits()[i]);
				hits.add(hit);
			}
			CompassSearchResultsWrapper results = new CompassSearchResultsWrapper(searchResults);
			results.setHits(hits);
			mv.addObject(getSearchResultsName(), results);
		} catch (SearchEngineQueryParseException ex){
			errors.add(TextUtil.escapeHTML(ex.getMessage()));
			mv.addObject("errors", errors);
		} catch (Exception ex) {
			errors.add(TextUtil.escapeHTML(ex.getMessage()));
			mv.addObject("errors", errors);
		}
		mv.setViewName(getSearchResultsView());
		return mv;
	}
	
}
