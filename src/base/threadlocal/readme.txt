1.java中的引用类型有哪几种

强,软，弱，虚

2.每种引用类型的特点是什么
1.强引用：只要当前对象有引用存在，宁可oom也不会回收该对象
2.软引用：空间不够就回收            图片缓存
3.弱引用：只有gc发现就回收          threadlocal防止内存泄露
4.虚引用：管理堆外内存


3.threadlocal应用在哪些地方
3.1   spring中的事务管理transaction 对connection的管理
3.2   mybatis分页插件中的应用

4.threadlocal内存泄露的处理
4.1  entry的key是tl的弱引用，当tl对象没有外部引用时, gc会回收 ；如果key是强引用，那边gc不会回收，会造成内存泄露
4.2  当entry的key被回收了， 就会出现key 为 null, 而value永远取不到的情况，也会造成内存泄露。set,get 方法会清除key 为null的数据，但是长时间
不调用set get方法就会出现问题，所以还是手动删除一下
4.3  当使用线程池的时候，线程是会被回收的，那边保存的key,value值，对于下一个任务来说还是能用，但是此时并不满足需求，
所以也是需要手动删除一下，以免造成业务逻辑错误。

4.4 jdk建议threadlocal 设置成static变量，使其生命周期更长，一直存在强引用，这样就不会被回收。


5.threadlocal hash冲突
多个tl对象时，threadlocalmap难免会遇到hash冲突，则会寻找相邻位置的tab[i]  nextIndex(i, len)
初始容量是16 不够会扩容。
