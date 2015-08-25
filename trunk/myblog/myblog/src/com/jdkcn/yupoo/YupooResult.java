/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		YupooResult.java
 * Class:			YupooResult
 * Date:			Jan 16, 2007 3:15:02 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Jan 16, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.yupoo;

import java.util.List;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Jan 16, 2007 3:15:02 PM
 * @version $Id YupooResult.java$
 */
public class YupooResult {
	private List<YupooPhoto> photos;
	private Integer page;
	private Integer pages;
	private Integer perpage;
	private Integer total;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getPerpage() {
		return perpage;
	}
	public void setPerpage(Integer perpage) {
		this.perpage = perpage;
	}
	public List<YupooPhoto> getPhotos() {
		return photos;
	}
	public void setPhotos(List<YupooPhoto> photos) {
		this.photos = photos;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
}
