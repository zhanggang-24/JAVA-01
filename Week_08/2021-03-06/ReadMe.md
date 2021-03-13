##
    2、（必做）基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布
    式事务应用 demo（二选一），提交到 Github
    
##
    碰到的问题：
     java.sql.SQLException: XAER_RMERR: Fatal error occurred in the transaction branch - check your data for consistency
     
     原因是帐号缺少权限： 执行下面命令解决  
     GRANT XA_RECOVER_ADMIN ON *.* TO '你的用户名'@'%';