package com.cqx.stncqxhat.plugin;

import lombok.Data;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@Data
public class Metadata {

    /**
     * 0000 echo 插件
     * 0001 help 插件
     * 0010 chat 插件
     */
    private int mode;

    private String pluginName;

    private String version;

    private String author;
}
