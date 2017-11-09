package com.cqx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.authentication.PasswordComparisonAuthenticator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;


/**
 * Created by BG307435 on 2017/9/28.
 */
@Configuration
@ImportResource(locations={"classpath:spring-ldap.xml"})
public class AppConfig {
    @Bean
    public BaseLdapPathContextSource baseLdapPathContextSource() {
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource("");
        return contextSource;
    }

    /**
     * 认证
     * @param ldapAuthenticator
     * @param ldapAuthoritiesPopulator
     * @return
     */
    @Bean
    public LdapAuthenticationProvider ldapAuthenticationProvider(LdapAuthenticator ldapAuthenticator, LdapAuthoritiesPopulator ldapAuthoritiesPopulator) {
        LdapAuthenticationProvider ldapAuthenticationProvider = new LdapAuthenticationProvider(ldapAuthenticator, ldapAuthoritiesPopulator);
        return ldapAuthenticationProvider;
    }

    /**
     * Authentication directly to the LDAP server ("bind" authentication).
     * 直接通过ldap服务认证
     * 在知道DN的规则前提下，通过设置DN pattern直接根据用户输入生成DN，然后去ldap认证
     * @return
     */
    @Bean
    public LdapAuthenticator ldapAuthenticator() {
        BindAuthenticator ldapAuthenticator = new BindAuthenticator(baseLdapPathContextSource());
        //设置dn pattern，从而直接生成dn，直接去ldap认证
        //比如contextSource设置了base为dc=springframework,dc=org
        //传入的参数为 ben
        //则生成的 DN为 uid=ben,dc=springframework,dc=org
        //如果还设置了search的base为ou=person 则生成的是uid=ben,ou=people,dc=springframework,dc=org
        ldapAuthenticator.setUserDnPatterns(new String[]{"uid={0},ou=people"});
        return ldapAuthenticator;
    }

    /**
     * Password comparison, where the password supplied by the user is compared with the one stored in the repository. This can either be done by retrieving the value of the password attribute and checking it locally or by performing an LDAP "compare" operation, where the supplied password is passed to the server for comparison and the real password value is never retrieved.
     * 密码比较
     * 1. 本地获取密码然后比较
     * 2. 传去ldap上比较(跟BindAuth不同的是需要自己去从ldap上获取DN，而不是自己本地根据规则生成DN)
     * @return
     */
    @Bean
    public PasswordComparisonAuthenticator passwordComparisonAuthenticator() {
        PasswordComparisonAuthenticator passwordComparisonAuthenticator = new PasswordComparisonAuthenticator(baseLdapPathContextSource());
        return passwordComparisonAuthenticator;
    }

    @Bean
    public LdapAuthoritiesPopulator ldapAuthoritiesPopulator() {
        return null;
    }
}
