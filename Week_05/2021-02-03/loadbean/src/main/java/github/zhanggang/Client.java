package github.zhanggang;

import github.zhanggang.service.IXmlAccountService;

public class Client {
    public static void main(String[] args) {

        MyXmlApplicationContext ac = new MyXmlApplicationContext("bean.xml");
        IXmlAccountService accountService = (IXmlAccountService) ac.getBean("accountService");
        accountService.saveAccount();
    }
}
