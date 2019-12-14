package com.cqx.skeleton;

import com.cqx.skeleton.annotation.EnableFox;
import com.cqx.skeleton.annotation.Fox;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 从自定义的路径里读取 bean definition 注册到registry中
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/9
 */
public class FoxRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    private ResourceLoader resourceLoader;

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(this.resourceLoader);
        scanner.addIncludeFilter(foxTypeFilter());
        //获取需要扫描的包
        Set<String> basePackages = getBasePackages(metadata);
        for (String basePackage : basePackages) {
            //利用scanner扫描生成beanDefinition
            Set<BeanDefinition> candidateComponents = scanner
                    .findCandidateComponents(basePackage);
            //遍历处理扫描获取到的BeanDefinition
            for (BeanDefinition candidateComponent : candidateComponents) {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {
                    // verify annotated class is an interface 必须是接口
                    AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                    AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                    Assert.isTrue(annotationMetadata.isInterface(),
                            "@Fox can only be specified on an interface");

                    Map<String, Object> attributes = annotationMetadata
                            .getAnnotationAttributes(
                                    Fox.class.getCanonicalName());
//                  注册beanDefinition到registry中
                    registerFox(registry, annotationMetadata, attributes);
                }
            }
        }

    }


    /**
     * 配置的扫描包属性
     *
     * @param importingClassMetadata
     * @return
     */
    protected Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(EnableFox.class.getCanonicalName());

        Set<String> basePackages = new HashSet<>();
        for (String pkg : (String[]) attributes.get("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (String pkg : (String[]) attributes.get("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (Class<?> clazz : (Class[]) attributes.get("basePackageClasses")) {
            basePackages.add(ClassUtils.getPackageName(clazz));
        }

        //如果没有配置，则默认注解所在包
        if (basePackages.isEmpty()) {
            basePackages.add(
                    ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }
        return basePackages;
    }

    /**
     * 获取基于classPath的beanDefinition扫描器
     *
     * @return
     */
    protected ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            /**
             * 是顶级类非内部类 && 不是注解类 => 添加到candidates中
             * @param beanDefinition
             * @return
             */
            @Override
            protected boolean isCandidateComponent(
                    AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (!beanDefinition.getMetadata().isAnnotation()) {
                        isCandidate = true;
                    }
                }
                return isCandidate;
            }
        };
    }


    /**
     * 扫描的过滤器 基于注解来过滤
     *
     * @return
     */
    private TypeFilter foxTypeFilter() {
        String annotation = Fox.class.getCanonicalName();
        return new TypeFilter() {
            /**
             * 如果类上有{@link Fox}注解则匹配成功
             * @param metadataReader
             * @param metadataReaderFactory
             * @return
             * @throws IOException
             */
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                return annotationMetadata.hasAnnotation(annotation);
            }
        };
    }


    /**
     * 正式注册到register中
     *
     * @param registry
     * @param annotationMetadata
     * @param attributes
     */
    private void registerFox(BeanDefinitionRegistry registry,
                             AnnotationMetadata annotationMetadata, Map<String, Object> attributes) {
        String className = annotationMetadata.getClassName();
        BeanDefinitionBuilder definition = BeanDefinitionBuilder
                .genericBeanDefinition(FoxFactoryBean.class);
        validate(attributes);
//        Class tClass = (Class) attributes.get("configuration");
        //注入配置
        definition.addPropertyValue("name", attributes.get("name"));
        definition.addPropertyValue("foxInterface", className);
        definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();
        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className,
                new String[]{});
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }

    private void validate(Map<String, Object> attributes) {
        AnnotationAttributes annotation = AnnotationAttributes.fromMap(attributes);
//        validateFallback(annotation.getClass("fallback"));
//        validateFallbackFactory(annotation.getClass("fallbackFactory"));
        Assert.notNull(attributes.get("name"),
                "name must not null");

    }
}

