package com.cqx.skeleton.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 没什么用
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/14
 */
@Data
@ConfigurationProperties(prefix = "fox")
public class FoxProperty {

    private String version;

    private String author;

    private Useless useless;

    @Data
    public static class Useless {

        private String meaningless;

    }

}
