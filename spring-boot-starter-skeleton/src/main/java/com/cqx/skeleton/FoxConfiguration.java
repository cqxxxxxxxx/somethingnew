package com.cqx.skeleton;

import com.cqx.skeleton.configurable.useless.UselessFox;
import com.cqx.skeleton.property.FoxProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/14
 */
@Configuration
public class FoxConfiguration {

    @Autowired
    FoxProperty foxProperty;

    /**
     * 全局统一的默认的UselessFox
     * 各个Fox接口也可以独自做实现 然后覆盖
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public UselessFox uselessFox(FoxProperty foxProperty) {
        UselessFox uselessFox = new UselessFox();
        uselessFox.setMeaningless(foxProperty.getUseless().getMeaningless());
        return uselessFox;
    }


}
