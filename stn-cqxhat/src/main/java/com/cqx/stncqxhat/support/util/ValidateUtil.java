package com.cqx.stncqxhat.support.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

/**
 *
 * @author BG307435
 * @date 2019/3/18
 */
@UtilityClass
public class ValidateUtil {
    private static final Pattern ILLEGAL_TERMS = Pattern.compile("[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t");
    private static final Pattern LEGAL_NUMBER_ABC = Pattern.compile("^[A-Z,a-z,0-9]*$");
    private static final Pattern LEGAL_NUMBER_WITH_DECIMAL_LIMIT2 = Pattern.compile("^-?0(\\.\\d{1,2})?$|^-?[1-9]\\d*(\\.\\d{1,2})?$");
    private static final Pattern LEGAL_POSITIVE_NUMBER_WITH_DECIMAL_LIMIT2 = Pattern.compile("^0(\\.\\d{1,2})?$|^[1-9]\\d*(\\.\\d{1,2})?$");
    private static final Pattern LEGAL_NUMBER = Pattern.compile("^[0-9]*$");


    /**
     * 正数值 且 小数位不超过2
     *
     * @param s
     * @return
     */
    public static String isPositiveNumberWithDecimalLimit2(final String s, final String message, final Object... values) {
        if (s == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (!LEGAL_POSITIVE_NUMBER_WITH_DECIMAL_LIMIT2.matcher(s).find()) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return s;
    }
    /**
     * 字母or数字
     *
     * @param s
     * @param message
     * @param values
     * @return
     */
    public static String isNumberOrAbc(final String s, final String message, final Object... values) {
        if (s == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (!LEGAL_NUMBER_ABC.matcher(s).find()) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return s;
    }

    public static String isNumber(final String s, final String message, final Object... values) {
        if (s == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (!LEGAL_NUMBER.matcher(s).find()) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return s;
    }

    public static String emptyOrIsNumber(final String s, final String message, final Object... values) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return isNumber(s, message, values);
    }

    public static <T extends CharSequence> T notEmpty(final T chars, final String message, final Object... values) {
        if (chars == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (chars.length() == 0) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return chars;
    }

    public static <T extends CharSequence> T lengthBetween(final T chars, final int min, final int max, final String message, final Object... values) {
        if (chars == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (chars.length() > max || chars.length() < min) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return chars;
    }

    public static <T extends CharSequence> T emptyOrLengthBetween(final T chars, final int min, final int max, final String message, final Object... values) {
        if (chars == null || chars.length() == 0) {
            return chars;
        }
        return lengthBetween(chars, min, max, message, values);
    }

    public static <T extends CharSequence> T noIllegalTerms(final T chars, final String message, final Object... values) {
        if (chars == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (ILLEGAL_TERMS.matcher(chars).find()) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return chars;
    }

    /**
     *  val 比 target 小
     * @param val
     * @param target
     * @param message
     * @param values
     */
    public static void lessThan(final Long val, Long target, final String message, final Object... values) {
        if (val == null || target == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (val < target) {
            return;
        }
        throw new IllegalArgumentException(String.format(message, values));
    }

//    public static void main(String[] args) {
//        ValidateUtil.emptyOrIsNumber("041601012001000683", "SSS");
//        ValidateUtil.emptyOrIsNumber("41601012001000683", "SSS");
//
//    }

//
//    public static <T> Then<T> of(T t) {
//        return Then.of(t);
//    }
//
//    private static class Then<T> {
//        private T t;
//        private Then(T t){ this.t = t; }
//        public static <E> Then<E> of(E e) {
//            return new Then<>(e);
//        }
//        public Then<T> then(Validate<T> v) {
//             v.validate(this.t);
//             return this;
//        }
//    }
//
//    @FunctionalInterface
//    public interface Validate<X> {
//        /**
//         * 1
//         * @param x
//         */
//        void validate(X x);
//    }
}
