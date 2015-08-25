/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		Link.java
 * Class:			Link
 * Date:			Mar 29, 2007 10:41:58 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Mar 29, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.domain;

import java.util.Date;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Mar 29, 2007 10:41:58 AM
 * @version $Id Link.java$
 */
public class Link extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2751855600256188011L;
	
	private String name;
	
	private String description;
	
	private String URL;
	
	private String imgURL;
	
	private Category category;
	
	private Date createTime;
	
	private Integer order;
	
	private Boolean recommend;
	
	public Link(){
	}
	
	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String url) {
		URL = url;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
