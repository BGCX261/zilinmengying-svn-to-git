/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		BlogFacade.java
 * Class:			BlogFacade
 * Date:			Sep 12, 2006 9:57:33 AM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Sep 12, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn;

import java.util.Date;
import java.util.List;

import com.jdkcn.domain.Category;
import com.jdkcn.domain.Comment;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.GuestBook;
import com.jdkcn.domain.Link;
import com.jdkcn.domain.Mail;
import com.jdkcn.domain.RequestCounter;
import com.jdkcn.domain.Role;
import com.jdkcn.domain.SiteConfig;
import com.jdkcn.domain.Tag;
import com.jdkcn.domain.User;
import com.jdkcn.exception.InvalidPasswordException;
import com.jdkcn.exception.InvalidUsernameException;
import com.jdkcn.exception.UsernameAlreadyExistException;
import com.jdkcn.util.PaginationSupport;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Sep 12, 2006 9:57:33 AM
 * @version $Id: BlogFacade.java$
 */
public interface BlogFacade {
	
	/**
	 * add or update the role 
	 * @param role
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdateRole(Role role);
	
	/**
	 * get Entry by id
	 * @param id Entry's id
	 * @return Entry
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Entry getEntry(String id);
	
	/**
	 * plus the entry's hits, and return the entry.
	 * @param id
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Entry plusHits(String entryId);
	
	/**
	 * plus the entry's commentSize, and return the entry.
	 * @param entryId
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Entry plusCommentSize(String entryId);
	
	/**
	 * get the next entry by the current entry's id ,it's means older than the current entry.
	 * @param id the current entry's id
	 * @return the nexe entry
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Entry getNextEntry(String id);
	
	/**
	 * get the previous entry by the current entry's id, it's means newer than the current entry.
	 * @param id the current entry's id
	 * @return the previous entry
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Entry getPreviousEntry(String id);
	
	/**
	 * get entry by entry's name
	 * @param name the entry's name
	 * @return Entry
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Entry getEntryByName(String name);
	
	/**
	 * get the recent entries.
	 * @param size
	 * @return the entry list
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Entry> getRecentEntries(int size);
	
	/**
	 * add or update the entry
	 * @param entry entry to add or update
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdateEntry(Entry entry);
	
	/**
	 * save or update the page.
	 * @param entry the page to save or update
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdatePage(Entry entry);
	
	/**
	 * remove the entry
	 * @param id enty's id to remove
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void removeEntry(String id);
	
	/**
	 * remove the page
	 * @param id
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void removePage(String id);
	
	/**
	 * get the entry by page
	 * @param entry the criteria for search
	 * @param pageSize page size
	 * @param startIndex the record's start index
	 * @param order the order field
	 * @param isDesc is desc order
	 * @return page
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public PaginationSupport<Entry> getEntryPage(Entry entry, int pageSize, int startIndex, String order, Boolean isDesc);
	
	/**
	 * get the entry page by category's id
	 * @param categoryId category's id
	 * @param pageSize page size
	 * @param startIndex the record's start index
	 * @param order the order field
	 * @param isDesc is desc order
	 * @return page
	 * @see PaginationSupport
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public PaginationSupport<Entry> getEntryPageByCategoryId(String categoryId, int pageSize, int startIndex, String order, Boolean isDesc);
	
	/**
	 * get the entry page by category's name
	 * @param categoryName category's name
	 * @param pageSize page size
	 * @param startIndex the record's start index
	 * @param order the order field
	 * @param isDesc is desc order
	 * @return page
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public PaginationSupport<Entry> getEntryPageByCategoryName(String categoryName, int pageSize, int startIndex, String order, Boolean isDesc);
	
	/**
	 * get the entry page by month.
	 * @param month
	 * @param pageSize
	 * @param startIndex
	 * @param order
	 * @param isDesc
	 * @return the entry page
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public PaginationSupport<Entry> getEntryPageByMonth(Date month, int pageSize, int startIndex, String order, Boolean isDesc);
	
	/**
	 * get the entry page by tag's id
	 * @param tagId tag's id
	 * @param pageSize page size
	 * @param startIndex the record's start index
	 * @param order the order field
	 * @param isDesc is desc order
	 * @return page
	 * @see PaginationSupport
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public PaginationSupport<Entry> getEntryPageByTagId(String tagId, int pageSize, int startIndex, String order, Boolean isDesc);
	
	/**
	 * get the entry page by tag's name
	 * @param tagName
	 * @param pageSize
	 * @param startIndex
	 * @param order
	 * @param isDesc
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public PaginationSupport<Entry> getEntryPageByTagName(String tagName, int pageSize, int startIndex, String order, Boolean isDesc);
	
	/**
	 * get the category by the id
	 * @param id category's id
	 * @return category
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Category getCategory(String id);
	
	/**
	 * get the category by category's name
	 * @param name category's name
	 * @return category
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Category getCategoryByName(String name);
	
	/**
	 * 
	 * @param category
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdateCategory(Category category);
	
	/**
	 * remove the category
	 * @param id 
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void removeCategory(String id);
	
	/**
	 * Get all of the categories.
	 * @return list of the categories
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Category> getCategories();
	
	/**
	 * save or update the comment
	 * @param comment comment to save or update
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdateComment(Comment comment);
	
	/**
	 * remove the comment
	 * @param id comment's id to remove
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void removeComment(String id);
	
	/**
	 * get the entry's comment by page
	 * @param entryId entry's id
	 * @param pageSize page size
	 * @param startIndex the start record's index
	 * @param order the order field
	 * @param isDesc is desc order
	 * @return comment page
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public PaginationSupport<Comment> getCommentPageByEntryId(String entryId, int pageSize, int startIndex, String order, Boolean isDesc);
	
	/**
	 * Get the comment by id.
	 * @param id
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Comment getComment(String id);
	
	/**
	 * get the comment by page
	 * @param comment
	 * @param pageSize
	 * @param startIndex
	 * @param order
	 * @param isDesc
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public PaginationSupport<Comment> getCommentPage(Comment comment, int pageSize, int startIndex, String order, Boolean isDesc);
	
	/**
	 * get the comment list by entry's id
	 * @param entryId the entry's id
	 * @return the entry's comment list
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Comment> getCommentListByEntryId(String entryId);
	
	
	/**
	 * get the comment list by entry's id and the comment's status.
	 * @param entryId
	 * @param status
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Comment> getCommentListByEntryIdAndStatus(String entryId, String status);
	
	/**
	 * get the recent comments
	 * @param size the comment's size
	 * @return the comments
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Comment> getRecentComments(int size);
	
	/**
	 * save or update the user
	 * @param user user to update or save
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdateUser(User user);
	
	/**
	 * update the user
	 * @param user user to update
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void updateUserWithoutEncryptPassword(User user);
	
	/**
	 * get the user by user's id
	 * @param id user's id
	 * @return the user
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public User getUser(String id);
	
	/**
	 * user login
	 * @param username user's username
	 * @param password user's password
	 * @return login user
	 * @throws InvalidUsernameException if no user named username
	 * @throws InvalidPasswordException if password is incorrect
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public User login(String username, String password) throws InvalidUsernameException, InvalidPasswordException;

	/**
	 * user login
	 * @param username user's username
	 * @param password user's encrypted password
	 * @return login user
	 * @throws InvalidUsernameException if no user named username
	 * @throws InvalidPasswordException if password is incorrect
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public User cookieLogin(String username, String password) throws InvalidUsernameException, InvalidPasswordException;

	/**
	 * get user by user's username
	 * @param username
	 * @return the user,null if no such user.
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public User getUserByUsername(String username);
	
	/**
	 * 
	 * @param user
	 * @param pageSize page size
	 * @param startIndex the start record's index
	 * @param order the order field
	 * @param isDesc is desc order
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public PaginationSupport<User> getUserPage(User user, int pageSize, int startIndex, String order, Boolean isDesc);
	
	/**
	 * remove the user
	 * @param id
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void removeUser(String id);
	
	/**
	 * save or update the guestbook
	 * @param guestBook
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdateGuestBook(GuestBook guestBook);
	
	/**
	 * get the guestbook by id
	 * @param id
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public GuestBook getGuestBook(String id);
	
	/**
	 * remove the guestbook
	 * @param id
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void removeGuestBook(String id);
	
	/**
	 * save or update the tag
	 * @param tag
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdateTag(Tag tag);
	
	/**
	 * 
	 * @param id
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void removeTag(String id);
	
	/**
	 * Get all of the tags
	 * @return the list of tags
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Tag> getTags();
	
	/**
	 * Get tag by tag id
	 * @param id tag's id
	 * @return the tag
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Tag getTag(String id);
	
	/**
	 * Get the tag by tag's name
	 * @param name tag's name
	 * @return The tag
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Tag getTagByName(String name);
	
	
	/**
	 * user register
	 * @param user
	 * @return registed user
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public User register(User user)throws UsernameAlreadyExistException;
	
	/**
	 * save or update the requestcounter
	 * @param requestCounter
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdateRequestCounter(RequestCounter requestCounter);
	
	/**
	 * send a mail
	 * @param mail mail tosend
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void sendMail(Mail mail);
	
	/**
	 * get the siteconfig
	 * @return siteconfig
	 * @deprecated use the {@link #getDatabaseSiteConfig()} instead.
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public SiteConfig getSiteConfig();
	
	/**
	 * get the database config.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public SiteConfig getDatabaseSiteConfig();
	
	/**
	 * save the siteconfig to database.
	 * @param siteConfig
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdateSiteConfig(SiteConfig siteConfig);
	
	/**
	 * save the siteconfig
	 * @param siteConfig the siteconfig to save
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveSiteConfig(SiteConfig siteConfig);
	
	/**
	 * save or update the link
	 * @param link
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void saveOrUpdateLink(Link link);
	
	/**
	 * remove the link by link's id
	 * @param id
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void removeLink(String id);
	
	/**
	 * get the link by link's id.
	 * @param id
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public Link getLink(String id);
	
	/**
	 * get all the links.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Link> getLinks();
	
	/**
	 * get the recommend links. 
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Link> getRecommendLinks();
	
	/**
	 * send the trackback ping.
	 * @param entryId
	 * @param trackbackURLs
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void sendTrackback(String entryId, List<String> trackbackURLs);
	
	/**
	 * approve the wait_for_approve status comment.
	 * @param commentId
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void approveComment(String commentId);
	
	/**
	 * against the comment ,set the comment's status to wait_for_approve
	 * @param commentId
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public void againstComment(String commentId);
	
	/**
	 * get the related entries.
	 * @param entry
	 * @param size
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Entry> getRelatedEntries(Entry entry, int size);
	
	/**
	 * get the entry's request referer.<br/>
	 * only contains the search engine's <i>Google.com</i> <i>Baidu.com</i>
	 * @param entry
	 * @param size the size of referer's count.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<RequestCounter> getRequestList(Entry entry, int size);
	
	/**
	 * get the request referer by the uri.
	 * @param uri
	 * @param size
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<RequestCounter> getRequestListByUri(String uri, int size);
	
	/**
	 * get all of the pages.<br/>
	 * Entry's type is <i>page</i>
	 * @param entryStatus the entryStatus.if null got all of the pages.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Entry> getPages(String entryStatus);
	
	/**
	 * get the published pages.cached.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Entry> getPublishedPages();
	
	
	/**
	 * get the distinct email about the entry's subscribe comment.
	 * @param entryId
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<String> getSubscribeEntryCommentEmails(String entryId);
	
	/**
	 * get the month list for archive.
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Date> getMonthList();
	
	/**
	 * Get the hot tags.
	 * @param size
	 * @return the tag list.
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public List<Tag> getHotTags(int size);
}
