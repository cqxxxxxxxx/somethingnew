package com.cqx.skeleton;

import com.cqx.skeleton.annotation.EnableFox;
import com.cqx.skeleton.property.FoxProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/14
 */
@Configuration
@ConditionalOnClass(EnableFox.class)
@EnableConfigurationProperties(FoxProperty.class)
public class FoxAutoConfiguration {


}
