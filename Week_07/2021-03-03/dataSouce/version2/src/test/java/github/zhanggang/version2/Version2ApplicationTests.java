package github.zhanggang.version2;

import github.zhanggang.version2.model.Account;
import github.zhanggang.version2.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Version2ApplicationTests {

    @Autowired
    private AccountService accountService;

    @Test
    public void addTest() {
        Account account = new Account();
        account.setName("master-王五");
        account.setMoney(BigDecimal.valueOf(12.34));
        account.setPhone("12222");
        accountService.add(account);
    }

    @Test
    public void findAllTest() {
        List<Account> all = accountService.findAll();
        System.out.println(all.toString());
    }

}
