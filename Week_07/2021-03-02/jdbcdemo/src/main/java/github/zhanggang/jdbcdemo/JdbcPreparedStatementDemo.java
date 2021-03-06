package github.zhanggang.jdbcdemo;

import java.sql.*;
import java.util.UUID;

public class JdbcPreparedStatementDemo {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {

            //获取连接
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            //新增
            String createSql = "insert into t_user_account values(?,?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(createSql);
            Long start = System.currentTimeMillis();
            for (int i = 1; i <= 1000000; i++) {

                ps.setString(1, UUID.randomUUID().toString());
                ps.setString(2, "张三" + i);
                ps.setString(3, "abcdefghigklmnopqrstuvwxyz" + i);
                ps.setString(4, "www.baidu.com/abc/efg.jpg" + i);
                ps.setString(5, "进击的巨人" + i);
                ps.setInt(6, 1);
                ps.setString(7, "2020-12-02");
                ps.setInt(8, 0);
                ps.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
                ps.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
                ps.addBatch();
                if (i % 100000 == 0) {
                    ps.executeBatch();
                    connection.commit();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();
            connection.commit();

            Long end = System.currentTimeMillis();
            System.out.println("耗时(ms)：" + (end - start));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
            connection.close();
        }
    }
}
