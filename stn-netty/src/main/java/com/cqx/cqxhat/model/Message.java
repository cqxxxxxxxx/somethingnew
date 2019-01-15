package com.cqx.cqxhat.model;

import lombok.Data;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
@Data
public class Message {

    private User from;

    private String msg;
}
