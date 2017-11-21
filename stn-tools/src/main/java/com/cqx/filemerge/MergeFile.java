package com.cqx.filemerge;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by BG307435 on 2017/11/21.
 */
public class MergeFile {

    public static void main(String[] args) {
        String[] strArray = {
                "C:\\Users\\BG307435\\Desktop\\mappedHTC.inp",
                "C:\\Users\\BG307435\\Desktop\\job-2.inp",
                "false",     //true 插入模式    false 替换模式=>最后传入的参数是截止符
                "** Interaction: Int-1",    //第一行
                "*Sfilm",       //第二行
                "**"};  //这里是截止符 匹配截止位置

        if (args.length == 0) {
            args = strArray;
        }

        String pathO = args[0];     //需要插入的文件
        String pathT = args[1];     //被插入
        boolean insertMode = Boolean.valueOf(args[2]);    //true 插入模式， false则最后一个为截止符的替换模式

        String prePattern = "";
        String sufPattern = "";
        List<String> patterns = getPatterns(args);
        String plainText = "";

        for (int i = 0; i < patterns.size(); i ++) {
            plainText += patterns.get(i) + "\n";
            if (i < patterns.size() - 1) {
                prePattern += wrapEscape(patterns.get(i)) + "\n";
            } else {
                if (!insertMode){    //有截止符的替换模式
                    sufPattern = wrapEscape(patterns.get(i));
                } else {
                    prePattern += wrapEscape(patterns.get(i));
                }
            }
        }
//      替换模式 零宽断言 匹配替换中间部分
        Pattern matcher = Pattern.compile("(?<=" + prePattern + ")([\\w\\W])*?(?=" + sufPattern + ")");
//      插入模式
        Pattern matcherInsert = Pattern.compile(prePattern);

        String[] splits = pathO.split("\\.");
        String suffix = splits[splits.length - 1];
        String out = MergeFile.class.getResource("/").getPath() + File.separator + "result." + suffix;
        System.out.println("输出文件路径:" + out);
        File target = new File(out);    //生成的文件
        if (target.exists()) {
            target.delete();
        }
        File fileO = new File(pathO);
        File fileT = new File(pathT);

        try {
            BufferedReader readerO = new BufferedReader(new FileReader(fileO));
            BufferedReader readerT = new BufferedReader(new FileReader(fileT));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target));

            String temp;
            StringBuilder sb = new StringBuilder();
            while ((temp = readerO.readLine()) != null) {
                sb.append(temp).append("\n");
            }
            readerO.close();

            String replaceContent = "\n" + sb.toString() + "\n"; //*FILM + 需要添加的内容

            sb = new StringBuilder();
            while ((temp = readerT.readLine()) != null) {
                sb.append(temp + "\n");
            }
            readerT.close();

            String result;
            if (insertMode) {
                result = matcherInsert.matcher(sb.toString()).replaceAll(plainText + replaceContent);
            } else {
                result = matcher.matcher(sb.toString()).replaceAll(replaceContent);
            }
            writer.write(result);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static String wrapEscape(String str) {
        return str.replaceAll("\\*", "\\\\*");
    }

    private static <T> List getPatterns(T[] array) {
        List<T> tList = new ArrayList<>();
        for (int i = 3; i < array.length; i++) {
            tList.add(array[i]);
        }
        return tList;
    }
}
