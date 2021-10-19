1.cas  (compareAndSet 比较与更新）
 unsafe类
 aba  （添加时间戳或者版本号来比较，类似）

 应用  atomic 同步类
2.volatile
保证可见性，不能保证原子性

3.aqs


4.锁
4.1公平锁、非公平锁
公平锁：线程在争取锁的时候，如果锁被其他线程占用，则会加入到阻塞队列中，根据FIFO原则获取新的资源

非公平锁：加塞。 无需遵循线程排队原则，性能高，吞吐量大。

4.2 可重入锁 （设计一个不可重入锁）
可重入锁 reentranlock 与 synchornize 都是可重入锁    见TestMyReentranLock

4.3 自旋锁 ：线程获取锁的时候，如果锁被占用，线程不会立即阻塞，而是采用循环等待去尝试获取锁，减少线程上下文切换的开销
代码见  TestSpinLock

4.4 读写锁 ：
    读读可行：
    读写互斥：
    写写互斥：
    见TestReetranWriteReadLock

4.5 countdownCount 的应用场景
    见TestCountDownLatch

4.6 cyclicBarrier 的应用场景
    见TestCyclicBarrier

4.7 semaphore 的应用场景  ： 一，用于多个共享资源的互斥使用   二，用于并发线程数的控制
   见TestSemaphore



