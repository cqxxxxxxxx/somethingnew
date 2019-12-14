package com.cqx.verify.fluent.sure;

import com.cqx.verify.fluent.ISure;

import java.util.regex.Pattern;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/11/4
 */
public class StringSure {
    private static final String ILLEGAL_CHAR = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
    private static final Pattern ILLEGAL_CHAR_PATTERN = Pattern.compile(ILLEGAL_CHAR);


    public static ISure<String> notEmpty() {
        return x -> x != null && x.length() > 0;
    }

    public static ISure<String> notBlank() {
        return x -> x != null && !x.trim().isEmpty();
    }

    /**
     * 合法字符
     *
     * @return
     */
    public static ISure<String> legal() {
        return x -> !ILLEGAL_CHAR_PATTERN.matcher(x).find();
    }
}
