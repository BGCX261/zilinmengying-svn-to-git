/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		YupooPhoto.java
 * Class:			YupooPhoto
 * Date:			Jan 16, 2007 3:15:43 PM
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

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Jan 16, 2007 3:15:43 PM
 * @version $Id YupooPhoto.java$
 */
public class YupooPhoto {
	private String id;

	private String owner;

	private String title;

	private String host;

	private String dir;

	private String filename;

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
