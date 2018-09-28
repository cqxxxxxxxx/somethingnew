package com.cqx.serial;

import java.io.Serializable;

public class OldVersion implements Serializable {

    private static final long serialVersionUID = 42L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
