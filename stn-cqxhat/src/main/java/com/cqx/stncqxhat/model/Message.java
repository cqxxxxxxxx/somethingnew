package com.cqx.stncqxhat.model;

import com.cqx.stncqxhat.constant.ServerConst;
import lombok.Data;

import java.io.Serializable;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
@Data
public class Message implements Serializable {

    private User from;

    private String msg;

    public static Message of(User from, String msg) {
        Message message = new Message();
        message.setMsg(msg);
        message.setFrom(from);
        return message;
    }
}
