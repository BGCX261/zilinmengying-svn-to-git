/*
 * Created on 2005-12-31
 * author somebody
 */
package com.jdkcn.domain;

import java.io.Serializable;

/**
 * @author <a href="mailto:Rory.cn@gmail.com">somebody</a>
 *
 */
public abstract class BaseDomain implements Serializable {
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
