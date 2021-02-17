package github.zhanggang.jdbcdemo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcBatchDemo {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //获取执行sql的对象
            statement = connection.createStatement();

            //新增
            String createSql = "insert into account values(1,'张三','1811111111',100),(2,'李四','1811111111',100); ";

            //更新
            String updateSql = "update account set name ='王五',money =200 where id =2 and name ='李四'; ";

            //删除
            String deleteSql = "delete from account where name ='张三';";


            statement.addBatch(createSql);
            statement.addBatch(updateSql);
            statement.addBatch(deleteSql);
            //执行
            statement.executeBatch();

            System.out.println("批量执行成功");

            String querySql = "select * from account";
            resultSet = statement.executeQuery(querySql);
            System.out.println("批量执行完，查询结果为：");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                BigDecimal money = resultSet.getBigDecimal("money");
                System.out.println("id:" + id + " name:" + name + " phone:" + phone + " money:" + money);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, statement, connection);
        }
    }
}
