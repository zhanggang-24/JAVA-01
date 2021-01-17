## 基本垃圾回收算法对比
| 算法 | 过程 | 优点 |缺点|
| :----| :---- | :---- |:---- |
| 标记-清除&nbsp; &nbsp; &nbsp; | 1.标记需要回收的对象</br>2.清理要回收的对象 | 实现简单 |1.执行效率不稳定，java堆中大量对象需要回收的时候，执行效率低</br>2.清理后内存空间会存在碎片|
| 标记-复制&nbsp; &nbsp; &nbsp;| 1.把内存分成两块，每次只使用一块</br> 2.将正在使用的内存中的存活对象复制到未使用的内存中去，然后清除掉正在使用的内存中的所有对象</br>3.交换两个内存的角色，等待下次回收| 1.实现简单，运行高效</br>2.无空间碎片问题| 1.空间利用率低（最高50%）</br>2.在对象存活率高的时候，要进行频繁的复制操作，效率低 |
| 标记-整理&nbsp; &nbsp; &nbsp;| 1.标记需要回收的对象</br>2.把所有的存过对象压缩到内存的一端</br>3.清理掉边界外的所有空间 | 无内存碎片 | 整理增加时间开销，操作全程必须STW |


## 各个垃圾回收器对比
| GC | 使用算法 | 工作区  | 线程  | 串行/并行/并发  |适用场合|
| :----| :---- | :---- |:---- |:---- |:---- |
| Serial | 标记-复制| 新生代 |单|串行|响应速度优先，单CPU;Client模式下|
| ParNew| 标记-复制|新生代|多|并行|响应速度优先，多CPU环境时在Server模式下与CMS配合|
| Parallel Scavenge| 标记-复制 |新生代|多|并行|吞吐量优先，在后台运算而不需要太多交互的任务|
| Serial Old|标记-整理| 老年代 | 单|串行|响应速度优先，单CPU，Clinet模式，CMS的后备预案|
| Parallel Old|标记-整理| 老年代 | 多|并行|吞吐量优先，在后台运算而不需要太多交互的任|
| CMS |标记-清除| 老年代 | 多|并发|响应速度优先，互联网站;B/S系统服务端|
| G1|标记-整理+复制| 新生代&老年代 | 多|并发|响应速度优先，面向服务端应用|

## 常用的GC组合
![](https://github.com/zhanggang-24/JAVA-01/blob/main/Week_02/2020-01-13/resource/%E5%B8%B8%E7%94%A8GC%E7%BB%84%E5%90%88%E5%9B%BE%E8%A7%A3.png)

| GC | 使用算法 | JVM开启参数 |
| :----| :---- | :---- |
| 串行 | 1.年轻代：Serial GC,使用标记-复制算法</br>2.老年代：Serial Old GC，使用标记-整理算法| -XX:+UseSerialGC |
| 并行| 1.年轻代：Parallel Scavenge GC,使用标记-复制算法</br>2.老年代：Parallel Old GC,使用标记-整理算法|以下三组参数等价</br> -XX:+UseParallelGC</br> -XX:+UseParallelOldGC </br> -XX:+UseParallelGC -XX:+UseParallelOldGC|
| CMS| 1.年轻代：ParNew GC，使用标记-复制算法</br>2.老年代：CMS GC,使用标记-清除算法 | -XX:+UseConcMarkSweepGC |
| G1|1.G1虽然也区分年轻代，老年代，但是堆被划分为多个region（每个region，可能一会被定义为Eden区，一会被定义为：Survivor区,一会被定义为Old区），在逻辑上，所有的Eden区和Survivor区合起来就是年轻代，所有的Old区拼在一起那就是老年代 </br>2.G1 整体上来看，使用的是“标记-整理”算法，region使用的是复制算法 | -XX:+UseG1GC | 


## 各GC最大young区计算方式
	默认情况下：堆各部分大小会受到自使用参数的影响（UseAdaptiveSizePolicy），关闭命令：-XX:-UseAdaptiveSizePolicy；
	串行
	并行（Parallel）
		最大young区：默认占最大堆内存的1/3，即：Xmx/3;
	CMS
		最大young区，公式：64M * 并发GC线程数(4) * 13 / 10;
			其中GC线程数：ParallelGCThreads 参数的默认值是：
			CPU核心数 <= 8，则为 ParallelGCThreads=CPU核心数，比如我的那个旧电脑是4
			CPU核心数 > 8，则为 ParallelGCThreads = CPU核心数 * 5/8 + 3 向下取整
			16核的情况下，ParallelGCThreads = 13
			32核的情况下，ParallelGCThreads = 23
			64核的情况下，ParallelGCThreads = 43
			72核的情况下，ParallelGCThreads = 48
	G1
		最大young区默认占整个heap的60% ；


## G1垃圾回收相关梳理：
	垃圾回收模式
		纯年轻代模式垃圾收集
		[GC pause (G1 Evacuation Pause) (young), 0.0092774 secs]-纯年轻代模式转移暂停（年轻代空间用满后出发）-并行收集
		混合模式垃圾收集
		[GC pause (G1 Humongous Allocation) (young) (initial-mark), 0.0022913 secs]
		[GC pause (G1 Evacuation Pause) (mixed), 0.0049752 secs]
		
		
