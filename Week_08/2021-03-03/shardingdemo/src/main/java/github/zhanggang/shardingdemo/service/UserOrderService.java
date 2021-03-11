package github.zhanggang.shardingdemo.service;

import github.zhanggang.shardingdemo.mapper.UserOrderMapper;
import github.zhanggang.shardingdemo.model.UserOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserOrderService {

    @Resource
    private UserOrderMapper userOrderMapper;

    public void add(UserOrder userOrder) {
        userOrderMapper.insert(userOrder);
    }

    public List<UserOrder> findAll() {
        return userOrderMapper.selectAll();
    }

}
