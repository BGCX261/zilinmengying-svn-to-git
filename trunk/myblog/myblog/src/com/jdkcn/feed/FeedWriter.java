/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		FeedWriter.java
 * Class:			FeedWriter
 * Date:			Dec 5, 2006 1:10:56 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 5, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.feed;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jdkcn.BlogFacade;
import com.jdkcn.domain.Category;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.SiteConfig;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 5, 2006 1:10:56 PM
 * @version $Id FeedWriter.java$
 */
public class FeedWriter {
	private BlogFacade blogFacade;

	public void setBlogFacade(BlogFacade blogFacade) {
		this.blogFacade = blogFacade;
	}
	
	public SyndFeed getFeed(String type){
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType(type);
		
		SiteConfig siteConfig = blogFacade.getDatabaseSiteConfig();
		
		feed.setTitle(siteConfig.getSiteName());
		feed.setLink(siteConfig.getSiteURL());
		feed.setDescription(siteConfig.getSiteSimpleAbout());
		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		Entry searchEntry = new Entry();
		searchEntry.setType(Entry.Type.POST);
		searchEntry.setEntryStatus(Entry.EntryStatus.PUBLISH);
		List<Entry> recentEntries = blogFacade.getEntryPage(searchEntry, 20, 0, null, null).getItems();
		for (Entry entry:recentEntries) {
			SyndEntry syndEntry = new SyndEntryImpl();
			syndEntry.setTitle(entry.getTitle());
			String link = null;
			if(StringUtils.isNotBlank(entry.getName())) {
				link = siteConfig.getSiteURL() + "/entry/" + entry.getName() + ".html";
			}else{
				link = siteConfig.getSiteURL() + "/entry/id/" + entry.getId() + ".html";
			}
			syndEntry.setLink(link);
			syndEntry.setAuthor(entry.getAuthor().getNickname());
			syndEntry.setPublishedDate(entry.getPostTime());
			List<SyndCategory> categories = new ArrayList<SyndCategory>();
			for(Category category:entry.getCategories()) {
				SyndCategory syndCategory = new SyndCategoryImpl();
				syndCategory.setName(category.getName());
				syndCategory.setTaxonomyUri(siteConfig.getSiteURL() + "/category/" + category.getName() + "/");
			}
			syndEntry.setCategories(categories);
			SyndContent content = new SyndContentImpl();
			content.setType("text/html");
			StringBuilder sb = new StringBuilder();
			sb.append(entry.getSummary())
			  .append("<p>").append("<a href=\"").append(link).append("\">[more..]</a></p>");
			content.setValue(sb.toString());
			syndEntry.setDescription(content);
			entries.add(syndEntry);
		}
		feed.setEntries(entries);
		return feed;
	}
}
