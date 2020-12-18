package com.cqx.stn.javassist;

import javassist.*;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
//        modifySuperClassAndWriteFile();
//        frozenAndDeFrost();
        modifyMethodBody();

    }

    private static void modifySuperClassAndWriteFile() throws NotFoundException, CannotCompileException, IOException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.cqx.stn.javassist.Rectangle");
        CtClass ctClass1 = classPool.get("com.cqx.stn.javassist.Point");
        ctClass.setSuperclass(ctClass1);
        ctClass.writeFile("F:\\Projects\\somethingnew\\stn-javassist\\src\\main\\java");
    }

    private static void frozenAndDeFrost() throws NotFoundException, CannotCompileException, IOException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("com.cqx.stn.javassist.Circle");
        ctClass.writeFile("F:\\Projects\\somethingnew\\stn-javassist\\src\\main\\java");
        //frozen了 在writeFile之后,需要调用defrost才能再次修改
        ctClass.defrost();
        ctClass.setSuperclass(classPool.get("com.cqx.stn.javassist.Point"));
        ctClass.writeFile("F:\\Projects\\somethingnew\\stn-javassist\\src\\main\\java\\rewrite");
    }


    /**
     * The default ClassPool returned by a static method ClassPool.getDefault() searches the same path that the underlying JVM (Java virtual machine) has.
     * 默认搜索JVM同个路径下面的包 如果需要其他的需要insertClassPath
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     */
    private static void classPathInsert() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        //1.
        pool.insertClassPath("/usr/local/javalib");

        //2.
        ClassPath cp = new URLClassPath("www.javassist.org", 80, "/java/", "org.javassist.");
        pool.insertClassPath(cp);

        //3.
        byte[] b = new byte[222];
        String name = "Leiming";
        pool.insertClassPath(new ByteArrayClassPath(name, b));
        CtClass cc = pool.get(name);
    }


    /**
     * 只有在toClass之前，Rectangle没有被加载过，修改才会生效，如果在这个方法开头先new了Rectangle触发了类加载，那么这边会报错
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     */
    private static void modifyMethodBody() throws NotFoundException, CannotCompileException, IOException {
//        在toClass之前加载了该类会导致toClass抛出异常  javassist.CannotCompileException: by java.lang.ClassFormatError:
//        Rectangle rectangle = new Rectangle();
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.cqx.stn.javassist.Rectangle");
        CtMethod getIdMethod = ctClass.getDeclaredMethod("getId");
        getIdMethod.insertAfter("{" +
                "System.out.println(\"我是你爹啊\");" +
                "}");
        Class<?> aClass = ctClass.toClass();
        Rectangle rectangle = new Rectangle();
        //打印了我是你爹
        rectangle.getId();
    }
}
