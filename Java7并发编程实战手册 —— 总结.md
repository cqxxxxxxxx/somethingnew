### Java7并发编程实战手册 —— 总结

简单介绍该书各个章节的内容



#### 0.单词

```java
parallelism  n.并行
concurrent adj. 同时发生的，并发的  n.同时发生的事件
priority adj. 优先级
critical adj. 关键性的,严重的
reentrant adj. 再进入的 n.再进入
```





### Chapter1 - ThreadManagement（线程管理）

> 基础部分

##### 1.线程创建和运行

- extends Thread  & override run()
- implements Runnable & override run() & new Thread(Runnable runnbale)

##### 2.基本信息

- ID - 线程唯一标识符	
- Name - 线程名
- Priority - 优先级  1最低 10最高
- Status - 线程状态 (new、runnable、blocked、waitting、time waitting、terminated)

##### 3.线程中断与控制中断

​	中断：调用Thread的interrupt()方法中断其运行，调用isInterrupted()检查是否被打断。

​	控制：抛出和捕获InterruptedException 来处理

##### 4.线程的休眠与等待终止

​	休眠：sleep() 与 TimeUnit 枚举类 控制休眠  *ps.休眠不会释放锁*

​	等待终止：join()  *当A线程对象的join()方法被B调用时，调用它的B线程将被挂起，直到A结束*



> plus


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

##### 5.工厂类创建线程

​	implements ThreadFactory & override newThread() method	

​	工厂类优点: 1.更容易修改类，或者改变创建对象的方式; 2.可以在工厂类中做些限制，比如限制对象的创建数; 3.在创建对象的时候可以进行一些统计; 4.创建对象的时候进行一些验证等等等等等等等等

​	



## Chapter2 - Basic Thread Synchronization（线程同步基础）

**Critical Section(临界区) 是一个用以访问共享资源的代码块，这个代码块同一时间内只允许一个线程执行。为了实现临界区，java提供了*同步机制*。当一个线程试图访问一个临界区时，它将使用一种同步机制来查看是否已有其他线程进入临界区，如果没有其他线程进入临界区，他就可以进入临界区；如果已有其他线程进入临界区，它就被同步机制挂起，直到进入临界区的线程离开；如果等待进入临界区的线程不止一个，JVM会选择其中一个，其余则继续等待。** 



> Synchronized

##### 1.简介

​	synchronized使用一般有3种，修饰非静态方法，修饰静态方法，用于保护代码块的访问。重点是理解到底把什么当作同步锁。因为锁才是控制线程能否访问同步方法的原因。

​	1.修饰非静态方法

​	锁是生成的某个具体对象，因为普通方法是属于对象的。在同一时间只有一个线程允许访问对象A的同步方法，但是其他线程可以访问另外一个对象B的同步方法，而且其他线程可以访问A的非同步方法。

​	2.修饰静态方法

​	锁是该类的Class对象，因为static方法是属于Class的，跟上面的只是对象不同，其实道理一样的。

​	3.保护代码块

​	需要传入一个对象的引用来把引用所指的对象作为代码块的同步锁。可以是某个对象，可以是this(指代执行方法所属的对象)，也可以是Class对象。

​	4.notice.

​	两个线程可以同时访问一个对象的两个不同的synchronized方法，一个是非静态方法，一个是静态方法，因为锁不同。

​	对象的两个方法里的两个同步代码块如果所传入的锁不同，则一个线程进入一个同步代码块的时候，另一个线程可以进入另外一个同步代码块。

​	synchronized关键字会降低程序性能，尽量少用。临界区的访问应该尽可能短，能用同步代码块缩小临界区就别用同步方法。

​	某个线程进入了同步方法后可以进行递归调用，即重复进入该方法，as long as holding the lock.



##### 2.wait() ，notify() ，notifyAll()

​	在同步代码块里调用wait()方法会让执行该代码块的线程挂起，同时释放控制这个代码块的对象，即释放锁。为了唤醒该线程，必须在这个对象（锁）控制的某个同步代码块中调用notify()或者notifyAll()方法。

​	比如生产者消费者模式里。某个存储类A，有两个同步方法consume(),produce()。消费者线程调用consume()发现库存为空，则调用wait()，挂起。生产者线程调用produce()添加库存并且调用notify()或者notifyAll()来释放锁，然后其他线程则根据priority 获取该锁，在这比如之前那个消费者线程被唤醒了获得了锁，那么他将继续执行接下来的代码。



> Lock

##### 1.简介

- Lock接口的重要的实现类ReentrantLock
- ReadWriteLock的实现类ReentrantReadWriteLock，它里面的内部类ReadLock和WriteLock则是实现了Lock接口
- 跟synchronized相比具有更好的性能，支持更灵活的同步代码块结构(控制的获取和释放不在同一个块中)，提供更多的功能。



##### 2.ReentrantLock(可重入锁)

```
public class A{
	private final Lock lock = new ReentrantLock();
	public void dododo(){
  		lock.lock();	//尝试获取锁，如果其他线程占用了则阻塞
  		//lock.tryLock();	//尝试获取锁，如果被占用了则立即返回false，不会被阻塞，继续执行接下来的未获取锁的逻辑。如果没被占用则获取锁，执行有锁的业务逻辑。
  		dosomeshit.
  		lock.unlock();	//一般放到try catch 中finally块中执行释放锁，一定要释放，否则死锁了我日。
	}
}
```



##### 3.ReentrantReadWriteLock(可重入读写锁)

```
使用读操作锁时可以允许多个线程同时访问。
使用写操作锁时只允许一个线程访问。
public class A{    
	private final ReadWritLock lock = new ReentrantReadWriteLock();    
	public void read(){        
		lock.readLock().lock();	//readLock()返回一个Lock借口的实现类
		do some read..
		lock.readLock().unlock();
    }
    public void write(){        
		lock.writeLock().lock();	
		do some write...
		lock.writeLock().unlock();
    }
}
```

​	

##### 4.修改锁的公平性

​	ReentrantLock 跟 ReentrantReadWriteeLock 类构造器都含有个 boolean 型参数 fair，默认为false，即非公平模式(Non-Fair Mode)。非公平模式下多个线程等待锁，锁会选择其中一个，这个选择是没有约束性的。如果在创建Lock时构造器传入true，则修改锁为公平模式，这个时候锁会选择一个等待时间最长的线程来获取锁。 

​	这两种模式只影响lock()和unlock()方法。因为tryLock()就算没有获取锁也不会将线程至于休眠状态。

##### 5.锁中条件的使用

​	一个锁可以关联一个或多个条件，这些条件通过Condition接口声明。

```
public class A{
	private final Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
}
```





### Chapter3 - Thread Synchronization Utilities（线程同步辅助类）