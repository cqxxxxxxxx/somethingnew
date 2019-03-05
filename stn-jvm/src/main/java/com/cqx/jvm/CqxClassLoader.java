package com.cqx.jvm;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/4
 */
public class CqxClassLoader extends ClassLoader {

    public CqxClassLoader() {

    }


    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            if (name.startsWith("java.lang")) {
                return super.getParent().loadClass(name);
            }
            return findClass(name);
        }
    }


    /**
     * Finds the class with the specified <a href="#binary-name">binary name</a>.
     * This method should be overridden by class loader implementations that
     * follow the delegation model for loading classes, and will be invoked by
     * the {@link #loadClass loadClass} method after checking the
     * parent class loader for the requested class.
     *
     * @param name The <a href="#binary-name">binary name</a> of the class
     * @return The resulting {@code Class} object
     * @throws ClassNotFoundException If the class could not be found
     * @implSpec The default implementation throws {@code ClassNotFoundException}.
     * @since 1.2
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = readFile(name);
        Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
        return c;
    }

    private byte[] readFile(String file) {
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/Users/cqx/Projects/somethingnew/stn-jvm/src/main/java/com/cqx/jvm/NameO.class");
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
