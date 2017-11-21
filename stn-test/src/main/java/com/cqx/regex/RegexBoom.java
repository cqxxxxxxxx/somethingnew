package com.cqx.regex;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by BG307435 on 2017/11/21.
 */
public class RegexBoom {


    @Test
    public void test1() {
        String plainPattern = "cqx.*1234";
        Pattern pattern = Pattern.compile(plainPattern);
        String text = "cqxaaaaaaa1234";
        String result = pattern.matcher(text).replaceAll("我是啊啊啊");
        System.out.println(result);

        String textN = "cqxaaaaaaa\n1234";
        System.out.println(pattern.matcher(textN).replaceAll("我是啊啊啊"));

//        ([\w\W]*) 匹配包括换行符内所有字符
        String plainPattern1 = "cqx([\\w\\W]*)1234";
        Pattern pattern1 = Pattern.compile(plainPattern1);
        System.out.println(pattern1.matcher(textN).replaceAll("我是啊啊啊"));

        System.out.println("\\*");
        System.out.println("*".replaceAll("\\*", "\\\\*"));

        String textM = "cqx\n1234";
        String plainPattern2 = "cqx\n1234";
        Pattern pattern2 = Pattern.compile(plainPattern2);
        System.out.println(pattern2.matcher(textM).replaceAll("说说说说说"));


        String textH = "** Interaction: Int-1\n" +
                "*Sfilm\n" +
                "FIREFACE, F, 1000., 800.\n" +
                "** Interaction: Int-2";
        String plainPattern3 = "\\*\\* Interaction: Int-1\n\\*Sfilm([\\w\\W])*\\*\\* Interaction: Int-2";
        String plainPattern4 = "\\*\\*Interaction: Int-1\n\\*Sfilm\n([\\w\\W])*\\*\\*";
        Pattern pattern3 = Pattern.compile(plainPattern3);
        String xx = pattern3.matcher(textH).replaceAll("萨法是否sad");
        System.out.println(xx);
    }


//  测试零宽断言
    @Test
    public void test2() {
        String pre = "\\*\\* Interaction: Int-1\n\\*Sfilm\n";
        String suf = "\\*\\*";
        String pattern = "(?<=" + pre + ")([\\w\\W])*?(?=" + suf + ")";

        String text = "** Interaction: Int-1\n" +
                "*Sfilm\n" +
                "FIREFACE, F, 1000., 800.\n" +
                "** Interaction: Int-2\n" +
                "*Sfilm\n" +
                "NATCONVECTION, F, 25., 25.\n" +
                "** Interaction: Int-3\n" +
                "*Sfilm\n" +
                "*FILM";

        Pattern pattern1 = Pattern.compile(pattern);
        String result = pattern1.matcher(text).replaceAll("我啊啊啊" + "\n");
        System.out.println(result);
    }
}
