/*
 * Created on 2005-12-31
 * author somebody
 */
package com.jdkcn.domain;


/**
 * @author <a href="mailto:Rory.cn@gmail.com">somebody</a>
 *
 */
public class Tag extends BaseDomain{
    /**
     * 
     */
    private static final long serialVersionUID = -4707210006094423001L;
    
    private String name;
    
    private String URLName; //this for url encode name
    
    private int count;
    
    public Tag(){}
    
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

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	return "tag:" + this.name;
    }
    
}
