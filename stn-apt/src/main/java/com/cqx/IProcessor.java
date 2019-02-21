package com.cqx;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/21
 */
public interface IProcessor {

    void process(TypeElement annotation, Set<? extends Element> annotatedElements);

}
