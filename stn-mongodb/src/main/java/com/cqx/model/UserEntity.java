package com.cqx.model;




import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Entity 是javax.persistence提供的注解
 * @Document 是spring提供的注解 @Document for Spring Data MongoDB/Spring Data Elasticsearch.
 * 使用了@Document就表明该实体类用于mongoDB数据库，
 * 在多数据库的时候可以用@Document跟@Entity区分开来Repository适用什么数据库
 * Created by BG307435 on 2017/8/4.
 */
@Document
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -3258839839160856613L;

    @Id //指定作为MongoDB 的 objectId
    private String idString;
    private Long id;
    private String userName;
    private String passWord;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "idString='" + idString + '\'' +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
