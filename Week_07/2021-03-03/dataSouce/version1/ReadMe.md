##
    （必做）读写分离-动态切换数据源版本1.0
      1、基于 Spring/Spring Boot，配置多个数据源(例如2个，master 和 slave)
      2、根据具体的 Service 方法是否会操作数据，注入不同的数据源,1.0版本
      3、改进一下1.1：基于操作 AbstractRoutingDataSource 和自定义注解 readOnly 之
      类的，简化自动切换数据源
      4、改进二下1.2：支持配置多个从库；
      5、改进三下1.3：支持多个从库的负载均衡