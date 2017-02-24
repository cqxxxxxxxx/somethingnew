###Fork/Join框架

>Work-Stealing Algorithm(工作窃取算法)

**Fork/join框架中执行主任务的线程(Worker Thread/工作者线程)会寻找其他未被执行的任务，然后执行该任务.**   
**Executor Framework中Work Thread则会通过Join操作，等待他创建的子任务的完成，然后继续主任务.**   
**因此Fork/join框架中这一点提升了应用程序的性能.**  
 
####ForkJoinTask 抽象类
    1.内部有多个适配器 里面具体套路看看源码，感觉这种写法挺有趣的。 adapt()方法接受Callable或者Runnable对象，转化为ForkJoinTask类，然后再去执行。
    2.里面有invokeAll()方法，这个是跟执行器框架(ExecutorFramework)与Fork/join框架的主要差异之一。还不是特别清楚，需要看一眼源码。

