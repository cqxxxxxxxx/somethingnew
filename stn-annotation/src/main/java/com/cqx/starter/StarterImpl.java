package com.cqx.starter;

import com.cqx.util.AnnotationProcessor;
import com.cqx.util.AwareProcesser;
import com.cqx.util.PackageScanner;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by Shan on 2017/1/20.
 */
public class StarterImpl implements Starter {
    private static final String DEFAULT_PATH = System.getProperty("user.dir") + "/stn-annotation/src/main/java/com/cqx/model";
    private AnnotationProcessor annotationProcessor = new AnnotationProcessor();
    private AwareProcesser awareProcesser = new AwareProcesser();

    @Override
    public void startLoad() {
        startLoad(DEFAULT_PATH);
    }

    @Override
    public void startLoad(String path) {
        List<String> javas =  PackageScanner.javaFiles(path);
        javas.stream().forEach(p -> {
            try {
                Class clazz = Class.forName(p);
                annotationProcessor.beanProcess(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        javas.stream().forEach(p -> {
            try {
                Class clazz = Class.forName(p);
                annotationProcessor.autowiredProcess(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        javas.stream().forEach(p -> {
            try {
                Class clazz = Class.forName(p);
                awareProcesser.awareProcess(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//        javas.stream().map(p -> {
//            try {
//                Class clazz = Class.forName(p);
//                annotationProcessor.beanProcess(clazz);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "";
//        }).map(p -> {
//            try {
//                Class clazz = Class.forName(p);
//                annotationProcessor.autowiredProcess(clazz);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "";
//        }).map(p -> {
//            try {
//                Class clazz = Class.forName(p);
//                awareProcesser.AwareProcess(clazz);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "";
//        }).close();
    }

}
