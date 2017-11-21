package com.cqx.path;

import org.junit.Test;

import java.io.File;

/**
 * 路径问题的一些demo
 * Created by Shan on 2017/2/23.
 */
public class PathBoom {


    /**
     * 读取resources 下的文件
     */
    @Test
    public void test1(){
        String path = "demo.preperties";
        File file = new File(path);
        System.out.println(file.getName());

        String path1 = "demo.xml";
        File file1 = new File(path1);
        System.out.println(file1.getName());
    }

    @Test
    public void test2() {
        System.out.println(PathBoom.class.getResource("/").getPath()); //到classes目录
        System.out.println(PathBoom.class.getResource("")); //到具体包所在目录
        System.out.println(PathBoom.class.getClassLoader().getResource("")); //到classes目录
        System.out.println(PathBoom.class.getClassLoader().getResource("/")); // null
    }



}
