package com.cqx.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by BG307435 on 2018/3/14.
 */
public class IODemo {

    private InputStream in;
    private OutputStream out;
    private Reader reader;
    private Writer writer;
    private InputStreamReader inputStreamReader;
    private OutputStreamWriter outputStreamWriter;

    @Test
    public void byteInOut() throws IOException {
        File file = new File("D://byteInOut.txt");
        OutputStream out = new FileOutputStream(file);
        byte[] bytes = "我啊ABCDE".getBytes();
        out.write(bytes);
        out.close();

        InputStream in = new FileInputStream(file);
        int i = in.available();
        byte[] result = new byte[i];
        in.read(result);
        System.out.println(new String(result));
    }

    @Test
    public void byteInOut1() throws IOException {
        OutputStream out = new ByteArrayOutputStream();
        byte[] bytes = "我啊ABCDE".getBytes();
        out.write(bytes);
        out.close();

        byte[] buffer = new byte[10];
        InputStream in = new ByteArrayInputStream(bytes);

        int ch;
        while ((ch = in.read()) != -1) {
            System.out.print((char) ch);
        }
    }

    @Test
    public void fileReaderWriter() throws IOException {
        File file = new File("D://byteInOut.txt");
        FileWriter fw = new FileWriter(file, false);
        fw.write("aello world!");
        fw.write("hahaha");

//      fw.flush(); //进行刷新，将字符写到目的地中。
        fw.close(); //关闭流，关闭资源。在关闭前会调用flush方法 刷新缓冲区。关闭后在写的话，会抛IOException


        FileReader fr = new FileReader(file);
        int ch1 = fr.read();
        System.out.print((char) ch1);

        int ch;
        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
        }
        fr.close();
    }


}
