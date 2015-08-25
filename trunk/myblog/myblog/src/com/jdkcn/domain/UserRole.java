/*
 */
package com.jdkcn.domain;

import java.io.Serializable;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-4-26 22:57:36
 */
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6602815790191082033L;
	private User user;
	private Role role;
	
	@SuppressWarnings("unused")
	private UserRole() {}
	
	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
