package github.zhanggang.shardingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("github.zhanggang.shardingdemo.mapper")
public class ShardingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingDemoApplication.class, args);
    }

}
