package com.cqx;

import org.springframework.context.annotation.Configuration;

/**
 * Created by BG307435 on 2017/9/28.
 */
@Configuration
//@ImportResource(locations={"classpath:spring-ldap.xml"})
public class AppConfig {

    //https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#beans-java

//    @Bean
//    public LdapTemplate ldapTemplate() {
//        LdapContextSource contextSource = new LdapContextSource();
//        contextSource.setUrl("ldap://47.92.6.210:8080");
//        contextSource.setUserDn("cn=admin,dc=example,dc=org");
//        contextSource.setPassword("e1NTSEF9N2dkTXlRcW5MdEVVdGtDZ1o3OXpNeVZ3KzlwSDZMano");
//        contextSource.setBase("dc=example,dc=org");
//        LdapTemplate ldapTemplate = new LdapTemplate();
//        ldapTemplate.setContextSource(contextSource);
//        return ldapTemplate;
//    }
}
