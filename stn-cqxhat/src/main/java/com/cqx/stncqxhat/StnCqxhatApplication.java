package com.cqx.stncqxhat;

import com.cqx.stncqxhat.constant.ServerConst;
import io.netty.util.internal.SystemPropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class StnCqxhatApplication implements CommandLineRunner {

    @Autowired
    private CqxhatServer cqxhatServer;

    public static void main(String[] args) {
        SpringApplication.run(StnCqxhatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String host = SystemPropertyUtil.get("host", ServerConst.HOST);
        Integer port = SystemPropertyUtil.getInt("port", ServerConst.PORT);
        System.out.println("cqxxxxxx" + host);
        cqxhatServer.start(new InetSocketAddress(host, port));

//      注册jvm的关闭hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                cqxhatServer.close();
            }
        });
    }
}

