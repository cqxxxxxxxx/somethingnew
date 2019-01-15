### words
```
instantiate: 实例化
anonymous  [ə'nɒnɪməs] : 匿名的
inefficient  [ɪnɪ'fɪʃ(ə)nt] : adj. 无效率的，效率低的；无能的
violation [ˌvaɪəˈleʃən] : n. 违反，妨碍，侵犯;违犯，违背;[体]违例，犯规;强奸，亵渎，污辱
cumulate  ['kju:mjəˌleɪt] : v.累积
out of the box : 开箱即用的
multi-plexing : 多路复用
```


### 临时记点

##### NioEventLoopGroup NioEventLoop源码线
```
a. NioEventLoopGroup 里面维护一个 NioEventLoop的数组
b. 每个NioEventLoop都是一个ExecutorService的实现类，也继承SingleThreadEventLoop类，意思就是是个单个线程的ExecutorService
c. NioEventLoopGroup也实现了ExecutorService接口，所有的submit之类等等操作都是调用next()方法获取NioEventLoop去执行的。
d. 所以NioEventLoopGroup就相当于是一个适配器模式？代理模式？装饰器模式？无所谓了，里面维护一堆NioEventLoop，管理并调用他们去执行任务

我知道了 NioEventLoopGroup最终有实现EventExecutorGroup接口 ，下面是他功能的说明
The {@link EventExecutorGroup} is responsible for providing the {@link EventExecutor}'s to use
via its {@link #next()} method. Besides this, it is also responsible for handling their
life-cycle and allows shutting them down in a global fashion.
翻译就是提供EventExecutor给外部使用，通过next()方法； 管理内部EventExecutor的生命周期。
```

1. SelectorProvider SPI机制
```$xslt
Service-provider class for selectors and selectable channels.

All of the methods in this class are safe for use by multiple concurrent threads.  因为里面方法很多加了synchronized

```

2. SelectStrategy  SelectStrategyFactory  
```$xslt
策略模式、工厂模式
```

3. NettyRuntime  
```
A utility class for wrapping calls to {@link Runtime}. 
Runtime.getRuntime().availableProcessors() 获取cpu的数量
```

4. SingleThreadEventLoop
```$xslt
Abstract base class for {@link EventLoop}s that execute all its submitted tasks in a single thread.
单个线程的executor， 里面可以维护一个task队列。可以指定队列长度
```

5. SystemPropertyUtil
```$xslt
A collection of utility methods to retrieve and parse the values of the Java system properties.

里面有AccessController、PrivilegedAction
```

6. MultithreadEventExecutorGroup
```$xslt
Multithread这个名字主要是体现在维护了多个EventExecutor，就是多线程。
NioEventLoopGroup的父的父类 最终NioEventLoopGroup的初始化会调用这个类的构造器。 初始化主要干的活就是
1. 初始化EventExecutor数组
2. 初始化Chosser -> 在调用next()方法获取EventExecutor时，其实是通过chooser的next方法来获取的。功能可能就是为了更加合理的获取eventExecutor，下面再讲
3. 给所有EventExecutor注册terminate的监听器， 主要用来观察是不是所有维护的EventExecutor全部都终止了
4. 吧EventExecutor复制到一个只读的set中， 使用了Collections.unmodifiableSet方法

    /**
     * Create a new instance.
     *
     * @param nThreads          the number of threads that will be used by this instance.
     * @param executor          the Executor to use, or {@code null} if the default should be used.
     * @param chooserFactory    the {@link EventExecutorChooserFactory} to use.
     * @param args              arguments which will passed to each {@link #newChild(Executor, Object...)} call
     */
    protected MultithreadEventExecutorGroup(int nThreads, Executor executor,
                                            EventExecutorChooserFactory chooserFactory, Object... args) {
        //具体的代码不贴了，具体可以自己看
    }
```


#### 书上的服务端创建线


1. AbstractBootstrap.channel(Class channelClass)
```$xslt
a. public class ReflectiveChannelFactory<T extends Channel> implements ChannelFactory<T> {}
通过反射来创建channel的工厂类， 由于netty只有在启动的时候才会创建channel，所以不会影响性能
```

2. AbstractBootstrap.option(ChannelOption.SO_BACKLOG, 128)
```$xslt
backlog设置指定内核为此socket排队的最大链接个数。对于给定的监听socket，内核需要维护两个队列：未链接队列和已链接队列
未链接队列：tcp3次握手没完成的
已链接队列：tcp3次握手完成的
backlog设置的是2个队列的总和
```

3. AbstractBootstrap.handler(ChannelHandler handler) AbstractBootstrap.childHandler(ChannelHandler handler)
```$xslt
handler 添加的是NioServerSocketChannel对应ChannelPipeline的handler
childHandler 添加的是客户端新接入的连接SocketChannel对应ChannelPipeline的handler
```

4. AbstractBootstrap.doBind(SocketAddress localAddress)
```$xslt
绑定端口，启动服务
initAndRegister() 
b. initAndRegister方法里面分为两步 
```

5. AbstractBootstrap.initAndRegister()
```$xslt
初始化NioServerSocketChannel并注册到reactor线程上的多路复用器上，轮询客户端连接事件。
里面的步骤
a. channel = channelFactory.newChannel(); //通过工厂类初始化一个NioServerSocketChannel
b. init(channel); //子类实现之， 0.属性初始化 1.添加AbstractBootstrap的handler到pipeline中 2.将ServerBootstrapAcceptor添加到pipeline中
//////======打断一下。 看那个代码ServerBootstrapAcceptor好像是用来初始化客户端连接的channel的？ 我不懂
c. ChannelFuture regFuture = config().group().register(channel); //注册NioServerChannel到Reactor线程的多路复用器上，轮询监听客户端链接事件
d. 根据regFuture的cause()看下是否注册成功，失败则关闭channel
```

6. EventLoopGroup.register(channel) 针对5中的c点
```$xslt
具体执行的时候的是MultithreadEventLoopGroup的register方法,具体实现类是NioEventLoopGroup，里面具体的流程是
a. next()方法获取一个EventExcutor(实现类是EventLoop)
b. SingleThreadEventLoop.register(Channel channel)，把NioServerSocketChannel注册到Reactor线程的多路复用器上
/////====== register内部的逻辑
c. AbstractChannel.AbstractUnsafe.register(EventLoop eventLoop, final ChannelPromise promise) 通过这个Unsafe类来注册,里面具体调用了Unsafe类中register0()来注册
d. AbstractNioChannel.doRegister() 吧NioServerSocketChannel注册到NioEventLoop的Selector（Selector是通过SelectorProvider获取的）上
e. 如果doRegister() 成功则执行safeSetSuccess(ChannelPromise promise), 调用其中的trySuccess方法，里面会notifyListeners()通知所有注册的监听器
notifyListeners会通知在AbstractBootstrap.doBind()中注册的监听器，里面监听了operationComplete()操作，具体作用是另外添加一个监听器监听此个channel的每个operationComplete的动作，如果是执行失败，则close这个channel
e. pipeline.fireChannelRegistered(); 注册成功后触发ChannelRegistered事件，通知一系列handler
f. NioServerSocketChannel.isActive() 判断是否监听端口成功
g. 监听成功则pipeline.fireChannelActive();  端口监听注册成功则开始监听ACCEPT
h. 修改interestOps为OP_ACCEPT，开始监听客户端的连接

```

7. 

