package com.cqx;

import com.cqx.copy.ConfigurationKey;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        ConfigurationKey configurationKey = new ConfigurationKey<Boolean>("lombok.addGeneratedAnnotation", "Generate @javax.annotation.Generated on all generated code (default: false). Deprecated, use 'lombok.addJavaxGeneratedAnnotation' instead.") {
        };
        configurationKey.getType()
        System.out.println("Hello World!");
    }


}
