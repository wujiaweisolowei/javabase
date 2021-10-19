1.list


2.map



3.set



4.阻塞队列  BlockingQueue   当队列满了，添加阻塞； 当队列没有元素，取阻塞
4.1 ArrayBlockingQueue  由数组结构组成的有界阻塞队列
4.2 LinkedBlockingQueue  由链表结构组成的有界队列（大小默认值是Integer最大值）
4.3 SynchronousQueue   不存储元素的阻塞队列，也即单个元素队列
4.4 LinkedBlockingDeque: 由列表结构构成的双向阻塞队列

方法 add,remove,element 不符合条件报异常
    offer,poll,peek  成功true,失败false
    put,take  失败即阻塞
    offer, poll  (time,timeType) 超时控制