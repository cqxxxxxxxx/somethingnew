package com.cqx.stn.jagent;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyTransformer implements ClassFileTransformer {
    private static final byte[] EMPTY_BYTE_ARRAY = {};

    @Override
    public byte[] transform(ClassLoader loader,
                            String classFile,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        // Lambda has no class file, no need to transform, just return.
        if (classFile == null) return EMPTY_BYTE_ARRAY;
        System.out.println(classFile);
        ClassPool pool = ClassPool.getDefault();
        try {
            final CtClass clazz = pool.makeClass(new ByteArrayInputStream(classfileBuffer), false);
            final CtMethod[] methods = clazz.getMethods();
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
}
