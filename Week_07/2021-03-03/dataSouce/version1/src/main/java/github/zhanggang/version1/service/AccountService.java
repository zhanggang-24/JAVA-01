package github.zhanggang.version1.service;

import github.zhanggang.version1.config.Slave;
import github.zhanggang.version1.config.Master;
import github.zhanggang.version1.mapper.AccountMapper;
import github.zhanggang.version1.model.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Master
    public void addMaster(Account account) {
        int i = accountMapper.insert(account);
        System.out.println("新增成功：" + i + "条");
    }

    @Master
    public List<Account> findAllMaster() {
        return accountMapper.selectAll();
    }

    @Slave
    public void addSlave(Account account) {
        int i = accountMapper.insert(account);
        System.out.println("新增成功：" + i + "条");
    }

    @Slave
    public List<Account> findAllSlave() {
        return accountMapper.selectAll();
    }


}
