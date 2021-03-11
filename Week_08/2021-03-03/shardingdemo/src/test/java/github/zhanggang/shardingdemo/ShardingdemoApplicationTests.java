package github.zhanggang.shardingdemo;

import github.zhanggang.shardingdemo.model.UserOrder;
import github.zhanggang.shardingdemo.service.UserOrderService;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingdemoApplicationTests {

    @Autowired
    private UserOrderService userOrderService;

    @Test
    public void add() {
        for (int i = 1; i <= 50; i++) {
            UserOrder userOrder = new UserOrder();
            userOrder.setOrderId((long) i);
            userOrder.setUserId((long) i);
            userOrder.setAddrId((long) i);
            userOrder.setOrderType(1);
            userOrder.setOrderNo("订单号：" + i);
            userOrder.setOrderTime(Timestamp.valueOf(LocalDateTime.now()));
            userOrder.setPayMethod(2);
            userOrder.setTotalAmount(BigDecimal.valueOf(100));
            userOrder.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            userOrder.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            userOrderService.add(userOrder);
        }
    }

    @Test
    public void findAll(){
        List<UserOrder> all = userOrderService.findAll();
        System.out.println(all.toString());

    }

}
