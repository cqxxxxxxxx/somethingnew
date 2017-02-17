package com.cqx.hv;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * Created by Shan on 2017/2/17.
 */
@Configuration
public class ValidatorConfig {

    /**
     * 把 hibernateValidator bean实例化
     * @return
     */
    @Bean(name = "hvFactory")
    public LocalValidatorFactoryBean hvFactory(){
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setProviderClass(HibernateValidator.class);
        return validatorFactoryBean;
    }

    @Bean(name = "validator")
    public Validator validator(){
        return hvFactory().getValidator();
    }



}
