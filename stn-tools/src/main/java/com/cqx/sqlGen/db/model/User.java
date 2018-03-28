package com.cqx.sqlGen.db.model;


import com.cqx.sqlGen.Mapping;

/**
 * Created by BG307435 on 2017/11/13.
 */
public class User {

    @Mapping(index = 0, SQLIndex = 4)
    private Integer id;

    @Mapping(index = 1)
    private String username;

    @Mapping(index = 2)
    private String xm;  //姓名

    @Mapping(index = 3)
    private String phone;

    @Mapping(index = 6, SQLIndex = 1)
    private String newUsername;

    @Mapping(index = 7, SQLIndex = 2)
    private String newXm;

    @Mapping(index = 8, SQLIndex = 3)
    private String newPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewXm() {
        return newXm;
    }

    public void setNewXm(String newXm) {
        this.newXm = newXm;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }
}
