### Java7并发编程实战手册 —— 总结

简单介绍该书各个章节的内容



#### 0.单词

```java
parallelism  n.并行
concurrent adj. 同时发生的，并发的  n.同时发生的事件
priority adj. 优先级
```





### Chapter1 - ThreadManagement（线程管理）

> 基础部分

##### 1.线程创建和运行

- extends Thread  & override run()
- implements Runnable & override run() & new Thread(Runnable runnbale)

##### 2.基本信息

- ID - 线程标识符	
- Name - 线程名	
- Priority - 优先级	
- Status - 线程状态 (new、runnable、blocked、waitting、time waitting、terminated)

##### 3.线程中断与控制中断

​	中断：调用Thread的interrupt()方法中断其运行，调用isInterrupted()检查是否被打断。

​	控制：抛出和捕获InterruptedException 来处理

##### 4.线程的休眠与等待终止

​	休眠：sleep() 与 TimeUnit 枚举类 控制休眠  *ps.休眠不会释放锁*

​	等待终止：join()  *当A线程对象的join()方法被B调用时，调用它的B线程将被挂起，直到A结束*



	>plus

##### 1.守护(Daemon)线程的创建与运行











