### Java7并发编程实战手册 —— 总结

简单介绍该书各个章节的内容，温故而知新，无敌。


#### 0.单词

```java
parallelism  n.并行
concurrent adj. 同时发生的，并发的  n.同时发生的事件
priority adj. 优先级
critical adj. 关键性的,严重的
reentrant adj. 再进入的 n.再进入
latch　n. 门闩
cyclic adj.周期性的
barrier n.屏障，栅栏
```

<br>

## Chapter1 - ThreadManagement（线程管理）

> 基础部分

##### 1.线程创建和运行

- extends Thread  & override run()
- implements Runnable & override run() & new Thread(Runnable runnbale)

##### 2.基本信息

- ID - 线程唯一标识符	
- Name - 线程名
- Priority - 优先级  1最低 10最高(不同操作系统线程优先级划分不一样。。。。当作没看到这句话)
- Status - 线程状态 (new、runnable、blocked、waitting、time waitting、terminated)

##### 3.线程中断与控制中断

​	中断：调用Thread的interrupt()方法中断其运行，调用isInterrupted()检查是否被打断。

​	控制：抛出和捕获InterruptedException 来处理

##### 4.线程的休眠与等待终止

​	休眠：sleep() 与 TimeUnit 枚举类 控制休眠  *ps.休眠不会释放锁，任然会占用CPU时间片*

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

<br>
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


<br>
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
 * 获取写锁的时候，读锁是阻塞的，只有释放了写锁，其他线程才能获取读锁。
 * 同理读锁被某些线程占有的时候，写锁是获取不了的，只有读锁被释放，写锁才有机会获取。
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

Condition中await()/signal()/signalAll() 分别代传统的Object的wait()/notify()/notifyAll() 

​	一个锁可以关联一个或多个条件，这些条件通过Condition接口声明。条件的存在目的是允许线程获取锁的时候查看某一个条件是否满足，如果不满足则挂起(await)直到某个线程唤醒(signal/signalAll)它们。比如某个线程获取锁后检查某个条件(conditionAAA)是否满足，不满足则调用**conditionAAA**的await()挂起线程，其他线程某个契机调用**conditionAAA**的signal()/signalAll()时候会唤起这个线程。

**而且Condition能更加精确的控制线程的唤醒**，比如假如多线程读/写同一个缓冲区：当向缓冲区中写入数据之后，唤醒"读线程"；当从缓冲区读出数据之后，唤醒"写线程"； 如果采用Object类中的wait(), notify(), notifyAll()实现该缓冲区，当向缓冲区写入数据之后需要唤醒"读线程"时，不可能通过notify()或notifyAll()明确的指定唤醒"读线程"，而只能通过notifyAll唤醒所有线程(但是notifyAll无法区分唤醒的线程是读线程，还是写线程)。 但是，通过Condition，就能明确的指定唤醒读线程(condition其实是等待队列的一个管理者，condition确保阻塞的对象按顺序被唤醒)  [介绍链接](http://www.cnblogs.com/dolphin0520/p/3920385.html)


```
public class A{
	private final Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
} 
```

<br>
## Chapter3 - Thread Synchronization Utilities（线程同步辅助类）
- Semaphore(信号量): 计数器，保护一个或多个资源的访问。
- CountDownLatch: Java提供的**同步辅助类**,在完成一组在正在其他线程中执行的任务前，它允许线程一直等待。ps.Tomcat执行的时候进行session还是cookie过期策略的时候经常会看见。没仔细看过。。。。。
- CyclicBarrier:**同步辅助类**，它允许多个线程在某个comment point（集合点）处进行互相等待。你等我我等他。。。。
- Phaser: **同步辅助类**， JAVA7的新特性哦哦哦哦。把并发任务分为几个阶段，在下一阶段开始前，当前阶段中所有线程都必须被执行完成。
- Exchanger: **同步辅助类**， 它提供了两个线程间数据交换点。

之所以有些是同步辅助类是因为它们并不能像Sychronized,Lock一样实现critical section(临界区噜),它们只是同步辅助类，用来加强一些功能用的。。。。。。。。。**他们的一些方法比如内部计数器的+1/-1,信号量的获取和释放等等都是原子性的(不然就起不到辅助的作用了)**

好，接下来就让我们开始一个个学习他们到底是什么鬼东西把把把把把把把把。

<br>
> Semaphore 

#### 是一种计数器，用于保护一个或多个共享资源的访问
线程访问一个共享资源，必须先获取信号量。如果信号量内部计数器大于0(意味有可使用的资源)，则-1，然后允许访问该资源。若计数器等于0，则说明当前无可用资源，信号量会把当前线程置于休眠，直至信号量大于0。线程使用完资源时，信号量必须被释放，计数器+1。

Binary Semaphore 作用类似于Lock，Sychronized 用于保护控制唯一资源的访问。

#### 几个方法啊
```
Semaphore semaphore = new Semaphore(1);   //设置只有1个资源 BinarySemaphore-> 只有 0 1   /参数是资源数量
semaphore.acquire() -> 获取信号量，信号量-1，如果信号量小于1，既没有则阻塞
semaphore.release() -> 释放信号量, 信号量+1
---
Semaphore semaphore = new Semaphore(4, true); //构造器第二个参数是设置信号量公平性，true公平mode ，default false
semaphore.tryAcquire() -> 类似tryLock() 不会阻塞，如果获取了资源则返回true，else false
```
#### 适用情况
控制线程对资源的访问量。

<br>
> CountDownLatch

#### 用于等待多个并发事件完成(同步辅助类)
CountDownLatch类用一个整数初始化，表示需要等待的数目。一个线程A需要等待其他线程完成任务后才执行时，可以调用CountDownLatch的await()方法，让该线程进入休眠，其他执行任务的线程在完成各自的操作后调用同一个对象的countDown()方法，内部计数器-1。当计数器为0的时候，CountDownLatch将会唤醒所有调用该对象await()方法而进入休眠的线程。

#### 几个方法
```
 private final CountDownLatch controller = new CountDownLatch(5); //初始化 5个任务
 controller.await(); //调用他的线程进入休眠 等待其他任务完成，内部计数器为0，然后唤醒他。 这个唤醒跟lock的Condition类似，跟notify不一样，会唤醒指定的线程。应该也是有个队列来维护因为该CountDownLatch而进入休眠的线程。
 controller.countDown(); //内部计数器-1，为0的时候进行唤醒 
notice: 
1.CountDownLatch机制不是用来保护共享资源或者临界区的，它是用来同步执行多个任务的一个或者多个线程 要理解这句话哦。 [同步/异步 & 阻塞/非阻塞](http://www.jianshu.com/p/aed6067eeac9)
2.CountDownLacth只用允许进入一次，一旦内部计数器为0，就恢复不了初始状态了，如果需要则必须创建一个新的CountDownLatch对象。
3.await(Long time, TimeUnit unit) -> 这个方法被调用后线程将休眠直到 线程被中断 or 内部计数器为0 or TimeUnit指定的时间过期
```
<br>
>CyclicBarrier

#### 用于使多个线程在某一个集合点上进行同步。
类似CountDownLatch，but 摸斗莫斗子由已。  
CyclicBarrier以一个整型数进行初始化，这个数是需要在某个点上同步的线程数，同时可以传入一个Runnable对象作为参数，当所有线程到达集合点后，runnable对象将会运行(这个特性使CyclicBarrier在并行任务上可以媲美分治编程技术/Divide and Conquer Programming Technigue)。

#### 几个方法
```
CyclicBarrier cb = new CyclicBarrier(int num); //需要同步的线程数
				 = new CyclicBarrier(int num, Runnable runnable); //还可以传入Runnable对象，在指定数量的线程到达集合点后执行该run方法。
cb.await(); //调用他的线程在这里进行休眠，cb的内部计数器-1， 当其他线程都到达后，即内部计数器为0的时候将会被唤醒。
cb.await(long time, TimeUnit unit); //类似CountDownLatch 不必多说

cb.reset(); //重置CyclicBarrier对象(跟CountDownLatch不同，它可以重置，然后重新使用) ,
Broken 破损状态: 
当执行reset()时候，如果有其他线程在await()方法上等待时，这些线程会收到一个BrokenBarrierException的异常，于是CyclicBarrier
就处于了Broken状态了，可以通过isBroken()来判断是否处于破损状态。 ps.可以捕获这个异常，进行一些其他操作如重新执行等等，或者只是打印出来。
```
#### 简单的应用场景：
比如一个二维数组int a[5][10],计算值总和，现将它拆为5个一维数组，然后分别计算和。
1. CyclicBarrier cb = new CyclicBarrier(5, () -> countAll);	//第二个参数为lambda表达式创建的runnable对象，用于在5个线程都到集合点后执行该run方法。
2. 创建个计算一位数组的类ArraySum实现Runnable接口，在构造器中传入CyclicBarrier对象。 在run方法计算好结果后执行 cb.await()方法，进入休眠，等待其他线程计算好结果。
3. 初始化五个ArraySum对象，传入cb对象，传入拆好的一维数组，启动线程，比如某个线程先计算好结果，他就会在cb.await()后把内部计数器-1，并休眠，等待其他线程完成。
4. 
4. 当cb的内部计数器为0的时候即所有线程都到了集合点，cb对象会唤醒所有在该集合点等待的线程，并执行cb初始化时候传入的runnable对象。由该runnable对象来收集整合计算结果。

<br>
>Phaser 

#### 用于执行并发多阶段任务
当我们有并发任务并需要分解为几个阶段执行的时候可以用Phaser，Phaser类的机制是在每一个阶段结束的位置对线程进行同步，当所有线程都完成了这一步，才允许进入下一阶段。  
在Phaser初始化的时候设置任务数量，而且可以动态的增加或者减少任务数。

#### 几个方法
讲道理这个同步辅助类还是有一丁点复杂的，详见github上代码和书中详解。懒得写那么多了。。。。。
```
Phaser phaser = new Phaser(3);  //参与同步的线程3个
phaser.arriveAndAwaitAdvance(); //字面意思，调用这个方法的线程到达并预先进入休眠状态以等待其他线程到达。phaser内部计数器-1
phaser.arriveAndDeregister(); //字面意思，如果某个线程不参与下一阶段则通知phaser该线程已经到达并取消注册，phaser对象在下一阶段将不会等待该线程。这就是动态减少任务数量了。
phaser.isTerminated(); //当phaser对象不存在参与同步的线程中时，返回true，phaser是终止状态，当取消了所有线程的注册时即都调用了上面这个方法时候，也返回true。

----
两个状态：Active / Termination
active： 当phaser存在与参与同步的线程中时候，就是active状态
termination： 当所有参与同步的线程都取消注册的时候，就是终止状态。当phaser终止态时，同步方法arriveAndAwaitAdvance()会立即返回，不会做任何同步操作。

---
Phaser类不必对它的方法进行异常处理，被Phaser类置于休眠状态的线程不会响应中断事件，不会抛出InterruptedException异常(只有一种特例会抛出，是什么呢,,)
```

<br>
>Exchanger

#### 用于并发任务间数据交换
ta允许两个线程之间定义一个Synchronization Point(同步点)，当两个线程都到达同步点的时候，他们交换数据结构，从而可以获得彼此线程中的数据。typically used in producer-consumer mode.but only for the situation which has just one producer and one consumer ,cause Exchanger can only synchronize two threads.

#### 几个方法
```
Exchanger<V> exchanger = new Exchanger();	//泛型类
exchanger.exchange(V data); //同步点，会抛出InterruptedException  在这里两个线程同步，彼此交换数据， 比如A先到 则A休眠，在这里等待B的到达，然后彼此交换数据---这边应该也有个唤醒休眠线程的机制 跟上面的一些同步辅助类一样 
exchanger.exchange(V data, long time, TimeUnit unit); //线程将休眠至被中断 | 其他线程到达 | 指定时间到了
```

<br>
阿西吧，第三章终于写好了。。。除了Phaser内容有点多外，其他理解上都没上面问题。。。。当然了，还都没看过源码，比如内部计数器的+ -的原子操作是怎么实现的？exchanger到底是怎么做的？and so on...remain to be solved.   =.=

<br>
## Chapter4 - Executor（线程执行器）
**讲道理 这一章的讲的对象都差不多，都是线程池对象，只是作用不一样，应用场景不同而已。 唯一需要注意的就是Callable 跟 Future这两个接口。**  
<br>
为什么要引入这么一个鬼东西呢呢呢呢呢呢呢。在开发大量的并发任务的时候会遇到如下问题:  
 1. 必须实现所有Thread对象相关的代码，创建，结束，结果处理。  
 2. 必须为每一个任务创建一个thread对象，线程如果太多会导致计算机负荷过大。 
 
因此JDK1.5就提供了一套机制Executor Framework(执行器框架), 围绕着Executor接口和它的子接口ExecutorService，以及实现这两个接口的ThreadPoolExeutor类展开。  
**这套机制将任务的创建和执行进行了分离，只要把Runnable或者Callable对象发给执行器，执行器负责运行这些任务所需要的的线程，包括创建，管理和线程的结束。**  
**One another advance of this Executor Framework is Callable interface， it's similar as Runnable ， but enhanced in two way。**
1. call()方法，跟run()类似，只是可以返回结果。
2. 当发送一个Callable对象给执行器的时候，将获得一个实现了Future接口的对象。该对象可用于控制Callable对象的状态和结果。

#### 一些基础的东西
```
- ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool(); //通过Executors工厂类来创建ThreadPoolExecutor对象,这里是个缓存线程池  还可以通过构造器来创建
- executor.execute(Runnbale or Callable); //开始执行runnable或者Callable对象
- executor.getPoolSize(); //返回执行器线程池中实际线程数
- executor.getActiveCount(); //返回正在执行的线程数目
- executor.getCompletedCount(); //返回执行器已经完成的任务数
- executor.shutDown(); //当里面所有任务都执行完成后，执行器将关闭停止运行，如果不显示的停止，它将会一直等待新任务的到来。如果在执行器shutDown()后再添加任务会抛出RejectedExecutionException异常 
```
#### Callable & Future

**一些基础使用:**
```
public class Task implements Callable<String>{
	private String info;
    public Task(String str){
    	this.info = str;
    }
	public String call() throws Exception{
    	TimeUnit.MILLISECONDS.sleep(2000); //休眠2000毫秒 
        return this.info;
    }
}

public class demo{
	public static void main(String[] args){
        List<Future<String>> futureList = new ArrayList<>();
    	ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(3); //固定线程池大小为3的执行器
    	for(int i = 0; i < 3; i ++){
        	Task task = new Task("第" + i + "个");
            Future<String> result = executor.execute(task);		//把返回的Future对象的引用给result
            futureList.add(result);	//把result放到list中
        }
		do {
        	调用future.isDone(); 输出任务是否完成的相关信息
            try {
            	TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedExceptione) {
            	e.printStackTrace();
            }
        } while(executor.getCompletedCount() < futureList.size()) //如果有任务没完成则一直循环这个
		//从上面循环跳出后说明所有任务都完成了
        循环3次{
        	输出-> future.get();  //需要捕获InterruptedException和ExcecutionException
        }
        executor.shutDown(); //最后关闭执行器
    }
}
ps. Future还有个get方法 -> get(long timeout, TimeUnit unit); //如果超出了这个时间就返回null 
```
*notice: 在调用Future的get()时候，如果该任务没执行完成，那么调用这个方法的线程将在这个方法这里阻塞直到任务完成，如果任务中断则抛出InterruptedExceptione, 如果call()方法抛出了异常，将会抛出ExecutionException*  
<br>

**额外的一些东西**
```
ExecutorService es = Executors.newCachedThreadPool();
List<Callable> taskList = new ArrayList<>();
1. Future result = es.invokeAny(taskList) -> 接收任务列表并运行，返回第一个完成任务且没抛出异常的执行结果。 
2. List<Future> resutlList = es.invokeAll(taskList) -> 接收任务列表并运行，返回所有传入的任务的执行结果。
关于invokeAll()的一个点是：future对象仅用于获取任务的结果，在返回的future对象上调用isDone()则都会返回true，因为当invokeAll()方法执行结束的时候，执行器里的任务也都完成了，返回的Future对象已经是结果了。
---
future.cancel(true); //可以取消已发给执行器的任务,即使任务正在执行也会被取消，如果是false则如果任务正在执行则不会取消任务。
FutureTask类可以在任务执行结束(无论是完成，取消还是没有正常结束)的时候执行里面的done()方法，我们可以继承这个类，并重写done()方法来进行一些处理。比如发个邮件，释放一些资源等。。。
``` 


#### 线程池类型
    Exceutors.newCachedThreadPool() -> 缓存线程池， 里面线程数量随着任务增加而增加
    Exceutors.newFixedThreadPool(5) -> 线程池大小固定为5， 任务太多会阻塞，直到线程池有空闲的线程
    Exceutors.newSingleThreadExecutor() -> 单个线程的执行器

#### ScheduledThreadPoolExecutor 
    new ScheduledThreadPoolExecutor(int i) -> i是线程池内线程数
    schedule(task, waitTime, TimeUnit.SECONDS) -> 三个参数 1.是执行的任务可以是Callable或者Runnbale接口，2.等待的时间 3.时间的单位，TimeUnit的常量
    Future对象的cancel()方法可以取消任务的执行
    
#### CompletionService
    发送任务给执行器，然后在另一个对象中处理结果，分离了任务的启动与结果处理
    
#### RejectedExecutionHandler接口
    实现该接口，用于处理在执行器中被拒接的任务，比如调用了shutdown()后发送任务给执行器，这时候该任务就被拒绝，如果该executor有设置拒绝的任务的处理程序，就调用该程序，没有就抛出异常。
    
<br>
## Chapter3 -  Fork/Join Framework
**这是JAVA7中新提供的ExecutorService接口的另一个实现，专门用来解决特殊类型的问题(Divide and Conquer Technique/分治技术)。**  
分治技术即将大任务拆分为小任务，基本处理如下:
在一个任务中，先检查问题的大小，如果大于一个设定的值，则拆分为可以通过Fork/Join框架执行的小任务。如果问题的大小比设定值小则直接在这个任务里解决掉这个问题，然后根据需要返回结果。
- Fork(分解)操作: 把任务拆分为更小的任务，在框架内执行这些任务。
- Join(合并)操作: 主任务等待其创建的多个子任务的完成，合并结果。

<br>
>Work-Stealing Algorithm(工作窃取算法)

Fork/join框架中执行主任务的线程(Worker Thread/工作者线程)会寻找其他未被执行的任务，然后执行该任务.   
Executor Framework中Work Thread则会通过Join操作，等待他创建的子任务的完成，而这时候主任务线程则处于休眠状态，浪费了。当所有子任务完成好后主任务线程被唤醒，然后继续执行.   
因此Fork/join框架中这一点提升了应用程序的性能.
 
<br>
#### Fork/join的组成核心介绍
1. ForkJoinPool: 它实现了ExecutorService接口和工作窃取算法，用于管理工作者线程，并提供任务的状态信息，以及任务的执行信息。
2. ForkJoinTask<T> implements Future<V>, Serializable: 它是一个在ForkJoinPool中执行任务的基类，类似Runnbale/Callable
3. RecursiveAction extend ForkJoinTask<Void>: 是个抽象类，继承了ForkJoinTask类，用于任务没有返回值的场景
4. RecursiveTask extend ForkJoinTask<T>: 是个抽象类，继承了ForkJoinTask类，实现了Future接口，用于任务有返回结果的场景


#### 基础使用流程
JAVA API文档中推荐的结构
```
1. 无返回结果的 RecursiveAction
if (problem size > default size) {
	tasks = divide(task);
    execute(tasks);
} else {
	resolve problem using another algorithm;
}

2. 有返回结果需要合并任务结果的 RecursiveTask 
if (problem size > default size) {
	tasks = divide(task);
    execute(tasks);
    groupResults();	//合并结果
    return result;
} else {
	resolve problem using another algorithm;
    return result;
}
```
下面仅仅介绍部分的流程， 完整的Demo太长了，具体可见书上或者github里的
```
1. 同步执行任务的方式(采用work-steal-algorithm来提升性能)
ForkJoinPool pool = new ForkJoinPool(); //无参构造器，默认创建线程数=计算机CPU数，对象创建好后，那些线程也就都创建好了，在线程池中等待任务的到达，然后开始执行。
ForkJoinPool创建的线程是 ForkJoinWorkerThread，它里面维护了ForkJoinPool对象和work-steal机制对象
pool.execute(ForkJoinTask forkJoinTask); //execute方法是异步调用的，会立刻返回。
forkJoinTask.compute(); //重写这个方法，在这里面进行任务大小判断，并拆分任务或者解决问题。
forkJoinTask.invokeAll(task1, task2); //它的invoke系列方法是同步的，需要等到传进来的任务都完成才会返回。
如果需要获取处理结果合并结果集 -> recursiveTask.get(); 获取任务结果(invoke调用是同步的，所有可以获取到处理结果)，然后处理并返回。
pool.shutDown(); //在最后关闭线程池
ps. invoke方法实际还是通过ForkJoinPool来执行任务的，即获取当前线程ForkJoinWorkerThread，然后获取pool来执行任务。

---

2. 异步执行任务的方式（因为是异步的，主任务线程会继续执行下去，所以不能使用work-steal-algorithm）
forkJoinTask.fork(); //把任务发送到线程池，并立刻返回，如果线程池有空闲的WorkerThread或者可以创建个新的线程，那么就立刻开始执行这个任务。
一旦主任务完成了自己的任务后就调用join()方法或者Future.get()来等待任务的执行结束，然后通过Future获取compute()方法的返回结果来合并处理结果。

```

#### ForkJoinTask 抽象类
随便看看  
1.内部有多个适配器 里面具体套路看看源码，感觉这种写法挺有趣的。 adapt()方法接受Callable或者Runnable对象，转化为ForkJoinTask类，然后再去执行。   
2.里面有invokeAll()方法，这个是跟执行器框架(ExecutorFramework)与Fork/join框架的主要差异之一。执行器框架是一开始把所有任务都扔进去，而ForkJoinPool的任务控制则是在线程池内进行的，比如任务的拆分执行。

#### 抛出异常
    ForkJoinTask中不能抛出Checked Exception，可以抛出Unchecked Exception(运行时异常)，不过即使抛出异常程序也不会停止。
    ForkJoinTask 的 isCompletedAbnormally() 方法检查主任务或者他的子任务是否抛出异常
    getException()获取异常信息

#### 取消任务
    ForkJoinPool 没有取消任务的方法。
    ForkJoinTask 提供cancel()方法，可以取消一个仍未被执行的任务。如果传参数true则任务正在运行也会被取消。

<br>
## Chapter6 - 并发集合

在并发编程的时候，大多数集合类不能直接应用，比如ArrayList，HashMap, and so on. 因此Java有提供专门用于并发程序中使用的集合类型，分为两类。
1. Blocking Collection  如果线程调用该类的添加或者删除等方法的时候不能立刻执行，调用该方法的线程会被阻塞，直到可以成功执行。 
2. Non-Blocking Collection 同上，如果不能立刻执行，则会立即返回Null或者抛出异常，调用该方法的线程不会被阻塞。

#### 简单列几个集合类
```
- ConcurrentLinkedDeque: 非阻塞式队列的实现类　// deque 双向队列
- LinkedBlockingDeque: 阻塞式队列的实现类
- LinkedTransferQueue: 用于数据生成或消费的阻塞式队列
- PriorityBlockingQueue: 按优先级排列元素的阻塞式队列
- DelayQueue: 带有延迟列表元素的阻塞队列
- ConcurrentSkipListMap: 非阻塞式可遍历映射
- ThreadLocalRandom: 随机数字
- AtomicLong AtomicIntegerArray: 

☆以后有需要用到再细究把啊。☆

```
<br>
#### 比较重要的原子变量(Atomic Variable)
Java中每个变量的每个操作都将被转换为机器可以理解的指令来执行，比如给变量赋值，java代码只需要一个指令(=),但是被转换到JVM中的时候就拆分为了好多个不同指令，这些指令按顺序执行才能完成这么一个赋值的操作。因此如果在多线程情况下，就有可能会发现数据不一直的问题。　　
#### 实现机制
CAS(Compare-and-Swap)  
锁的分类：独占锁（悲观锁），乐观锁  
独占锁：synchronized就是一种独占锁，它会导致所有需要此锁的线程挂起，等待锁的释放。  
乐观锁：每次不加锁去完成操作，如果因为冲突失败就重试，直到成功。  //同数据库的乐观锁　加个version字段　　　

CAS的机制就相当于这种（非阻塞算法），CAS是由CPU硬件实现，所以执行相当快.CAS有三个操作参数：内存地址，期望值，要修改的新值，当期望值和内存当中的值进行比较不相等的时候，表示内存中的值已经被别线程改动过，这时候失败返回，当相等的时候，将内存中的值改为新的值，并返回成功。

