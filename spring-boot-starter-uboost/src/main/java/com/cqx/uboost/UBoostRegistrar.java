package com.cqx.uboost;

import com.cqx.uboost.annotation.EnableUBoost;
import com.cqx.uboost.annotation.UBoost;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
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
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/8
 */
public class UBoostRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {
    // patterned after Spring Integration IntegrationComponentScanRegistrar
    // and RibbonClientsConfigurationRegistrar

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
        scanner.addIncludeFilter(uboostTypeFilter());
        Set<String> basePackages = getBasePackages(metadata);
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponents = scanner
                    .findCandidateComponents(basePackage);
            for (BeanDefinition candidateComponent : candidateComponents) {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {
                    // verify annotated class is an concrete 必须是实现类，不能是接口或者abstract
                    AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                    AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                    Assert.isTrue(annotationMetadata.isConcrete(),
                            "@UBoost can only be specified on an concrete class");

                    Map<String, Object> attributes = annotationMetadata
                            .getAnnotationAttributes(
                                    UBoost.class.getCanonicalName());

                    String name = getClientName(attributes);
                    registerClientConfiguration(registry, name,
                            attributes.get("configuration"));
//                  注册beanDefinition到registry中
                    registerUBoost(registry, annotationMetadata, attributes);
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
                .getAnnotationAttributes(EnableUBoost.class.getCanonicalName());

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
            @Override
            protected boolean isCandidateComponent(
                    AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                //是顶级类非内部类 && 不是注解类 => 添加到candidates中
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
    private TypeFilter uboostTypeFilter() {
        String annotation = UBoost.class.getCanonicalName();
        return new TypeFilter() {
            /**
             * 如果类或者方法上有{@link UBoost}注解则匹配成功
             * @param metadataReader
             * @param metadataReaderFactory
             * @return
             * @throws IOException
             */
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                return annotationMetadata.hasAnnotation(annotation) || annotationMetadata.hasAnnotatedMethods(annotation);
            }
        };
    }


    private void registerUBoost(BeanDefinitionRegistry registry,
                                AnnotationMetadata annotationMetadata, Map<String, Object> attributes) {
//        String className = annotationMetadata.getClassName();
//        BeanDefinitionBuilder definition = BeanDefinitionBuilder
//                .genericBeanDefinition(FeignClientFactoryBean.class);
//        validate(attributes);
//        definition.addPropertyValue("url", getUrl(attributes));
//        definition.addPropertyValue("path", getPath(attributes));
//        String name = getName(attributes);
//        definition.addPropertyValue("name", name);
//        String contextId = getContextId(attributes);
//        definition.addPropertyValue("contextId", contextId);
//        definition.addPropertyValue("type", className);
//        definition.addPropertyValue("decode404", attributes.get("decode404"));
//        definition.addPropertyValue("fallback", attributes.get("fallback"));
//        definition.addPropertyValue("fallbackFactory", attributes.get("fallbackFactory"));
//        definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
//
//        String alias = contextId + "FeignClient";
//        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();
//
//        boolean primary = (Boolean) attributes.get("primary"); // has a default, won't be
//        // null
//
//        beanDefinition.setPrimary(primary);
//
//        String qualifier = getQualifier(attributes);
//        if (StringUtils.hasText(qualifier)) {
//            alias = qualifier;
//        }
//
//        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className,
//                new String[] { alias });
//        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }
}
