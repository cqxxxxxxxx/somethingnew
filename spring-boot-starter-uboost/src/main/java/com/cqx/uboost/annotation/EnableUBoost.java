package com.cqx.uboost.annotation;

import com.cqx.uboost.UBoostRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 抄的{@link org.springframework.cloud.openfeign.EnableFeignClients}
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(UBoostRegistrar.class)
public @interface EnableUBoost {

    /**
     * Alias for the {@link #basePackages()} attribute. Allows for more concise annotation
     * declarations e.g.: {@code @ComponentScan("org.my.pkg")} instead of
     * {@code @ComponentScan(basePackages="org.my.pkg")}.
     *
     * @return the array of 'basePackages'.
     */
    String[] value() default {};

    /**
     * Base packages to scan for annotated components.
     * <p>
     * {@link #value()} is an alias for (and mutually exclusive with) this attribute.
     * <p>
     * Use {@link #basePackageClasses()} for a type-safe alternative to String-based
     * package names.
     *
     * @return the array of 'basePackages'.
     */
    String[] basePackages() default {};

    /**
     * Type-safe alternative to {@link #basePackages()} for specifying the packages to
     * scan for annotated components. The package of each class specified will be scanned.
     * <p>
     * Consider creating a special no-op marker class or interface in each package that
     * serves no purpose other than being referenced by this attribute.
     *
     * @return the array of 'basePackageClasses'.
     */
    Class<?>[] basePackageClasses() default {};


    /**
     * List of classes annotated with @UBoost. If not empty, disables classpath
     * scanning.
     *
     * @return list of UBoost classes
     */
    Class<?>[] targets() default {};

}
