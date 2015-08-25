/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		SiteConfig.java
 * Class:			SiteConfig
 * Date:			Dec 31, 2006 11:47:31 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 31, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.domain;


/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 31, 2006 11:47:31 AM
 * @version $Id SiteConfig.java$
 */
public class SiteConfig extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -814103622204673794L;

	private String siteURL;
	
	private String siteName;
	
	private String siteSubName;

	private String siteAbout;
	
	private String siteSimpleAbout;
	
	private String linkUnitsAds;
	
	private String imageAds;
	
	private String videoAds;
	
	private String textAds;
	
	private String referralsAds;
	
	private String adSenseForSearch;
	
	private String ICPNumber;
	
	private String analyticsCode;
	
	private Integer limitLength; // this is for the list page's entry summary.
	
	private static final String APP_VERSION = "Ver1.8.20071011-National-Day-Final_Bachelor's-day-patch";
	
	private String theme;
	
	public SiteConfig(){
	}
	
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getAnalyticsCode() {
		return analyticsCode;
	}

	public void setAnalyticsCode(String analyticsCode) {
		this.analyticsCode = analyticsCode;
	}

	public String getICPNumber() {
		return ICPNumber;
	}

	public void setICPNumber(String number) {
		ICPNumber = number;
	}

	public String getAdSenseForSearch() {
		return adSenseForSearch;
	}

	public void setAdSenseForSearch(String adSenseForSearch) {
		this.adSenseForSearch = adSenseForSearch;
	}

	public String getReferralsAds() {
		return referralsAds;
	}

	public void setReferralsAds(String referralsAds) {
		this.referralsAds = referralsAds;
	}

	public String getImageAds() {
		return imageAds;
	}

	public void setImageAds(String imageAds) {
		this.imageAds = imageAds;
	}

	public String getLinkUnitsAds() {
		return linkUnitsAds;
	}

	public void setLinkUnitsAds(String linkUnitsAds) {
		this.linkUnitsAds = linkUnitsAds;
	}

	public String getTextAds() {
		return textAds;
	}

	public void setTextAds(String textAds) {
		this.textAds = textAds;
	}

	public String getVideoAds() {
		return videoAds;
	}

	public void setVideoAds(String videoAds) {
		this.videoAds = videoAds;
	}

	public Integer getLimitLength() {
		return limitLength;
	}

	public void setLimitLength(Integer limitLength) {
		this.limitLength = limitLength;
	}

	public String getSiteURL() {
		return siteURL;
	}

	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}

	public String getSiteSimpleAbout() {
		return siteSimpleAbout;
	}

	public void setSiteSimpleAbout(String siteSimpleAbout) {
		this.siteSimpleAbout = siteSimpleAbout;
	}

	public String getSiteAbout() {
		return siteAbout;
	}

	public void setSiteAbout(String siteAbout) {
		this.siteAbout = siteAbout;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteSubName() {
		return siteSubName;
	}

	public void setSiteSubName(String siteSubName) {
		this.siteSubName = siteSubName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.siteName + "'s config";
	}
	
	/**
	 * get the myblog's version.
	 * 
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public String getAppVersion() {
		return APP_VERSION;
	}
	
}
