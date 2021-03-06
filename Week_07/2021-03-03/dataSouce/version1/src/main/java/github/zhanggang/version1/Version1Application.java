package github.zhanggang.version1;

import github.zhanggang.version1.model.Account;
import github.zhanggang.version1.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
@MapperScan("github.zhanggang.version1.mapper")

public class Version1Application {

    public static void main(String[] args) {
        SpringApplication.run(Version1Application.class, args);
    }
}
