/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		Constants.java
 * Class:			Constants
 * Date:			2006-9-14
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| 2006-9-14   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.util;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-9-14
 * @version $Id Constants.java$
 */
public class Constants {
	private Constants(){};
	
	/**
	 * login user's session name
	 * <code>_myblog_authuer</code>
	 */
	public final static String AUTH_USER = "_myblog_authuser";
	
	/**
	 * The identifying code 's session attribute name
	 * <code>_identifying_code</code>
	 */
	public final static String IDENTIFYING_CODE_SESSION_NAME = "_identifying_code";
	
	/**
	 * famale is 0
	 */
	public final static Integer FEMALE = 0;
	
	/**
	 * male is 1
	 */
	public final static Integer MALE = 1;
	
	/**
	 * the default page size is 10.
	 */
	public final static Integer DEFAULT_PAGE_SIZE = 10;
	
	/**
	 * the default recent entries size is 10.
	 */
	public final static Integer DEFAULT_RECENT_ENTRIES_SIZE = 10;
	
	/**
	 * the default recent comments size is 10.
	 */
	public final static Integer DEFAULT_RECENT_COMMENTS_SIZE = 10;
	
	/**
	 * the default related entries size is 10.
	 */
	public final static Integer DEFAULT_RELATED_ENTRIES_SIZE = 10;
	
	/**
	 * the default coming requests size is 10.
	 */
	public final static Integer DEFAULT_COMING_REQUESTS_SIZE = 10;
	
	/**
	 * the default hot tags size is 50.
	 */
	public final static Integer DEFAULT_HOT_TAGS_SIZE = 50;
	
	/**
	 * the cached recent entries 's key(_recent_entries).
	 */
	public final static String RECENT_ENTRIES_CACHE_KEY = "_recent_entries";
	
	/**
	 * the cached categories's key(_categories).
	 */
	public final static String CATEGORIES_CACHE_KEY = "_categories";
	
	/**
	 * the cached links's key(_links);
	 */
	public final static String LINKS_CACHE_KEY = "_links";
	
	/**
	 * the cached recommend links's key(_recommend_links);
	 */
	public final static String RECOMMEND_LINKS_CACHE_KEY = "_recommend_links";
	
	/**
	 * the cached archive month list's key(_archive_month_list);
	 */
	public final static String ARCHIVE_MONTH_LIST_CACHE_KEY = "_archive_month_list";
	
	/**
	 * the cached hot tags key(_hot_tags);
	 */
	public final static String HOT_TAGS_CACHE_KEY = "_hot_tags";
	
	/**
	 * the cached pages's key <i>_pages</i>
	 */
	public final static String PAGES_CACHE_KEY = "_pages";
	
	/**
	 * the original url key(_original_url).
	 */
	public final static String ORIGINAL_URL = "_original_url";
	
	/**
	 * the cached recent comments 's key(_recent_comments).
	 */
	public final static String RECENT_COMMENTS_CACHE_KEY = "_recent_comments";
	
	/**
	 * the cached siteconfig's key(_siteconfig).
	 */
	public final static String SITECONFIG_CACHE_KEY = "_siteconfig";
	
	/**
	 * the default limit length is 340.
	 */
	public final static Integer DEFAULT_LIMIT_LENGTH = 340;
	
	/**
	 * the do not intercept's key(_do_not_intercept);
	 */
	public final static String DO_NOT_INTERCEPT = "_do_not_intercept";
	
	/**
	 * the comment's authorname's cookie key.
	 */
	public final static String AUTHORNAME_COOKIE_KEY = "_myblog_authorname";
	
	/**
	 * the comment's authormail's cookie key.
	 */
	public final static String AUTHORMAIL_COOKIE_KEY = "_myblog_authormail";
	
	/**
	 * the comment's authorsite's cookie key.
	 */
	public final static String AUTHORSITE_COOKIE_KEY = "_myblog_authorsite";
}
