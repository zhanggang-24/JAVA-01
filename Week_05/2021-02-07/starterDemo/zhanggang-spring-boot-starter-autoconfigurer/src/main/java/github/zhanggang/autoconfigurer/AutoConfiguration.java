package github.zhanggang.autoconfigurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguration {

    @Bean
    public School MySchool() {
        School school = new School();
        return school;
    }
}
