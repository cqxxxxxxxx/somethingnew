package com.cqx.stn.jagent;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.ref.PhantomReference;
import java.security.ProtectionDomain;

public class MyTransformer implements ClassFileTransformer {
    private static final byte[] EMPTY_BYTE_ARRAY = {};
    private static final String TARGET_CLASS = "com.cqx.jagent.examples.Person";
    @Override
    public byte[] transform(ClassLoader loader,
                            String classFile,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        // Lambda has no class file, no need to transform, just return.
        if (classFile == null) return EMPTY_BYTE_ARRAY;
        String className = toClassName(classFile);
        if (!TARGET_CLASS.equals(className)) {
            return null;
        }
        ClassPool pool = ClassPool.getDefault();
        try {
            final CtClass clazz = pool.get(className);
            final CtMethod sayHi = clazz.getDeclaredMethod("sayHi");
            sayHi.insertBefore("{" +
                    "System.out.println(\"我是你爹啊\");" +
                    "}");
            return clazz.toBytecode();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String toClassName(final String classFile) {
        return classFile.replace('/', '.');
    }
}
