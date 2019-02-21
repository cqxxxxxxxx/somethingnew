package com.cqx;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.cqx.Tools.PRINT;

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

    private List<IProcessor> processorList = new ArrayList<>();

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        Tools.init(processingEnv);
        PRINT.INFO("当前线程的classloader" + Thread.currentThread().getContextClassLoader());
        processorList.add(new MetaProcessor());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        PRINT.INFO("处理器列表: " + processorList.stream().map(x -> x.getClass().toString()).collect(Collectors.joining()));
        PRINT.INFO("捕获到的注解列表: " + annotations.stream().map(x -> ((TypeElement) x).getSimpleName().toString()).collect(Collectors.joining()));
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            PRINT.INFO("发现注解", annotation);
            processorList.stream().forEach(x -> x.process(annotation, annotatedElements));
        }
        /**
         * true则是说明所有的注解处理器都处理完成了
         * 如果项目有引用lombok这边设为true，lombok的apt会执行不到，报错，所以返回false
         * 或者应该可以设置注解处理器的顺序
         */
        return false;
    }


}