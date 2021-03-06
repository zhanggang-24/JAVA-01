##
    2、（必做）按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率
    
## 

    1.jdbc连接串设置参数：
    url=jdbc:mysql://localhost:3306/jdbc_test_db?useSSL=false&rewriteBatchedStatements=true
    
    2.prepareStatement手动提交事务+批处理
    
    3.mysql参数修改：
    # mysql-server限制接受的数据包大小
    max_allowed_packet=128M 
    
    # 事务提交不主动触发写入磁盘操作
    innodb_flush_log_at_trx_commit=0  
    
    # 写日志缓存区增加，减少数据库写数据文件的次数
    innodb_log_buffer_size = 16M
    
    #tablespace 空间已经满了后，自动快站size设置为128M
    innodb_autoextend_increment = 128M
    
    # undo日志的大小设置为128M
    innodb_log_file_size = 128M
    
    # 缓冲池大小
    innodb_buffer_pool_size = 4096M
    
##  
    上述配置，本机执执行20s以内
    
    