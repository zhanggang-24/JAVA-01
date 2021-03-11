package github.zhanggang.shardingdemo.mapper;

import github.zhanggang.shardingdemo.model.UserOrder;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;


public interface UserOrderMapper extends Mapper<UserOrder> {

}
