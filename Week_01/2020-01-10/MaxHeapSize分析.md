##1.G1-CMS-Parallel最大yang区计算

	①G1 GC
	java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -jar gateway-server-0.0.1-SNAPSHOT.jar
	   MinHeapFreeRatio         = 40
	   MaxHeapFreeRatio         = 70
	   MaxHeapSize              = 1073741824 (1024.0MB)
	   NewSize                  = 1363144 (1.2999954223632812MB)
	   MaxNewSize               = 643825664 (614.0MB)
	   OldSize                  = 5452592 (5.1999969482421875MB)
	   NewRatio                 = 2
	   SurvivorRatio            = 8
	   MetaspaceSize            = 21807104 (20.796875MB)
	   CompressedClassSpaceSize = 1073741824 (1024.0MB)
	   MaxMetaspaceSize         = 17592186044415 MB
	   G1HeapRegionSize         = 1048576 (1.0MB)
	最大young区默认占整个heap的60% --1024*0.6=614m

	java -Xmx2g -Xms2g -XX:-UseAdaptiveSizePolicy -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -jar gateway-server-0.0.1-SNAPSHOT.jar
	   MinHeapFreeRatio         = 40
	   MaxHeapFreeRatio         = 70
	   MaxHeapSize              = 2147483648 (2048.0MB)
	   NewSize                  = 1363144 (1.2999954223632812MB)
	   MaxNewSize               = 1287651328 (1228.0MB)
	   OldSize                  = 5452592 (5.1999969482421875MB)
	   NewRatio                 = 2
	   SurvivorRatio            = 8
	   MetaspaceSize            = 21807104 (20.796875MB)
	   CompressedClassSpaceSize = 1073741824 (1024.0MB)
	   MaxMetaspaceSize         = 17592186044415 MB
	   G1HeapRegionSize         = 1048576 (1.0MB)
	  最大young区默认占整个heap的60% --2048*0.6=1228m
	

	②CMS GC
	java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC  -jar gateway-server-0.0.1-SNAPSHOT.jar
	   MinHeapFreeRatio         = 40
	   MaxHeapFreeRatio         = 70
	   MaxHeapSize              = 1073741824 (1024.0MB)
	   NewSize                  = 357892096 (341.3125MB)
	   MaxNewSize               = 357892096 (341.3125MB)
	   OldSize                  = 715849728 (682.6875MB)
	   NewRatio                 = 2
	   SurvivorRatio            = 8
	   MetaspaceSize            = 21807104 (20.796875MB)
	   CompressedClassSpaceSize = 1073741824 (1024.0MB)
	   MaxMetaspaceSize         = 17592186044415 MB
	   G1HeapRegionSize         = 0 (0.0MB)
	最大young区：Xmx/3=1024m/3=341m
	
	java -Xmx2g -Xms2g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC  -jar gateway-server-0.0.1-SNAPSHOT.jar
	   MinHeapFreeRatio         = 40
	   MaxHeapFreeRatio         = 70
	   MaxHeapSize              = 2147483648 (2048.0MB)
	   NewSize                  = 715784192 (682.625MB)
	   MaxNewSize               = 715784192 (682.625MB)
	   OldSize                  = 1431699456 (1365.375MB)
	   NewRatio                 = 2
	   SurvivorRatio            = 8
	   MetaspaceSize            = 21807104 (20.796875MB)
	   CompressedClassSpaceSize = 1073741824 (1024.0MB)
	   MaxMetaspaceSize         = 17592186044415 MB
	   G1HeapRegionSize         = 0 (0.0MB)
	最大young区：Xmx/3=682m

	java -Xmx4g -Xms4g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC  -jar gateway-server-0.0.1-SNAPSHOT.jar
	   MinHeapFreeRatio         = 40
	   MaxHeapFreeRatio         = 70
	   MaxHeapSize              = 4294967296 (4096.0MB)
	   NewSize                  = 1134100480 (1081.5625MB)
	   MaxNewSize               = 1134100480 (1081.5625MB)
	   OldSize                  = 3160866816 (3014.4375MB)
	   NewRatio                 = 2
	   SurvivorRatio            = 8
	   MetaspaceSize            = 21807104 (20.796875MB)
	   CompressedClassSpaceSize = 1073741824 (1024.0MB)
	   MaxMetaspaceSize         = 17592186044415 MB
	   G1HeapRegionSize         = 0 (0.0MB)
	最大young区: Xmx/3=1081m


	*********************
	我本机16核，默认并发线程数为13，即ParallelGCThreads = 13，根据公式，最大young区=64M * 并发GC线程数(4) * 13 / 10 =1081m；
	但当上面参数Xmx1g，Xmx2g时，由于最大young区计算为：1081m，猜测是其它区不够分，没有触发CMS GC,而是触发了Parallel GC，
	当Xmx4g 及以上时，满足CMS内存分配条件，因而触发了CMS GC；

	测试加如下参数：-XX:ParallelGCThreads=4 Xmx1g，可以正常触发CMS GC;

	**********************


	③Parallel GC
	java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseParallelGC  -jar gateway-server-0.0.1-SNAPSHOT.jar
	using thread-local object allocation.
	Parallel GC with 13 thread(s)
	
	Heap Configuration:
	   MinHeapFreeRatio         = 40
	   MaxHeapFreeRatio         = 70
	   MaxHeapSize              = 1073741824 (1024.0MB)
	   NewSize                  = 357564416 (341.0MB)
	   MaxNewSize               = 357564416 (341.0MB)
	   OldSize                  = 716177408 (683.0MB)
	   NewRatio                 = 2
	   SurvivorRatio            = 8
	   MetaspaceSize            = 21807104 (20.796875MB)
	   CompressedClassSpaceSize = 1073741824 (1024.0MB)
	   MaxMetaspaceSize         = 17592186044415 MB
	   G1HeapRegionSize         = 0 (0.0MB)
	最大young区: Xmx/3=341m

	java -Xmx2g -Xms2g -XX:-UseAdaptiveSizePolicy -XX:+UseParallelGC  -jar gateway-server-0.0.1-SNAPSHOT.jar
	using thread-local object allocation.
	Parallel GC with 13 thread(s)
	
	Heap Configuration:
	   MinHeapFreeRatio         = 40
	   MaxHeapFreeRatio         = 70
	   MaxHeapSize              = 2147483648 (2048.0MB)
	   NewSize                  = 715653120 (682.5MB)
	   MaxNewSize               = 715653120 (682.5MB)
	   OldSize                  = 1431830528 (1365.5MB)
	   NewRatio                 = 2
	   SurvivorRatio            = 8
	   MetaspaceSize            = 21807104 (20.796875MB)
	   CompressedClassSpaceSize = 1073741824 (1024.0MB)
	   MaxMetaspaceSize         = 17592186044415 MB
	   G1HeapRegionSize         = 0 (0.0MB)
	最大young区: Xmx/3=682m



##以下是老师的分享数据:
	2.Parallel GC和CMS GC的最大young区大小如何计算
	默认情况下，大小会受到自适应参数影响，我们先关掉此参数-XX:-UseAdaptiveSizePolicy。
	然后试验如下：
	java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseParallelGC -jar target/gateway-server-0.0.1-SNAPSHOT.jar
	   MaxHeapSize              = 1073741824 (1024.0MB)
	   NewSize                     = 357564416 (341.0MB)
	   MaxNewSize               = 357564416 (341.0MB)
	   OldSize                      = 716177408 (683.0MB)
	
	java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -jar target/gateway-server-0.0.1-SNAPSHOT.jar
	   MaxHeapSize              = 1073741824 (1024.0MB)
	   NewSize                     = 348913664 (332.75MB)
	   MaxNewSize               = 348913664 (332.75MB)
	   OldSize                      = 724828160 (691.25MB)
	
	java -Xmx2g -Xms2g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -jar target/gateway-server-0.0.1-SNAPSHOT.jar
	   MaxHeapSize              = 2147483648 (2048.0MB)
	   NewSize                     = 348913664 (332.75MB)
	   MaxNewSize               = 348913664 (332.75MB)
	   OldSize                      = 1798569984 (1715.25MB)
	
	可以看到 ParallelGC下，young区大小为1024/3 = 341.3M，跟上述显示一致。
	CMS情况下则为332.75M，不是1/3，并且在xmx为2048M时，还是332.75M，这说明最大young区大小与Xmx参数无关。
	实际上，我的电脑上：64M * 并发GC线程数(4) * 13 / 10 =332.8M
	这个式子是jvm代码写死的，只跟GC线程数有关系。
	
	继续测试：
	-XX:ParallelGCThreads=2 
	   MaxHeapSize              = 2147483648 (2048.0MB)
	   NewSize                     = 174456832 (166.375MB)
	   MaxNewSize               = 174456832 (166.375MB)
	   OldSize                      = 1973026816 (1881.625MB)
	
	-XX:ParallelGCThreads=8
	   MaxHeapSize              = 2147483648 (2048.0MB)
	   NewSize                     = 697892864 (665.5625MB)
	   MaxNewSize               = 697892864 (665.5625MB)
	   OldSize                      = 1449590784 (1382.4375MB)

	CMS GC的 默认GC进程数是怎么来的？
	区分young区的parnew gc线程数和old区的cms线程数，分别为以下两参数：
	-XX:ParallelGCThreads=m
	-XX:ConcGCThreads=n 
	
	其中ParallelGCThreads 参数的默认值是：
	CPU核心数 <= 8，则为 ParallelGCThreads=CPU核心数，比如我的那个旧电脑是4
	CPU核心数 > 8，则为 ParallelGCThreads = CPU核心数 * 5/8 + 3 向下取整
	16核的情况下，ParallelGCThreads = 13
	32核的情况下，ParallelGCThreads = 23
	64核的情况下，ParallelGCThreads = 43
	72核的情况下，ParallelGCThreads = 48
	
	ConcGCThreads的默认值则为：
	ConcGCThreads = (ParallelGCThreads + 3)/4 向下去整。
	ParallelGCThreads = 1~4时，ConcGCThreads = 1
	ParallelGCThreads = 5~8时，ConcGCThreads = 2
	ParallelGCThreads = 13~16时，ConcGCThreads = 4
