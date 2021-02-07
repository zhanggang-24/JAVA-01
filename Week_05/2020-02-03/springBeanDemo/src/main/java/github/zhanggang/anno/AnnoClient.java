package github.zhanggang.anno;

import github.zhanggang.anno.service.IAnnoAccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnoClient {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnoConfig.class);

        IAnnoAccountService service = (IAnnoAccountService) context.getBean("accountService");
        service.saveAccount();

    }
}
