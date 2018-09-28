package com.cqx.complie.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Knight.TestAnno
public class Knight {

    @TestAnno
    public String name;

    @TestAnno
    public String getName() {
        return "w s n d";
    }


    @Target({TYPE, FIELD, METHOD})
    @Retention(RUNTIME)
    public @interface TestAnno {

        String name() default "cqx";
    }
}
