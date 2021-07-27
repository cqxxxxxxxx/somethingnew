package com.cqx.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileScanner {

    private String root = null;

    public List<String> flat() {
        List<String> files = new ArrayList<>();
        File f = new File(root);
        recursion(f, files);
        return files;
    }

    private void recursion(File file, List<String> r) {
        if (file.isFile()) {
            r.add(file.getName());
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursion(f, r);
            }
        }
    }

    public static void main(String[] args) {
        FileScanner fileScanner = new FileScanner();
        fileScanner.root = "E:\\文档\\惠生活\\废弃的广告案例";
        List<String> flat = fileScanner.flat();
        String collect = flat.stream().map(x -> {
            String replace = x.replace(".png", "");
            return "'" + replace + "'";
        }).collect(Collectors.joining(","));
        System.out.println(collect);
    }
}
