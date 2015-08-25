/**
 * created on 2005-10-4
 */
package com.jdkcn.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author <a href="mailto:Rory.cn@gmail.com">somebody</a>
 * 
 */
public class Entry extends BaseDomain{
	/**
	 * 
	 */
	private static final long serialVersionUID = -326081814640501544L;


	private Set<Category> categories = new HashSet<Category>();
	private String title;
	private String content;
	private User author;
	private Date modifyTime;
	private Date postTime;
	private String postIP;
	private String comeFrom;
	private String comeFromURL;
	private String summary;
	private List<String> quotes =  new ArrayList<String>();
	private Set<Tag> tags =  new HashSet<Tag>();
    private Integer hits;
    private Integer commentSize;
    private Integer trackbackSize;
    private String name;
    private String type;
	private String entryStatus;
	private String commentStatus;
    
	/**
	 * The Entry's status.
	 * 
	 * @author <a href="mailto:rory.cn@gmail.com">Dingfu.Ye</a>
	 * @since Jun 25, 2007 1:43:24 PM
	 * @version $Id: Entry.java v$
	 */
	public static final class EntryStatus {
		private EntryStatus() {
		}

		/**
		 * Published status<br />
		 * <code><strong>publish</strong></code>
		 */
		public static final String PUBLISH = "publish";

		/**
		 * Draft status<br />
		 * <code><strong>draft</strong></code>
		 */
		public static final String DRAFT = "draft";

		/**
		 * Private status<br />
		 * <code><strong>private</strong></code>
		 */
		public static final String PRIVATE = "private";

		/**
		 * Top status<br />
		 * <code><strong>top</strong></code>
		 */
		public static final String TOP = "top";

		/**
		 * Trash status<br />
		 * <code><strong>trash</strong></code>
		 */
		public static final String TRASH = "trash";

		/**
		 * all of the entryStatus <br/> Contains: <code><strong>publish</strong></code>&nbsp;<code><strong>draft</strong></code>&nbsp;<code><strong>private</strong></code>&nbsp;<code><strong>top</strong></code>&nbsp;<code><strong>trash</strong></code>
		 */
		public static final List<String> ENTRY_STATUS = Arrays
				.asList(PUBLISH, DRAFT, PRIVATE, TOP, TRASH);
		
		/**
		 * the showing entry status.<br/>
		 * Contains :<code><i>publish</i></code>&nbsp;<code><i>top</i></code>
		 */
		public static final List<String> SHOWING_ENTRY_STATUS = Arrays.asList(PUBLISH, TOP);
	}

	/**
	 * The entry's comment status.
	 * 
	 * @author <a href="mailto:rory.cn@gmail.com">Dingfu.Ye</a>
	 * @since Jun 25, 2007 2:05:27 PM
	 * @version $Id: Entry.java v$
	 */
	public static final class CommentStatus {
		private CommentStatus() {
		}

		/**
		 * The entry is open for comment.<br />
		 * <code><strong>open</strong></code>
		 */
		public static final String OPEN = "open";

		/**
		 * The entry is not allowed comment.<br />
		 * <code><strong>close</strong></code>
		 */
		public static final String CLOSE = "close";

		/**
		 * The entry is commented by registered user only.<br />
		 * <code><strong>registered_only</strong></code>
		 */
		public static final String REGISTERED_ONLY = "registered_only";

		/**
		 * all of the comment status.<br />
		 * <code><strong>open</strong></code>&nbsp;<code><strong>close</strong></code>&nbsp;<code><strong>registered_only</strong></code>
		 */
		public static final List<String> COMMENT_STATUS = Arrays.asList(new String[] { OPEN, CLOSE, REGISTERED_ONLY });
	}

	/**
	 * The entry's ping status.
	 * 
	 * @author <a href="mailto:rory.cn@gmail.com">Dingfu.Ye</a>
	 * @since Jun 25, 2007 2:19:01 PM
	 * @version $Id: Entry.java v$
	 */
	public static final class PingStatus {
		private PingStatus() {
		}

		/**
		 * this entry is open for ping back.<br />
		 * <code><strong>open</strong></code>
		 */
		public static final String OPEN = "open";

		/**
		 * this entry is close for ping back.<br />
		 * <code><strong>close</strong></code>
		 */
		public static final String CLOSE = "close";

		/**
		 * all of the ping status.<br />
		 * <code><strong>open</strong></code>&nbsp;<code><strong>close</strong></code>
		 */
		public static final List<String> PING_STATUS = Arrays.asList(new String[] { OPEN, CLOSE });
	}

	/**
	 * the entry's type.
	 * 
	 * @author <a href="mailto:rory.cn@gmail.com">Dingfu.Ye</a>
	 * @since Jun 25, 2007 2:27:19 PM
	 * @version $Id: Entry.java v$
	 */
	public static final class Type {
		private Type() {
		}

		/**
		 * this entry is a post.<br />
		 * <code><strong>post</strong></code>
		 */
		public static final String POST = "post";

		/**
		 * this entry is a page.<br />
		 * <code><strong>page</strong></code>
		 */
		public static final String PAGE = "page";

		/**
		 * all of the entry's type.<br/>
		 * <code><strong>post</strong></code>&nbsp;<code><strong>page</strong></code>
		 */
		public static final List<String> TYPES = Arrays.asList(new String[] { POST, PAGE });
	}
    
    
    public String getEntryStatus() {
		return entryStatus;
	}

	public void setEntryStatus(String entryStatus) {
		if (EntryStatus.ENTRY_STATUS.contains(entryStatus)) {
			this.entryStatus = entryStatus;
		} else {
			this.entryStatus = EntryStatus.PUBLISH;
		}
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		if (CommentStatus.COMMENT_STATUS.contains(commentStatus)) {
			this.commentStatus = commentStatus;
		} else {
			this.commentStatus = CommentStatus.OPEN;
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCommentSize() {
		return commentSize;
	}

	public void setCommentSize(Integer commentSize) {
		this.commentSize = commentSize;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getTrackbackSize() {
		return trackbackSize;
	}

	public void setTrackbackSize(Integer trackbackSize) {
		this.trackbackSize = trackbackSize;
	}

	public Entry(){
    }
    
	public List<String> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<String> quotes) {
		this.quotes = quotes;
	}
	
	public String getPostIP() {
		return postIP;
	}

	public void setPostIP(String postIP) {
		this.postIP = postIP;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public String getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public String getComeFromURL() {
		return comeFromURL;
	}

	public void setComeFromURL(String comeFromURL) {
		this.comeFromURL = comeFromURL;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    
    public void addCategory(Category category){
    	this.categories.add(category);
    }
    
    public void removeCategory(Category category){
    	this.categories.remove(category);
    }
    
    public void addTag(Tag tag){
    	this.tags.add(tag);
    }
    
    public void removeTag(Tag tag){
    	this.tags.remove(tag);
    }
    
    public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Id:").append(getId())
		  .append(" Author:").append(author.getNickname())
		  .append(" PostTime").append(postTime)
		  .append(" Title:").append(title);
		return sb.toString();
	}
    
	public boolean equals(Object other) {	
		if(other==null) return false;
		if(!(other instanceof Entry)) return false;
		return ((Entry)other).getId().equals(id);
	}

	public int hashCode() {
		return id.hashCode();
	}
    
}
