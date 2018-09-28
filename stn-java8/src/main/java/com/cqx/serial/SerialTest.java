package com.cqx.serial;

import org.junit.Test;

import java.io.*;
import java.lang.annotation.Target;
import java.net.URI;
import java.net.URL;

public class SerialTest {

    @Test
    public void serialTest() throws IOException {
        OldVersion oldVersion = new OldVersion();
        oldVersion.setName("cqx");
        URL url = SerialTest.class.getResource("/");
        FileOutputStream fileOutputStream = new FileOutputStream(url.getPath() + File.separator + "a.ser");
        OutputStream out = new ObjectOutputStream(fileOutputStream);
        ((ObjectOutputStream) out).writeObject(oldVersion);
    }

    @Test
    public void deSerialTest() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/Users/cqx/Documents/projects/somethingnew/stn-java8/src/main/java/com/cqx/serial/a.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        OldVersion oldVersion = (OldVersion) objectInputStream.readObject();

    }
}
