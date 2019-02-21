package com.cqx;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/21
 */
public class Tools {
    private static volatile boolean initialized = false;
    /**
     * 里面有一些工具类 比如messager用来打印信息
     */
    private static ProcessingEnvironment processingEnv;
    private static Elements elements;
    private static Types types;
    /**
     * 文件相关的辅助类
     */
    public static Filer FILER;

    private Tools() {

    }


    public boolean isInitialized() {
        return initialized;
    }

    public static void init(ProcessingEnvironment processingEnv) {
        Tools.processingEnv = processingEnv;
        Tools.elements = processingEnv.getElementUtils();
        Tools.types = processingEnv.getTypeUtils();
        Tools.FILER = processingEnv.getFiler();
        Tools.initialized = true;
    }

    /**
     * 操作Element的工具
     * Element比如说有 package, class, or method, modifier, field, 参数名
     * 相当于编译期间的 抽象语法树的元素 AST
     */
    public static class ELEMENT {

        public static boolean isClass(Element e) {
            return e.getKind().isClass();
        }

        public static String packageName(Element e) {
            return elements.getPackageOf(e).getQualifiedName().toString();
        }


    }

    /**
     * 打印信息的工具
     */
    public static class PRINT {
        public static void INFO(CharSequence msg) {
            INFO(msg, null);
        }

        public static void WARN(CharSequence msg) {
            WARN(msg, null);
        }

        public static void ERROR(CharSequence msg) {
            ERROR(msg, null);
        }

        public static void INFO(CharSequence msg, Element e) {
            PRINT(Diagnostic.Kind.NOTE, msg, e);
        }

        public static void WARN(CharSequence msg, Element e) {
            PRINT(Diagnostic.Kind.NOTE, msg, e);
        }

        public static void ERROR(CharSequence msg, Element e) {
            PRINT(Diagnostic.Kind.NOTE, msg, e);
        }

        public static void PRINT(Diagnostic.Kind kind, CharSequence msg, Element e) {
            processingEnv.getMessager().printMessage(kind, msg, e);
        }
    }


}
