package com.cqx.model;

import java.io.Serializable;

/**
 * Created by BG307435 on 2017/9/26.
 */
public class City implements Serializable {
    private static final long serialVersionUID = -1L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
