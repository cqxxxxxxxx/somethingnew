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

​	*一个经典的守护线程就是java的垃圾回收器，每一次垃圾回收都是Stop-The-World的机制，就是java应用程序的所有其他线程都被挂起，形成一种全局暂停现象*

​	创建：Thread构造器中调用setDaemon(true)  只有在start()方法调用之前，才能修改线程守护状态

​	运行:  直接调用start()方法， 守护线程只有当同一个应用程序里没有其他线程运行的时候才会运行

##### 2.线程运行时异常(unchecked/runtime Exception)的处理

​	*非运行时异常(Checked Exception)必须在方法体内捕获或者抛出，而run()方法不支持抛出，所以对待非运行时异常则都需要try-catch捕获处理*

​	*运行时异常不必再方法声明中指定，也不需要在方法体中捕获*

​	线程中运行时异常的处理:	实现UncaughtExceptionHandler接口，并且在创建线程对象的时候通过set方法指定运行时异常处理器

##### 3.线程局部变量的使用(ThreadLocal<T>)

​	为了避免数据被多个线程共享而引发，在类内部创建ThreadLocal<T>线程局部变量，该变量会为每一个线程提供互相独立的存储区域。这个东西还是用的比较多的。

##### 4.线程组

​	把线程分组，可以对组内线程当做一个单一的单元，进行统一的管理调用。

​	



## Chapter2 - Basic Thread Synchronization（线程同步基础）



