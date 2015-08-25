package com.jdkcn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.jdkcn.domain.Category;
import com.jdkcn.domain.Comment;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.Link;
import com.jdkcn.domain.Role;
import com.jdkcn.domain.SiteConfig;
import com.jdkcn.domain.Tag;
import com.jdkcn.domain.User;
import com.jdkcn.util.Constants;


/**
 * initialization the data.
 * 
 * @author <a href="mailto:Rory.cn@gmail.com">somebody</a>
 * 
 */
public class InitDataManager implements InitializingBean {

	private Log log = LogFactory.getLog(this.getClass());
	
    private Boolean initialization;
    
    private Boolean importFromPJblog;
    
    private BlogFacade blogFacade;
    
    private String username;
    
    private String password;
    
    private String email;
    
	private String dataUrl;
	
	private Map<String, Tag> tagMap = new HashMap<String, Tag>();
	
	private Map<String, Category> categoryMap = new HashMap<String, Category>();
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setImportFromPJblog(Boolean importFromPJblog) {
		this.importFromPJblog = importFromPJblog;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setBlogFacade(BlogFacade blogFacade) {
		this.blogFacade = blogFacade;
	}
	
	
	public Boolean getInitialization() {
		return initialization;
	}


	public void setInitialization(Boolean initialization) {
		this.initialization = initialization;
	}


	@SuppressWarnings("deprecation")
	public void afterPropertiesSet() throws Exception {

		if(importFromPJblog) {
			moveTags();
			moveCategory();
			moveEntry();
			return;
		}
		
        if(initialization==null||!initialization)
            return;
        
		log.info("[myblog]init database:");
		
		SiteConfig siteConfig = blogFacade.getDatabaseSiteConfig();
		if (siteConfig==null) {
			log.info("[myblog]copy the xml config to database.");
			siteConfig = blogFacade.getSiteConfig();
			siteConfig.setTheme("default");
			blogFacade.saveOrUpdateSiteConfig(siteConfig);
		}
		if (blogFacade.getUserPage(new User(), 10, 0, null, null).getItems().size()>0){
			log.info("[myblog]:seem inited! return");
			return;
		}
		
		/*
		 * Create Role
		 */
		Role role = new Role();
		role.setName("Administrator");
		role.setDescription("The administrator's role");
		role.setCreateTime(new Date());
		blogFacade.saveOrUpdateRole(role);
		
		/*
		 * Create User
		 */

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setAge(24);
		user.setGender(Constants.MALE);
		user.setMail(email);
		user.setNickname("Admin");
		user.setSite("http://jdkcn.com");
		user.addRole(role);
		blogFacade.saveOrUpdateUser(user);
		
		/*
		 * Create Category
		 */
		Category category = new Category();
		category.setName("Java");
		category.setOrder(0);
		category.setDefaultCategory(true);
        category.setCreateTime(new Date());
        category.setSecret(false);
		blogFacade.saveOrUpdateCategory(category);
		log.info(category);
		
		/**
		 * create entry
		 */
		Entry entry = new Entry();
		entry.setTitle("Hello Myblog!");
		entry.setAuthor(user);
		entry.setName("Hello-Myblog");
		entry.addCategory(category);
		entry.setContent("Welcome to Myblog. This is your first post. Edit or delete it, then start blogging!<br/>欢迎来到Myblog。这是你的第一篇日志，你可以修改或者删除它，然后开始博吧:)。");
		entry.setSummary(entry.getContent());
		entry.setPostIP("127.0.0.1");
		entry.setPostTime(new Date());
		entry.setCommentSize(0);
		entry.setTrackbackSize(0);
		entry.setHits(0);
		entry.setEntryStatus(Entry.EntryStatus.PUBLISH);
		entry.setType(Entry.Type.POST);
		Tag tag = new Tag();
		tag.setName("Myblog");
		tag.setCount(1);
		blogFacade.saveOrUpdateTag(tag);
		entry.addTag(tag);
		blogFacade.saveOrUpdateEntry(entry);
		log.info(entry);
		
		/**
		 * create comment
		 */
		Comment comment = new Comment();
		comment.setEntry(entry);
		comment.setAuthorMail("rory.cn@gmail.com");
		comment.setAuthorName("莫多泡泡");
		comment.setAuthorSite("http://jdkcn.com");
		comment.setPostIP("127.0.0.1");
		comment.setPostTime(new Date());
		comment.setStatus(Comment.Status.APPROVED);
		comment.setContent("感谢您选择 <strong>Myblog</strong>,更多技术支持请访问 <a href=\"http://groups.google.com/group/myblogdev\"><strong>Myblog Dev</strong></a>");
		blogFacade.saveOrUpdateComment(comment);
		log.info(comment);
		
		/**
		 * create page
		 */
		Entry page = new Entry();
		page.setTitle("About");
		page.setName("about");
		page.setAuthor(user);
		page.setContent("This is an example of a Myblog page, you could edit this to put information about yourself or your site so readers know where you are coming from. You can create as many pages like this one as you like and manage all of your content inside of Myblog.<br/>这是myblog的页面，你可以修改或这删除它，在这里你可以写一些介绍站点或者作者的内容。你也可以进入后台添加更多的页面。<br />");
		page.setPostIP("127.0.0.1");
		page.setPostTime(new Date());
		page.setCommentSize(0);
		page.setTrackbackSize(0);
		page.setHits(0);
		page.setEntryStatus(Entry.EntryStatus.PUBLISH);
		page.setType(Entry.Type.PAGE);
		page.addTag(tag);
		blogFacade.saveOrUpdatePage(page);
		log.info(page);
		
		/**
		 * create link
		 */
		Link link = new Link();
		link.setName("莫多泡泡");
		link.setCreateTime(new Date());
		link.setURL("http://www.jdkcn.com");
		link.setDescription("莫多泡泡,泡出好心情,泡出好技术!");
		link.setImgURL("http://jdkcn.com/logo.gif");
		link.setOrder(0);
		link.setRecommend(true);
		blogFacade.saveOrUpdateLink(link);
		log.info(link);
	}
	
	private Connection getConnection(){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conn = DriverManager.getConnection(dataUrl);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void moveTags()throws Exception{
		String sql = "select * from blog_tag";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Tag tag = new Tag();
				tag.setName(rs.getString("tag_name"));
				String tagId = rs.getString("tag_id");
				blogFacade.saveOrUpdateTag(tag);
				tagMap.put(tagId, tag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			pstmt.close();
			conn.close();
		}
	}
	
	public void moveCategory() throws Exception{
		String sql = "select * from blog_category";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getBoolean("cate_OutLink")) {
					continue;
				}
				Category category = new Category();
				category.setName(rs.getString("cate_name"));
//				if (category.getName().equalsIgnoreCase("index")
//						|| category.getName().equalsIgnoreCase("tags")
//						|| category.getName().equalsIgnoreCase("guestbook")
//						|| category.getName().equalsIgnoreCase("个人档案")
//						|| category.getName().equalsIgnoreCase("软件下载")) {
//					continue;
//				}
				category.setOrder(0);
				category.setDefaultCategory(false);
				category.setCreateTime(new Date());
				category.setSecret(false);
				String categoryId = rs.getString("cate_id");
				
				blogFacade.saveOrUpdateCategory(category);
				categoryMap.put(categoryId, category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			pstmt.close();
			conn.close();
		}
		
	}
	
	public void moveEntry() throws Exception{
		String sql = "select * from blog_content";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String tagStr = rs.getString("log_tag");
				tagStr = tagStr.replace("}{", "__");
				tagStr = tagStr.replace("{", "");
				tagStr = tagStr.replace("}", "");
				List<String> strTags = Arrays.asList(tagStr.split("__"));
				String categoryId = rs.getString("log_CateID");
				Entry entry = new Entry();
				entry.setAuthor(getAuthor());
				entry.setTitle(rs.getString("log_Title"));
				entry.setCommentSize(rs.getInt("log_CommNums"));
				entry.setComeFrom(rs.getString("log_From"));
				entry.setComeFromURL(rs.getString("log_FromUrl"));
				entry.setPostTime(new Date(rs.getTimestamp("log_PostTime").getTime()));
				entry.setContent(StringEscapeUtils.unescapeHtml(rs.getString("log_Content")));
				entry.setHits(rs.getInt("log_ViewNums"));
				entry.addCategory(categoryMap.get(categoryId));
				entry.setPostIP("127.0.0.1");
				int logId = rs.getInt("log_ID");
				entry.setName(String.valueOf(logId));
				for(String str:strTags){
					if(tagMap.get(str)!=null && tagMap.get(str).getId()!=null) {
						entry.addTag(tagMap.get(str));
					}
				}
				blogFacade.saveOrUpdateEntry(entry);
				for(Comment comment:getComments(logId)) {
					comment.setEntry(entry);
					blogFacade.saveOrUpdateComment(comment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pstmt.close();
			conn.close();
		}
		
	}
	
	public List<Comment> getComments(int entryId) throws Exception{
		String sql = "select * from blog_comment where blog_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Comment> comments = new ArrayList<Comment>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, entryId);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				Comment comment = new Comment();
				comment.setAuthorName(rs.getString("comm_Author"));
				comment.setContent(rs.getString("comm_Content"));
				comment.setIsSubscribe(false);
				comment.setPostIP(rs.getString("comm_PostIP"));
				comment.setPostTime(new Date(rs.getTimestamp("comm_PostTime").getTime()));
				comments.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
		return comments;
	}
	
	public User getAuthor()throws Exception{
		List<User> userList = blogFacade.getUserPage(new User(), 1, 0, null, null).getItems();
		User author = null;
		if(userList.size()>0) {
			author = userList.get(0);
		}else{
			/*
			 * Create Role
			 */
			Role role = new Role();
			role.setName("Administrator");
			role.setDescription("The administrator's role");
			role.setCreateTime(new Date());
			blogFacade.saveOrUpdateRole(role);
			
			/*
			 * Create User
			 */

			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setAge(24);
			user.setGender(Constants.MALE);
			user.setMail(email);
			user.setNickname(username);
			user.setSite("http://jdkcn.com");
			user.addRole(role);
			blogFacade.saveOrUpdateUser(user);
			return user;
		}
		return author;
	}
	

}
