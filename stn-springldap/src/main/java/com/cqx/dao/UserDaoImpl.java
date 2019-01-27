package com.cqx.dao;

import com.cqx.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by BG307435 on 2017/11/6.
 */
@Repository
public class UserDaoImpl implements UserDao{
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private LdapTemplate ldapTemplate;
    @Autowired
    private ContextSource contextSource;


    @Override
    public User findByDn(String dn) {
//        return ldapTemplate.findByDn(dn, User.class);
        return null;
    }



}
