package com.cqx.stncqxhat.support.keywords;

import com.cqx.stncqxhat.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/9
 */
@Data
@AllArgsConstructor(staticName = "of")
public class KeyWord implements KeyWordsHandler{

    /**
     * 关键字
     */
    private String key;

    /**
     * 相关使用信息之类介绍
     */
    private String info;

    /**
     * 处理器
     */
    private KeyWordsHandler handler;


    public void handle(Message message) {
        handler.handle(message);
    }
}
