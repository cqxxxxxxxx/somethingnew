###Executor

####线程池类型
    Exceutors.newCachedThreadPool() -> 缓存线程池， 里面线程数量随着任务增加而增加
    Exceutors.newFixedThreadPool(5) -> 线程池大小固定为5， 任务太多会阻塞，直到线程池有空闲的线程
    Exceutors.newSingleThreadExecutor() -> 单个线程的执行器
    
    ThreadPoolExecutor.invokeAny() -> 接收任务列表并运行，返回第一个完成任务且没抛出异常的执行结果。
    ThreadPoolExecutor.invokeAll() -> 接收任务列表并运行，返回所有传入的任务的执行结果。
    
####ScheduledThreadPoolExecutor 
    new ScheduledThreadPoolExecutor(int i) -> i是线程池内线程数
    schedule(task, waitTime, TimeUnit.SECONDS) -> 三个参数 1.是执行的任务可以是Callable或者Runnbale接口，2.等待的时间 3.时间的单位，TimeUnit的常量
    Future对象的cancel()方法可以取消任务的执行
    
####CompletionService
    发送任务给执行器，然后在另一个对象中处理结果，分离了任务的启动与结果处理
    
####RejectedExecutionHandler接口
    实现该接口，用于处理在执行器中被拒接的任务，比如调用了shutdown()后发送任务给执行器，这时候该任务就被拒绝，如果该executor有设置拒绝的任务的处理程序，就调用该程序，没有就抛出异常。