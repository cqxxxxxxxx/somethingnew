package com.cqx.stncqxhat.support.apt;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 支持所有注解
 * 支持jdk版本最新为8
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/20
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
/**
 * google的库，可以自动生成spi的配置即meta-info下的文件。
 * 注解处理器，java编译时会提前通过spi机制吧该类加载进去，然后该注解处理器可以作用于后续的代码编译
 *
 * 关于注解处理器的其他触发方法可以参考 https://www.baeldung.com/java-annotation-processing-builder
 */
@AutoService(Processor.class)
public class DefaultAnnotationProcessor extends AbstractProcessor {

    /**
     * 里面有一些工具类 比如messager用来打印信息
     */
    private ProcessingEnvironment processingEnv;
    private List<IProcessor> processorList = new ArrayList<>();
    private Messager messager;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        this.messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "当前线程的classloader" + Thread.currentThread().getContextClassLoader());
        MetaProcessor metaProcessor = new MetaProcessor();
        metaProcessor.setMessager(messager);
        processorList.add(metaProcessor);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE, "12312" + processorList.stream().map(x -> x.getClass().toString()).collect(Collectors.joining()));
        messager.printMessage(Diagnostic.Kind.NOTE, "12312" + annotations.stream().map(x -> ((TypeElement) x).getSimpleName().toString()).collect(Collectors.joining()));

        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            messager.printMessage(Diagnostic.Kind.NOTE, "发现注解", annotation);
            processorList.stream().forEach(x -> x.process(annotation, annotatedElements));
        }
        /**
         * true则是说明所有的注解处理器都处理完成了
         * 如果项目有引用lombok这边设为true，lombok的apt会执行不到，报错，所以返回false
         * 或者应该可以设置注解处理器的顺序
         */
        return false;
    }

    /**
     * @desc:
     * @version: 1.0.0
     * @author: cqx
     * @Date: 2019/2/20
     */
    public static interface IProcessor {

        void process(TypeElement annotation, Set<? extends Element> annotatedElements);

        void setMessager(Messager messager);
    }

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
//    @AutoService(IProcessor.class)
    public static class MetaProcessor extends ElementScanner8<Void, Void> implements IProcessor {

        private Messager messager;
        private final String ANNOTATION_NAME = "com.cqx.stncqxhat.plugin.Meta";

        /**
         * 无参构造器 用于serviceloader 加载
         */
        public MetaProcessor() {
        }


        @Override
        public void process(TypeElement annotation, Set<? extends Element> annotatedElements) {
            messager.printMessage(Diagnostic.Kind.WARNING, "TypeElement QualifiedName: " + annotation.getQualifiedName().toString());
            if (ANNOTATION_NAME.equals(annotation.getQualifiedName().toString())) {
                annotatedElements.stream().forEach(this::scan);
            }
            ;
        }

        @Override
        public void setMessager(Messager messager) {
            this.messager = messager;
        }


        /**
         * Visits a package element.
         *
         * @param e the element to visit
         * @param p a visitor-specified parameter
         * @return a visitor-specified result
         */
        public Void visitPackage(PackageElement e, Void p) {
            messager.printMessage(Diagnostic.Kind.NOTE, e.getQualifiedName());
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
            messager.printMessage(Diagnostic.Kind.NOTE, e.getQualifiedName());
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
            messager.printMessage(Diagnostic.Kind.NOTE, e.getSimpleName());
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
            messager.printMessage(Diagnostic.Kind.NOTE, e.getSimpleName());
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
            messager.printMessage(Diagnostic.Kind.NOTE, e.getSimpleName());
            return null;
        }

    }
}