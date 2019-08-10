package com.cqx.stncqxhat.model;

import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
@Data
@AllArgsConstructor(staticName = "of")
public class User {
    private String name;

    private String ip;

    private Channel channel;

    /**
     * 0000 echo 插件
     * 0001 help 插件
     * 0002 chat 插件
     */
    private int mode;

    /**
     * 插件内部状态
     */
    private String subKeyWord;
}
