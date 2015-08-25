/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		PJblog2Myblog.java
 * Class:			PJblog2Myblog
 * Date:			Dec 10, 2006 9:20:27 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 10, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.tools;

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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdkcn.BlogFacade;
import com.jdkcn.domain.Category;
import com.jdkcn.domain.Comment;
import com.jdkcn.domain.Entry;
import com.jdkcn.domain.Role;
import com.jdkcn.domain.Tag;
import com.jdkcn.domain.User;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 10, 2006 9:20:27 PM
 * @version $Id PJblog2Myblog.java$
 * @deprecated Move it to initDataManager
 * @see #com.jdkcn.InitDataManager
 */
public class PJblog2Myblog {
	
	private String dataUrl;
	
	private BlogFacade blogFacade;
	
	private Map<String, Tag> tagMap = new HashMap<String, Tag>();
	
	private Map<String, Category> categoryMap = new HashMap<String, Category>();
	
	public PJblog2Myblog(){
		dataUrl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=D:/pjblog.mdb";
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
	
	public void setBlogFacade(BlogFacade blogFacade) {
		this.blogFacade = blogFacade;
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
				Category category = new Category();
				category.setName(rs.getString("cate_name"));
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
				entry.setContent(rs.getString("log_Content"));
				entry.setHits(rs.getInt("log_ViewNums"));
				entry.addCategory(categoryMap.get(categoryId));
				entry.setPostIP("127.0.0.1");
				for(String str:strTags){
					entry.addTag(tagMap.get(str));
				}
				blogFacade.saveOrUpdateEntry(entry);
				int logId = rs.getInt("log_ID");
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
			user.setUsername("rory");
			user.setPassword("rory");
			user.setAge(24);
			user.setGender(Constants.MALE);
			user.setMail("rory.cn@gmail.com");
			user.setNickname("莫多");
			user.setSite("http://jdkcn.com");
			user.addRole(role);
			blogFacade.saveOrUpdateUser(user);
			return user;
		}
		return author;
	}
	
	public static void  main(String[] args)throws Exception{
		String[] configFiles = {"spring/global.xml", "spring/myblog-context.xml"};
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configFiles);
		PJblog2Myblog pJblog2Myblog = new PJblog2Myblog();
		pJblog2Myblog.setBlogFacade((BlogFacade)applicationContext.getBean("blogFacade"));
		pJblog2Myblog.moveTags();
		pJblog2Myblog.moveCategory();
		pJblog2Myblog.moveEntry();
	}
}
