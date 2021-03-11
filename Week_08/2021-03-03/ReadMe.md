
##
    2、（必做）设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。
    并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github
    
## 
    采用 shardingsphere-proxy实现
    1.下载shardingsphere-proxy，下载后解压
       https://www.apache.org/dyn/closer.cgi/shardingsphere/5.0.0-alpha/apache-shardingsphere-5.0.0-alpha-shardingsphere-proxy-bin.tar.gz
    2.下载mysql驱动jar包
        https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.47/mysql-connector-java-5.1.47.jar
    3.将下载的mysql驱动jar包放在解压后的lib目录下。

    4.配置%SHARDINGSPHERE_PROXY_HOME%/conf/server.yaml，配置手册：https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-proxy/configuration/
    
	5.配置%SHARDINGSPHERE_PROXY_HOME%/conf/config-sharding.yaml，配置手册：https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-proxy/configuration/
    