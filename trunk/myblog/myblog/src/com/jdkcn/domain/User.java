/*
 * Created on 2004-11-11
 *
 */
package com.jdkcn.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author <a href="mailto:Rory.cn@gmail.com">somebody</a>
 * 
 */
public class User extends BaseDomain {


	private static final long serialVersionUID = -5846058052631960116L;

    private String username;
    
    private String password;

    private String nickname;
    
    private Date birthday;

    private Integer gender;

    private Integer age;

    private String mail;

    private Integer qq;
    
    private String msn;
    
    private String gmail;
    
    private Integer icq;
    
    private String site;
    
    private List<Role> roles = new ArrayList<Role>();
    
    private List<UserRole> userRoles = new ArrayList<UserRole>();
    
    public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public User(){
    }
    
    public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

    public Date getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getIcq() {
		return icq;
	}

	public void setIcq(Integer icq) {
		this.icq = icq;
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	public UserRole addRole(Role role) {
		if (getRoles().indexOf(role)>=0) {
			throw new RuntimeException("role already assigned");
		}
		getRoles().add(role);
		role.getUsers().add(this);
		UserRole ur = new UserRole(this, role);
		getUserRoles().add(ur);
		return ur;
	}
	
	public void removeRole(int selectedRole) {
		if(selectedRole>getUserRoles().size()) {
			throw new RuntimeException("selected role does not exist");
		}
		UserRole ur = (UserRole) getUserRoles().remove(selectedRole);
		ur.getRole().getUsers().remove(this);
		getRoles().remove(ur.getRole());
	}

	public boolean equals(Object other) {
		if(other==null) return false;
		if(!(other instanceof User)) return false;
		return ((User)other).getUsername().equals(username);
	}

	public int hashCode() {
		return username.hashCode();
	}
	
	
}