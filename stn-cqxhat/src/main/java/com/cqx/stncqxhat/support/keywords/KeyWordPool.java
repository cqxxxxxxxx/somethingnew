package com.cqx.stncqxhat.support.keywords;

import com.cqx.stncqxhat.plugin.Plugin;
import com.cqx.stncqxhat.support.util.ValidateUtil;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/9
 */
public class KeyWordPool {
    private Plugin owner;
    private Map<String, KeyWord> keyWordMap = new HashMap<>();
    private KeyWord defaultKeyWord;

    private KeyWordPool(){}

    public static KeyWordPool of(Plugin plugin) {
        KeyWordPool keyWordPool = new KeyWordPool();
        keyWordPool.owner = plugin;
        return keyWordPool;
    }

    /**
     * 获取KeyWord
     * @param s
     * @return
     */
    public KeyWord get(String s) {
        Objects.requireNonNull(s, "参数不能为空");
        return keyWordMap.get(s);
    }

    /**
     * 是否关键字
     * @param s
     * @return
     */
    public boolean isKeyWords(String s) {
        Objects.requireNonNull(s, "参数不能为空");
        KeyWord keyWord = keyWordMap.get(s);
        return keyWord == null;
    }

    /**
     * 添加关键字
     * @param key
     * @param info
     * @return
     */
    public KeyWordPool addKeyWord(String key, String info, KeyWordsHandler handler) {
        ValidateUtil.notEmpty(key, "参数不能为空");
        ValidateUtil.notEmpty(info, "参数不能为空");
        KeyWord keyWord = KeyWord.of(key, info, handler == null ? EmptyHandler.getInstance() : handler);
        keyWordMap.put(key, keyWord);
        return self();
    }

    /**
     * 默认的
     * @param key
     * @param info
     * @return
     */
    public KeyWordPool withDefault(String key, String info, KeyWordsHandler handler) {
        ValidateUtil.notEmpty(key, "参数不能为空");
        ValidateUtil.notEmpty(info, "参数不能为空");
        KeyWord keyWord = KeyWord.of(key, info, handler == null ? EmptyHandler.getInstance() : handler);
        this.defaultKeyWord = keyWord;
        return self();
    }

    public KeyWord defaultKeyWord() {
        return defaultKeyWord;
    }

    private KeyWordPool self() {
        return this;
    }
}
