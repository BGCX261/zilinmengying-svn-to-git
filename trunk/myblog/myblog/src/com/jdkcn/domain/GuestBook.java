/*
 */
package com.jdkcn.domain;

import java.util.Date;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-5-7 18:15:53
 */
public class GuestBook extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072387142090524700L;

	private String authorName;

	private String authorMail;

	private String authorSite;

	private Date postTime;

	private String postIP;

	private Boolean subscribe;

	private String face;

	private String content;

	private User replyAuthor;

	private Date replyTime;

	private String replyContent;

	private Boolean hidden;
	
	public GuestBook(){
	}
	
	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Boolean getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}

	public String getAuthorMail() {
		return authorMail;
	}

	public void setAuthorMail(String authorMail) {
		this.authorMail = authorMail;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorSite() {
		return authorSite;
	}

	public void setAuthorSite(String authorSite) {
		this.authorSite = authorSite;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
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

	public User getReplyAuthor() {
		return replyAuthor;
	}

	public void setReplyAuthor(User replyAuthor) {
		this.replyAuthor = replyAuthor;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void addReply(User replyAuthor, String replyContent) {
		this.replyContent = replyContent;
		this.replyAuthor = replyAuthor;
		this.replyTime = new Date();
	}

}
