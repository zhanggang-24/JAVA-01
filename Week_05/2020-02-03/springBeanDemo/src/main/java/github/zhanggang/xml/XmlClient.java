package github.zhanggang.xml;

import github.zhanggang.xml.service.IXmlAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlClient {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        IXmlAccountService service = (IXmlAccountService) ac.getBean("accountService");
        service.saveAccount();

        System.out.println("-------分隔线--------------");

        IXmlAccountService service1 = (IXmlAccountService) ac.getBean("accountService1");
        service1.saveAccount();
    }
}
