package com.cqx.util;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Shan on 2017/1/20.
 */
public class PackageScanner {
    //static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //左移4位，16

    public static List<String> classFiles(String path){
        File file = new File(path);
        File[] files = file.listFiles();
        if (files.length == 0)
            return null;
        List<String> classes = Arrays.stream(files)
                .filter(file1 -> file1.getAbsolutePath().endsWith("class"))
                .map(file1 -> {
                    String fullPath = file1.getAbsolutePath();
                    int begin = fullPath.indexOf("com");
                    int end = fullPath.length()-6;
                    return fullPath.substring(begin, end);
                })
                .collect(Collectors.toList());
        return classes;
    }
    public static List<String> javaFiles(String path){
        File file = new File(path);
        File[] files = file.listFiles();
        if (files.length == 0)
            return null;
        List<String> javas = Arrays.stream(files)
                .filter(file1 -> file1.getAbsolutePath().endsWith("java"))
                .map(file1 -> {
                    String fullPath = file1.getAbsolutePath().replace("/", ".");
                    int begin = fullPath.indexOf("com");
                    int end = fullPath.length()-5;
                    return fullPath.substring(begin, end);
                })
                .collect(Collectors.toList());
        return javas;
    }
}
