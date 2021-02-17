package github.zhanggang.jdbcdemo;

import java.math.BigDecimal;
import java.sql.*;

public class JdbcPreparedStatementDemo {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;

        PreparedStatement ps = null;
        PreparedStatement psQuery = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psDelete = null;
        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //新增
            String createSql = "insert into account values(?,?,?,?),(?,?,?,?)";
            ps = connection.prepareStatement(createSql);
            ps.setInt(1, 1);
            ps.setString(2, "张三");
            ps.setString(3, "1811111111");
            ps.setBigDecimal(4, BigDecimal.valueOf(100));

            ps.setInt(5, 2);
            ps.setString(6, "李四");
            ps.setString(7, "1811111111");
            ps.setBigDecimal(8, BigDecimal.valueOf(100));

            int createCnt = ps.executeUpdate();
            //结果
            System.out.println("create成功，数量：" + createCnt);

            //查询
            String querySql = "select * from account";
            psQuery = connection.prepareStatement(querySql);
            resultSet = psQuery.executeQuery();
            System.out.println("query成功，数据为：");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                BigDecimal money = resultSet.getBigDecimal("money");
                System.out.println("id:" + id + " name:" + name + " phone:" + phone + " money:" + money);
            }

            //修改
            String updateSql = "update account set name =?,money =? where id =? and name =?";
            psUpdate = connection.prepareStatement(updateSql);
            psUpdate.setString(1, "王五");
            psUpdate.setBigDecimal(2, BigDecimal.valueOf(200));
            psUpdate.setInt(3, 2);
            psUpdate.setString(4, "李四");

            int updateCnt = psUpdate.executeUpdate();

            System.out.println("update成功，数量：" + updateCnt);
            System.out.println("update后结果为：");
            resultSet = psQuery.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                BigDecimal money = resultSet.getBigDecimal("money");
                System.out.println("id:" + id + " name:" + name + " phone:" + phone + " money:" + money);
            }

            //删除
            String deleteSql = "delete from account where name in(?,?);";
            psDelete = connection.prepareStatement(deleteSql);
            psDelete.setString(1, "张三");
            psDelete.setString(2, "王五");

            int deleteCnt = psDelete.executeUpdate();
            System.out.println("delete成功，数量：" + deleteCnt);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
            psQuery.close();
            psUpdate.close();
            psDelete.close();
            resultSet.close();
            connection.close();
        }
    }
}
