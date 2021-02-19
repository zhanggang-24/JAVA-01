package github.zhanggang.startertest;

import github.zhanggang.autoconfigurer.AutoConfiguration;
import github.zhanggang.autoconfigurer.School;
import org.springframework.boot.SpringApplication;

import org.springframework.context.ConfigurableApplicationContext;

public class HelloTest {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AutoConfiguration.class, args);
        School school = applicationContext.getBean(School.class);
        school.ding();
    }
}
