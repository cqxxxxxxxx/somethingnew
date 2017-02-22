####Semaphore(信号量)
    一个计数器，用于保护一个货多个共享资源的访问
    acquire() -> 获取信号量，信号量-1，如果信号量小于1，既没有则阻塞
    release() -> 释放信号量, 信号量+1
    BinarySemaphore作用类似Lock Synchronized

####CountDownLatch
    用于等待多个并发事件的完成，然后执行某些任务
    只是同步的辅助类，本身并不能使方法同步，只是他的一些方法是原子性的
    所以线程运行到countDown方法这个点的时候是同步的。
    
####CyclicBarrier
    
    
####Phaser

####Exchanger
    用于两个线程间交换数据，多用于生产者消费者情景中。