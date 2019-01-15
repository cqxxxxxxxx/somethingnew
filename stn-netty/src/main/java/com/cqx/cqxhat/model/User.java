package com.cqx.cqxhat.model;

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
}
