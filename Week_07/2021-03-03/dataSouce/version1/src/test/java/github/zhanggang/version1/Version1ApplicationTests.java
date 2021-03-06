package github.zhanggang.version1;

import github.zhanggang.version1.model.Account;
import github.zhanggang.version1.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Version1ApplicationTests {

    @Autowired
    private AccountService accountService;

    @Test
    public void addMasterTest() {
        Account account = new Account();
        account.setName("master-王五");
        account.setMoney(BigDecimal.valueOf(12.34));
        account.setPhone("12222");
        accountService.addMaster(account);
    }


    @Test
    public void findAllMasterTest() {
        List<Account> all = accountService.findAllMaster();
        System.out.println(all.toString());
    }

    @Test
    public void addSlaveTest() {
        Account account = new Account();
        account.setName("slave-王五");
        account.setMoney(BigDecimal.valueOf(12.34));
        account.setPhone("12222");
        accountService.addSlave(account);
    }

    @Test
    public void findAllSlaveTest() {
        List<Account> all = accountService.findAllSlave();
        System.out.println(all.toString());
    }

}
