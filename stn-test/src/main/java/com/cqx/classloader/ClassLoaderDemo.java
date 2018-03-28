package com.cqx.classloader;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by BG307435 on 2017/12/7.
 */
public class ClassLoaderDemo {
    private ClassLoader classLoader = LoadDemo.class.getClassLoader();

    @Test
    public void loadTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String classpath = classLoader.getResource("").toString();
        PathClassLoader pathClassLoader = new PathClassLoader(classpath);
        Class clazz = pathClassLoader.loadClass("com.cqx.classloader.LoadDemo.class");
        LoadDemo loadDemo = new LoadDemo();
        loadDemo.printClassLoader();
        LoadDemo loadDemo1 = (LoadDemo) clazz.newInstance();
        loadDemo1.printClassLoader();
    }

    /**
     * 加载特定路径下的class
     */
    private class PathClassLoader extends ClassLoader {
        private String classPath;
        private String packageName = "com.cqx.classloader";

        public PathClassLoader(String classPath) {
            this.classPath = classPath;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if (name.startsWith(packageName)) {
                byte[] classData = getData(name);
                if (classData == null) {
                    throw new ClassNotFoundException();
                }
                return defineClass(name, classData, 0, classData.length);
            }
            return super.findClass(name);
        }

        private byte[] getData(String className) {
//            String path = classPath + File.separator + className.replace(".", File.separator);
            String path = "E:/projects/somethingnew/stn-test/target/classes/com/cqx/classloader/LoadDemo.class";
            try {
                InputStream in = new FileInputStream(path);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[2048];
                int num = 0;
                while ((num = in.read(buffer)) != -1) {
                    out.write(buffer, 0, num);
                }
                return out.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
