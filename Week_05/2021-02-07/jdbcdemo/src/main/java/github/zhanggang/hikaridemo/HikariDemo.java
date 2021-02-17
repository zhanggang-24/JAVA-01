package github.zhanggang.hikaridemo;

import github.zhanggang.jdbcdemo.JdbcUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HikariDemo {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement psQuery = null;
        try {
            //获取连接
            connection = HikariUtils.getConnection();

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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            psQuery.close();
            resultSet.close();
            connection.close();
        }
    }
}
