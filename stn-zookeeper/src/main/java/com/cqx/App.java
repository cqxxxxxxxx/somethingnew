package com.cqx;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 创建一个与服务器的连接
        ZooKeeper zk = new ZooKeeper("47.92.6.210:8080", 60000, new Watcher() {
//        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 60000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.out.println("EVENT:" + event.getType());
            }
        });

        // 查看根节点
        System.out.println("ls / => " + zk.getChildren("/", true));

        // 创建一个目录节点
        if (zk.exists("/node", true) == null) {
            zk.create("/node", "conan".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("create /node conan");
            // 查看/node节点数据
            System.out.println("get /node => " + new String(zk.getData("/node", false, null)));
            // 查看根节点
            System.out.println("ls / => " + zk.getChildren("/", true));
        }

        // 创建一个子目录节点
        if (zk.exists("/node/sub1", true) == null) {
            zk.create("/node/sub1", "sub1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("create /node/sub1 sub1");
            // 查看node节点
            System.out.println("ls /node => " + zk.getChildren("/node", true));
        }

        // 修改节点数据
        if (zk.exists("/node", true) != null) {
            zk.setData("/node", "changed".getBytes(), -1);
            // 查看/node节点数据
            System.out.println("get /node => " + new String(zk.getData("/node", false, null)));

            //第二次修改没有触发watcher，因为是一次性触发器。
            zk.setData("/node", "changed_twice".getBytes(), -1);
            // 查看/node节点数据
            System.out.println("get /node => " + new String(zk.getData("/node", false, null)));
        }

        // 删除节点
        if (zk.exists("/node/sub1", true) != null) {
            zk.delete("/node/sub1", -1);
            zk.delete("/node", -1);
            // 查看根节点
            System.out.println("ls / => " + zk.getChildren("/", true));
        }

        // 关闭连接
        zk.close();
    }
}
