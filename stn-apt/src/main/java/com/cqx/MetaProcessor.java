package com.cqx;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;
import java.io.IOException;
import java.util.Set;

import static com.cqx.Tools.*;

/**
 * 浪费时间
 * https://stackoverflow.com/questions/33159316/how-to-load-a-class-from-annotations-processor-in-eclipse
 * 好像不能再注解处理器阶段 加载自己的类
 * TODO: 吧注解处理器这模块移出来，单独作为一个独立的jar包提供给其他使用
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/20
 */
public class MetaProcessor implements IProcessor {
    private final String ANNOTATION_NAME = "Meta";

    /**
     * 无参构造器 用于serviceloader 加载
     * // 现在移出来了 不用spi来加载了。直接代码里new就好了
     */
    public MetaProcessor() {
    }


    @Override
    public void process(TypeElement annotation, Set<? extends Element> annotatedElements) {
        PRINT.INFO("TypeElement QualifiedName: " + annotation.getQualifiedName().toString());
        if (!ANNOTATION_NAME.equals(annotation.getSimpleName().toString())) {
            return;
        }
        annotatedElements.stream().forEach(el -> {
            PRINT.INFO("el SimpleName" + ((Element) el).getSimpleName());
            if (el.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) el;
                Meta meta = typeElement.getAnnotation(Meta.class);
                String author = meta.author();
                String pluginName = meta.pluginName();
                String version = meta.version();
                int mode = meta.mode();

                MethodSpec methodSpec = MethodSpec.methodBuilder("metadata")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(Meta.Info.class)
                        .addStatement("$T metaInfo = new $T()", Meta.Info.class, Meta.Info.class)
                        .addStatement("metaInfo.setMode($L)", mode)
                        .addStatement("metaInfo.setAuthor($S)", author)
                        .addStatement("metaInfo.setPluginName($S)", pluginName)
                        .addStatement("metaInfo.setVersion($S)", version)
                        .addStatement("return metaInfo")
                        .build();
                TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                        .addMethod(methodSpec)
                        .build();

                JavaFile javaFile = JavaFile.builder(ELEMENT.packageName(el), helloWorld)
                        .build();
                try {
                    javaFile.writeTo(FILER);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    /**
     * Visitor模式 可以扫描元素的变量
     * new MetaScanner().visit(Element e);
     */
    private static class MetaScanner extends ElementScanner8<Void, Void> {
        /**
         * Visits a package element.
         *
         * @param e the element to visit
         * @param p a visitor-specified parameter
         * @return a visitor-specified result
         */
        public Void visitPackage(PackageElement e, Void p) {
            PRINT.INFO(e.getQualifiedName());
            return null;
        }

        /**
         * Visits a type element.
         *
         * @param e the element to visit
         * @param p a visitor-specified parameter
         * @return a visitor-specified result
         */
        public Void visitType(TypeElement e, Void p) {
            PRINT.INFO(e.getQualifiedName());
            return null;
        }

        /**
         * Visits a variable element.
         *
         * @param e the element to visit
         * @param p a visitor-specified parameter
         * @return a visitor-specified result
         */
        public Void visitVariable(VariableElement e, Void p) {
            PRINT.INFO(e.getSimpleName());
            return null;
        }

        /**
         * Visits an executable element.
         *
         * @param e the element to visit
         * @param p a visitor-specified parameter
         * @return a visitor-specified result
         */
        public Void visitExecutable(ExecutableElement e, Void p) {
            PRINT.INFO(e.getSimpleName());
            return null;
        }

        /**
         * Visits a type parameter element.
         *
         * @param e the element to visit
         * @param p a visitor-specified parameter
         * @return a visitor-specified result
         */
        public Void visitTypeParameter(TypeParameterElement e, Void p) {
            PRINT.INFO(e.getSimpleName());
            return null;
        }
    }

}