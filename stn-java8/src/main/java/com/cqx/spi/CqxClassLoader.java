package com.cqx.spi;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.HashSet;
import java.util.Set;

public class CqxClassLoader extends ClassLoader {
    private static final Set<String> SPI_CLASSES = new HashSet<>();

    static {
        SPI_CLASSES.add("com.cqx.spi.impl.CatSay");
        SPI_CLASSES.add("com.cqx.spi.impl.DogSay");
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                if (SPI_CLASSES.contains(name)) {
                    c = findClass(name);
                } else {
                    c = super.loadClass(name, false);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    @Override
    public Class findClass(String name) {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        // 这里要读入.class的字节，因此要使用字节流
        String classPath = name.replace(".", "/");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/Users/cqx/Projects/somethingnew/stn-java8/target/classes/" + classPath + ".class");
            FileChannel fc = fis.getChannel();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableByteChannel wbc = Channels.newChannel(baos);
            ByteBuffer by = ByteBuffer.allocate(1024);
            while (true) {
                int i = 0;
                i = fc.read(by);
                if (i == 0 || i == -1)
                    break;
                by.flip();
                wbc.write(by);
                by.clear();
            }
            fis.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

}
