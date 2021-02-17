package github.zhanggang.jdbcdemo;

import java.math.BigDecimal;
import java.sql.*;

public class JdbcDemo {
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
            String createSql = "insert into account values(1,'张三','1811111111',100),(2,'李四','1811111111',100)";
            //执行sql
            int createCnt = statement.executeUpdate(createSql);
            //结果
            System.out.println("create成功，数量：" + createCnt);

            //查询
            String querySql = "select * from account";
            resultSet = statement.executeQuery(querySql);
            System.out.println("query成功，数据为：");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                BigDecimal money = resultSet.getBigDecimal("money");
                System.out.println("id:" + id + " name:" + name + " phone:" + phone + " money:" + money);
            }

            //修改
            String updateSql = "update account set name ='王五',money =200 where id =2 and name ='李四'";
            int updateCnt = statement.executeUpdate(updateSql);
            System.out.println("update成功，数量：" + updateCnt);
            System.out.println("update后结果为：");
            resultSet = statement.executeQuery(querySql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                BigDecimal money = resultSet.getBigDecimal("money");
                System.out.println("id:" + id + " name:" + name + " phone:" + phone + " money:" + money);
            }

            //删除
            String deleteSql = "delete from account where name in('张三','王五');";
            int deleteCnt = statement.executeUpdate(deleteSql);
            System.out.println("delete成功，数量：" + deleteCnt);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, statement, connection);
        }
    }
}
