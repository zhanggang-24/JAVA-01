package github.zhanggang.txdemo;

import java.io.IOException;
import java.sql.SQLException;

public class DemoMain {
    public static void main(String[] args) throws IOException, SQLException {

        XAOrderService orderService = new XAOrderService("/sharding-databases-tables.yaml");
        orderService.init();
        orderService.insert();
        orderService.insertFailed();
    }
}
