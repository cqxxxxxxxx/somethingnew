package com.cqx.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;
import java.io.Serializable;

/**
 * @Entry Class level annotation indicating the objectClass definitions to which the entity maps. (required)
 * ldap中entry至少要满足 objectClasses 所指定的，可以多，不可以缺失，缺失就映射失败
 * 如果随便加个不存在的objectclass则会所有ldap中的entry都匹配失败，映射不到这歌POJO上来
 *
 * @Id indicating this is a dn
 *
 * @Attribute 用于映射POJO属性跟entry中的属性
 *
 * @DnAttribute 对应entry中的Dn,例如（ou=Development,ou=IT,ou=Departments,dc=example,dc=com）
 * 里面value对应ou或者dc（这边理解可能有问题）
 * index对应顺序，越大越前面（这边理解可能有问题）
 *
 *
 * Created by BG307435 on 2017/11/6.
 */
@Entry(objectClasses = {"organizationalPerson", "person", "top"})
public class User implements Serializable{
    @Id
    private Name dn;

    @Attribute(name = "cn")
    @DnAttribute(value = "cn", index = 0)
    private String fullName;

    @Attribute(name = "sn")
    private String lastName;

    @Attribute(name = "description")
    private String description;

    @Attribute(name = "userPassword")
    private String userPassword;

    @Transient
    private String country;

    @Transient
    private String company;

    @Attribute(name = "telephoneNumber")
    private String phone;

    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(
                this, obj);
    }

    public int hashCode() {
        return HashCodeBuilder
                .reflectionHashCode(this);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(
                this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
