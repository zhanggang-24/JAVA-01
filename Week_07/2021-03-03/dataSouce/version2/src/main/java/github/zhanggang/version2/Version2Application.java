package github.zhanggang.version2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("github.zhanggang.version2.mapper")
public class Version2Application {

    public static void main(String[] args) {
        SpringApplication.run(Version2Application.class, args);
    }

}
