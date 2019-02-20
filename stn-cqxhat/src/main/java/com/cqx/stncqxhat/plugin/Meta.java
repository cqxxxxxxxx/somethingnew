package com.cqx.stncqxhat.plugin;

import com.cqx.stncqxhat.support.apt.APT;
import lombok.Data;

import java.lang.annotation.*;

/**
 * 用于标记插件的metadata
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@APT
public @interface Meta {

    /**
     * 必填
     *
     * @return
     */
    int mode();

    /**
     * 必填
     *
     * @return
     */
    String pluginName();

    String version() default "0.0.1-alpha";

    String author() default "pikachu";


    @Data
    public class Info {

        /**
         * 0000 echo 插件
         * 0001 help 插件
         * 0010 chat 插件
         */
        private int mode;

        private String pluginName;

        private String version;

        private String author;

        public static final Info NULL_OBJECT = new Info();
    }

}
