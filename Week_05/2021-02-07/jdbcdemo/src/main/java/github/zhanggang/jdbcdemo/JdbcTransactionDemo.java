package github.zhanggang.jdbcdemo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransactionDemo {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            //获取连接
            connection = JdbcUtils.getConnection();

            //开启事务
            connection.setAutoCommit(false);

            //转账操作
            String sql1 = "update account set money =money -? where id =?;";
            String sql2 = "update account set money =money +? where id =?;";

            ps1 = connection.prepareStatement(sql1);
            ps2 = connection.prepareStatement(sql2);

            ps1.setBigDecimal(1, BigDecimal.valueOf(50));
            ps1.setInt(2, 1);

            ps2.setBigDecimal(1, BigDecimal.valueOf(50));
            ps2.setInt(2, 2);

            ps1.executeUpdate();
            int i = 1 / 0;
            ps2.executeUpdate();
            //提交事务
            connection.commit();

        } catch (Exception e) {
            try {
                //回滚事务
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ps1.close();
            ps2.close();
            connection.close();
        }
    }
}
