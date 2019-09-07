package com.cqx.dao;

import com.cqx.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapName;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Created by BG307435 on 2017/11/6.
 */
@Component
public class UserDaoImpl implements UserDao{
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private LdapTemplate ldapTemplate;
    @Autowired
    private ContextSource contextSource;


    /**
     * 认证
     * @param userDn
     * @param credentials
     * @return
     */
    @Override
    @Transactional
    public boolean auth(String userDn, String credentials) {
        DirContext ctx = null;
        try {
            ctx = contextSource.getContext(userDn, credentials);
            return true;
        } catch (Exception e) {
            // Context creation failed - authentication did not succeed
            logger.error("Login failed", e);
            return false;
        } finally {
            // It is imperative that the created DirContext instance is always closed
            LdapUtils.closeContext(ctx);
        }
    }

    /**
     * 根据某个attribute 或者什么搜索条件获取DN
     * @param account
     * @return
     */
    private String getDnForUser(String account) {
        List<String> result = ldapTemplate.search(
                query().where("sAMAccountName").is(account),
                new AbstractContextMapper() {
                    protected String doMapFromContext(DirContextOperations ctx) {
                        return ctx.getNameInNamespace();
                    }
                });
        if(result.size() != 1) {
            throw new RuntimeException("User not found or not unique");
        }
        return result.get(0);
    }


    @Override
    public void create(User user) {
        ldapTemplate.create(user);
    }

    @Override
    public void update(User user) {
        ldapTemplate.update(user);
    }

    @Override
    public void delete(User user) {
        ldapTemplate.delete(user);
    }

    /**
     * 根据特定属性查询
     * @return
     */
    @Override
    public List<String> getAllUserNames() {
        String[] attrs = {"cn", "description", "mail", "mobile", "CN"};
        return ldapTemplate.search(query()
                        .where("objectclass").is("person"),
                new AttributesMapper<String>() {
                    public String mapFromAttributes(Attributes attrs) throws NamingException {
                        return attrs.get("cn").get().toString();
                    }
                });
    }

    @Override
    public List<User> findAll() {
        return ldapTemplate.findAll(User.class);
    }

    /**
     * 根据 DN 区查
     * @param company
     * @param fullname
     * @return
     */
    @Override
    public User findByPrimaryKey(String company, String fullname) {
        LdapName dn = buildDn(company, fullname);
        User user = ldapTemplate.findByDn(dn, User.class);

        return user;
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public List<User> search(String name) {
        return ldapTemplate.search(query().where("sAMAccountName").is(name),
                new AttributesMapper<User>() {
                    public User mapFromAttributes(Attributes attrs) throws NamingException {
                        User user = new User();
                        user.setFullName(attrs.get("cn").get().toString());
                        return user;
                    }
                });
    }

    private LdapName buildDn(User person) {
        return buildDn(person.getCompany(), person.getFullName());
    }

    /**
     * 构建出DN（基于base）（distinguished name 类似于数据库主键，唯一定位一个Entry）
     * @param company
     * @param fullname
     * @return
     */
    private LdapName buildDn(String company, String fullname) {
        return LdapNameBuilder.newInstance()
                .add("CN", company)
                .add("CN", fullname)
                .build();
    }

}
