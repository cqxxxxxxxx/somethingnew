package com.cqx.chapter4;

/**
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task" + i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
