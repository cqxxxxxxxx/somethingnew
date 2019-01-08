package com.cqx;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {

    private NameChecker nameChecker;


    /**
     *  重写init方法 获取ProcessingEnvironment 上下文环境
     *
     * @param processingEnv
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (!roundEnv.processingOver()) {
            roundEnv.getRootElements().stream().forEach(nameChecker::checkName);
        }

        return false;
    }


    private static class NameChecker {
        private final ProcessingEnvironment processingEnv;
        private final Messager messager;

        public NameChecker(ProcessingEnvironment processingEnv) {
            this.processingEnv = processingEnv;
            this.messager = processingEnv.getMessager();
        }

        public void checkName(Element element) {

        }


        /**
         * 名称类型检查器实现类。 继承了JDK1.6种新提供的ElementScanner6<br>
         * 以Visitor模式访问抽象语法树中的元素
         */
        private class NameCheckScanner extends ElementScanner8<Void, Void> {

            @Override
            public Void visitType(TypeElement e, Void p) {
                scan(e.getTypeParameters(), p);
                checkCamelCase(e, true);
                super.visitType(e, p);
                return null;
            }

            @Override
            public Void visitExecutable(ExecutableElement e, Void p) {
                if (e.getKind() == ElementKind.METHOD) {
                    Name name = e.getSimpleName();
                    if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                        messager.printMessage(Diagnostic.Kind.WARNING, "一个普通方法" + name + "不应当与类名重复，避免与构造方法产生混淆", e);
                    }
                }
                checkCamelCase(e, false);
                super.visitExecutable(e, p);
                return null;
            }

            @Override
            public Void visitVariable(VariableElement e, Void p) {
                if (e.getKind() == ElementKind.ENUM_CONSTANT
                        || e.getConstantValue() != null
                        || heuristicallyConstant(e)) {
                    checkAllCaps(e);
                } else {
                    checkCamelCase(e, false);
                }
                return null;
            }

            private boolean heuristicallyConstant(VariableElement e) {
                return false;
            }

            private void checkAllCaps(Element e) {

            }
            private void checkCamelCase(Element e, boolean initialCaps) {

            }
        }
    }

}
