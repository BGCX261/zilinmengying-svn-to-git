/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		AboutController.java
 * Class:			AboutController
 * Date:			Jan 4, 2007 11:03:54 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Jan 4, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.WebContentGenerator;

import com.jdkcn.domain.SiteConfig;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Jan 4, 2007 11:03:54 AM
 * @version $Id AboutController.java$
 */
public class EditSiteConfigController extends BaseController {
	
	private Resource themePath;
	
	public void setThemePath(Resource themePath) {
		this.themePath = themePath;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<String> themes = new ArrayList<String>();
		File[] files = themePath.getFile().listFiles();
		for (File file:files) {
			if (file.isDirectory() && !"admin".equalsIgnoreCase(file.getName())) {
				themes.add(file.getName());
			}
		}
		mv.addObject("themes", themes);
		if (WebContentGenerator.METHOD_GET.equals(request.getMethod())) {
			mv.addObject("siteConfig", blogFacade.getDatabaseSiteConfig());
			mv.setViewName(getFormView());
			return mv;
		}
		String siteName = request.getParameter("siteName");
		String siteSubName = request.getParameter("siteSubName");
		String siteAbout = request.getParameter("siteAbout");
		String siteSimpleAbout = request.getParameter("siteSimpleAbout");
		String siteURL = request.getParameter("siteURL");
		String limitLength = request.getParameter("limitLength");
		String linkUnitsAds = request.getParameter("linkUnitsAds");
		String imageAds = request.getParameter("imageAds");
		String videoAds = request.getParameter("videoAds");
		String textAds = request.getParameter("textAds");
		String referralsAds = request.getParameter("referralsAds");
		String adSenseForSearch = request.getParameter("adSenseForSearch");
		String ICPNumber = request.getParameter("ICPNumber");
		String analyticsCode = request.getParameter("analyticsCode");
		String theme = request.getParameter("theme");
		if(StringUtils.isNotBlank(siteName)
				&&StringUtils.isNotBlank(siteSubName)
				&&StringUtils.isNotBlank(siteAbout)
				&&StringUtils.isNotBlank(siteSimpleAbout)
				&&StringUtils.isNotBlank(limitLength)
				&&StringUtils.isNotBlank(theme)) {
			SiteConfig siteConfig = blogFacade.getDatabaseSiteConfig();
			siteConfig.setSiteName(siteName);
			siteConfig.setSiteSubName(siteSubName);
			siteConfig.setSiteAbout(siteAbout);
			siteConfig.setSiteSimpleAbout(siteSimpleAbout);
			siteConfig.setSiteURL(siteURL);
			siteConfig.setLinkUnitsAds(linkUnitsAds);
			siteConfig.setImageAds(imageAds);
			siteConfig.setVideoAds(videoAds);
			siteConfig.setTextAds(textAds);
			siteConfig.setReferralsAds(referralsAds);
			siteConfig.setAdSenseForSearch(adSenseForSearch);
			siteConfig.setICPNumber(ICPNumber);
			siteConfig.setAnalyticsCode(analyticsCode);
			siteConfig.setTheme(theme);
			if(NumberUtils.isNumber(limitLength)) {
				siteConfig.setLimitLength(Integer.parseInt(limitLength));
			}else{
				siteConfig.setLimitLength(Constants.DEFAULT_LIMIT_LENGTH);
			}
			blogFacade.saveOrUpdateSiteConfig(siteConfig);
			mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			mv.addObject("save", "edit");
			saveMessage(request, getText("msg.edit.siteconfig.success"));
		}
		mv.clear();
		mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
		mv.setViewName(getSuccessView());
		return mv;
	}

}
