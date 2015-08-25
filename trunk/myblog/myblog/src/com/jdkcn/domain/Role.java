/*
 */
package com.jdkcn.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-4-26 22:46:49
 */
public class Role extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2281629217402297540L;
	
	private String name;
	private String description;
	private List<User> users = new ArrayList<User>();
	private Date createTime;
	
	public Role(){
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		users.add(user);
		user.getRoles().add(this);
	}
	
	public boolean equals(Object other) {
		if(other==null) return false;
		if(!(other instanceof Role)) return false;
		return ((Role) other).getName().equals(name);
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	
	
	
}
