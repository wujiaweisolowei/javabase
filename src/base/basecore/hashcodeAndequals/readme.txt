#介绍下hashcode与equals

1.重写hashcode与equals的场景，一般是在使用到hashtable，hashmap,hashset等数据结构的时候，用来区别对象是否重复

2.为什么equals相等的时候，一定要求hashcode相等。而hashcode相等时，equals不一定相等
  根据规范，首先比较hashcode值，如果hashcode值不一致表示2个对象不相等，如果hashcode值相等，在比较equals是否相等。
  所以重写hashcode，一定也要重写equals。才能保证
  默认的hashcode是指地址值，而equals也是比较地址值

