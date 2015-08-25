/**
 * created on 2005-9-21
 */
package com.jdkcn.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author <a href="mailto:Rory.cn@gmail.com">somebody</a> </br>
 *         <p>
 *         This is the blog's category.Just a simple category not for many
 *         children category.
 *         </p>
 */
public class Category extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8505757607567501648L;

	private String name;

	private String description;

	private int order;

	private Category parent;

	private Set<Category> children;

	private Integer blogSize;

	private Boolean defaultCategory;

	private Date createTime;

	private Boolean secret;

	private String URLName;// this is for url encode name
	
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getURLName() {
		return URLName;
	}

	public void setURLName(String name) {
		URLName = name;
	}

	public Integer getBlogSize() {
		return blogSize;
	}

	public void setBlogSize(Integer blogSize) {
		this.blogSize = blogSize;
	}

	public Boolean getDefaultCategory() {
		return defaultCategory;
	}

	public void setDefaultCategory(Boolean defaultCategory) {
		this.defaultCategory = defaultCategory;
	}

	public Boolean getSecret() {
		return secret;
	}

	public void setSecret(Boolean secret) {
		this.secret = secret;
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

	public Category() {
	}

	public Set<Category> getChildren() {
		if(children ==null) {
			return new HashSet<Category>();
		}
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id:").append(getId()).append(" name:").append(name).append(
				" description:").append(description).append(" order:").append(
				order);
		if (parent != null) {
			sb.append(" parent.id:").append(parent.getId()).append(
					" parent.name:").append(parent.getName());
		} else {
			sb.append(" parent:null");
		}
		return sb.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
