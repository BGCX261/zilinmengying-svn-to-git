/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		MetaWeblogAPIHandler.java
 * Class:			MetaWeblogAPIHandler
 * Date:			Oct 7, 2007 11:10:33 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Oct 7, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.xmlrpc;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcException;

import com.jdkcn.domain.Category;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.SiteConfig;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Oct 7, 2007 11:10:33 PM
 * @version $Id MetaWeblogAPIHandler.java$
 */
public class MetaWeblogAPIHandler extends BaseAPIHandler {
	
	private Log log = LogFactory.getLog(MetaWeblogAPIHandler.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 587506596308977207L;
	
	public MetaWeblogAPIHandler(){
		super();
	}
	
	/**
	 * Authenticates a user and returns the categories available in the website
	 * @param blogid
	 * @param userid
	 * @param password
	 * @return
	 * @throws Exception
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Object getCategories(String blogid, String userid, String password) throws Exception{
		log.debug("[myblog] getCategories() Called by xmlrpc..");
		try{
			SiteConfig siteConfig =  validate(blogid, userid, password);
			Hashtable<String, Object> result = new Hashtable<String, Object>();
			List<Category> categories = blogFacade.getCategories();
			for (Category cate:categories) {
				result.put(cate.getId(), createCategoryStruct(siteConfig, cate));
			}
			return result;
		} catch (Exception ex) {
			String msg = "ERROR in MetaWeblogAPIHandler.getCategories";
			log.error(msg, ex);
			throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
		}
	}
	
    /**
     * Makes a new post to a designated blog. Optionally, will publish the blog after making the post
     *
     * @param blogid Unique identifier of the blog the post will be added to
     * @param userid Login for a MetaWeblog user who has permission to post to the blog
     * @param password Password for said username
     * @param struct Contents of the post
     * @param publish If true, the blog will be published immediately after the post is made
     * @throws org.apache.xmlrpc.XmlRpcException
     * @return
     */
    @SuppressWarnings("unchecked")
	public String newPost(String blogid, String userid, String password,
            Hashtable struct, int publish) throws Exception {
    	return newPost(blogid, userid, password, struct, publish > 0);
    }
    
    @SuppressWarnings("unchecked")
	public String newPost(String blogid, String userid, String password,
            Hashtable struct, boolean publish) throws Exception {
    	log.debug("[myblog] newPost() Called from xmlrpc.");
    	String title = (String) struct.get("title");
    	String description = (String) struct.get("description");
    	if (StringUtils.isBlank(description) || StringUtils.isBlank(title)) {
    		throw new XmlRpcException(BLOGGERAPI_INCOMPLETE_POST, "Must specify title and description");
    	}
    	Date dateCreated = (Date) struct.get("dateCreated");
    	if (dateCreated == null) dateCreated = (Date)struct.get("pubDate");
        if (dateCreated == null) dateCreated = new Date();
        validate(blogid, userid, password);
        try {
        	Entry entry = new Entry();
        	entry.setTitle(title);
        	entry.setContent(description);
        	entry.setPostTime(dateCreated);
        	entry.setModifyTime(dateCreated);
        	entry.setPostIP("from.xmlrpc");
        	entry.setType(Entry.Type.POST);
        	entry.setTrackbackSize(0);
        	entry.setCommentSize(0);
        	entry.setHits(0);
        	if (Boolean.valueOf(publish).booleanValue()) {
        		entry.setEntryStatus(Entry.EntryStatus.PUBLISH);
        	} else {
        		entry.setEntryStatus(Entry.EntryStatus.DRAFT);
        	}
        	entry.setAuthor(blogFacade.getUserByUsername(userid));
        	Category category = null;
        	Object[] cats = (Object[]) struct.get("categories");
        	if (cats != null && cats.length > 0) {
        		log.debug("cats type -" + cats[0].getClass().getName());
        		log.debug("cat to string -" + cats[0].toString());
        		for (Object obj:cats) {
        			category = blogFacade.getCategoryByName((String)obj);
        			if (category != null) {
        				entry.getCategories().add(category);
        			}
        		}
			}
        	// not found the category.
        	if (category == null) {
        		entry.getCategories().add(blogFacade.getCategories().get(0));
        	}
        	blogFacade.saveOrUpdateEntry(entry);
        	return entry.getId();
        } catch (Exception ex) {
        	 String msg = "ERROR in MetaWeblogAPIHandler.newPost";
        	 log.error(msg, ex);
        	 throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
        }
    }
	
    /**
     * get the post
     * @param postId
     * @param userId
     * @param password
     * @return
     * @throws Exception
     * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
     */
    public Object getPost(String postId, String userId, String password) throws Exception {
    	log.debug("[myblog] getPost() Called from xmlrpc.");
    	log.debug("    PostId:" + postId);
    	log.debug("    UserId:" + userId);
    	SiteConfig siteConfig = validate("", userId, password);
    	Entry entry = blogFacade.getEntry(postId);
    	try {
    		return createPostStruct(entry, siteConfig);
    	} catch (Exception e) {
    		String msg = "ERROR in MetaWeblogAPIHandler.getPost";
    		log.error(msg, e);
    		throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
    	}
    }
    
    /**
     * Edits a given post. Optionally, will publish the blog after making the edit
     *
     * @param postid Unique identifier of the post to be changed
     * @param userid Login for a MetaWeblog user who has permission to post to the blog
     * @param password Password for said username
     * @param struct Contents of the post
     * @param publish If true, the blog will be published immediately after the post is made
     * @throws org.apache.xmlrpc.XmlRpcException
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean editPost(String postid, String userid, String password,
            Hashtable struct, int publish) throws Exception {
    	return editPost(postid, userid, password, struct, publish > 0);
    }
    
    @SuppressWarnings("unchecked")
	public boolean editPost(String postid, String userid, String password,
            Hashtable struct, boolean publish) throws Exception {
    	log.debug("[myblog] editPost() Called from xmlrpc");
    	log.debug("      PostId:" + postid);
    	log.debug("      UserId:" + userid);
    	log.debug("     Publish:" + publish);
    	validate("", userid, password);
    	Entry entry = blogFacade.getEntry(postid);
    	
    	Hashtable postcontent = struct;
        String description = (String)postcontent.get("description");
        String title = (String)postcontent.get("title");
        if (title == null) title = "";
        
        Date dateCreated = (Date)postcontent.get("dateCreated");
        if (dateCreated == null) dateCreated = (Date)postcontent.get("pubDate");
        if ( postcontent.get("categories") != null ) {
            Object[] cats = (Object[])postcontent.get("categories");
            entry.getCategories().clear();
            for (Object obj:cats) {
            	entry.getCategories().add(blogFacade.getCategoryByName((String)obj));
            }
        }
        try {
        	entry.setTitle(title);
        	entry.setContent(description);
        	entry.setModifyTime(new Date());
        	blogFacade.saveOrUpdateEntry(entry);
        	return true;
        } catch (Exception ex) {
        	String msg = "ERROR in MetaWeblogAPIHandler.editPost";
        	log.error(msg, ex);
        	throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
        }
    }
    
    /**
     * Get a list of recent posts for a category
     *
     * @param blogid Unique identifier of the blog the post will be added to
     * @param userid Login for a Blogger user who has permission to post to the blog
     * @param password Password for said username
     * @param numposts Number of Posts to Retrieve
     * @throws XmlRpcException
     * @return
     */
    public Object getRecentPosts(String blogid, String userid, String password,
            int numposts) throws Exception {
    	log.debug("[myblog]getRecentPosts() Called from xmlrpc.");
    	log.debug("     BlogId:" + blogid);
    	log.debug("     UserId:" + userid);
    	log.debug("     Number:" + numposts);
    	SiteConfig siteConfig = validate(blogid, userid, password);
    	try {
    		List<Object> results = new ArrayList<Object>();
    		List<Entry> entries = blogFacade.getRecentEntries(numposts);
    		for (Entry entry:entries) {
    			results.add(createPostStruct(entry, siteConfig));
    		}
    		return results;
    	} catch (Exception ex) {
    		String msg = "ERROR in MetaWeblogAPIHandler.getRecentPosts";
    		log.error(msg, ex);
    		throw new XmlRpcException(UNKNOWN_EXCEPTION, msg);
    	}
    }
    
    private Hashtable<String, Object> createPostStruct(Entry entry, SiteConfig siteConfig) {
    	String link = null;
    	if (StringUtils.isNotBlank(entry.getName())) {
    		link = siteConfig.getSiteURL() + "/entry/" + entry.getName() + ".html";
    	} else {
    		link = siteConfig.getSiteURL() + "/entry/id/" + entry.getId() + ".html";
    	}
    	Hashtable<String, Object> struct = new Hashtable<String, Object>();
    	struct.put("title", entry.getTitle());
    	struct.put("description", entry.getContent());
    	struct.put("pubDate", entry.getPostTime());
    	struct.put("dateCreated", entry.getPostTime());
    	struct.put("guid", link);
    	struct.put("permaLink", link);
    	struct.put("postid", entry.getId());
    	struct.put("userid", entry.getAuthor().getUsername());
    	struct.put("author", entry.getAuthor().getMail());
    	List<Object> categories = new ArrayList<Object>();
    	for (Category cate:entry.getCategories()) {
    		categories.add(cate.getName());
    	}
    	struct.put("categories", categories);
    	return struct;
    }
    
    
	private Hashtable<String, Object> createCategoryStruct(SiteConfig siteConfig, Category category) {
		String url = siteConfig.getSiteURL() + "/category/" + category.getName();
		Hashtable<String, Object> struct = new Hashtable<String, Object>();
		url = StringUtils.replace(url, " ", "%20");
		struct.put("htmlUrl", url);
		return struct;
	}
}
