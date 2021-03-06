package github.zhanggang.version2.service;

import github.zhanggang.version2.mapper.AccountMapper;
import github.zhanggang.version2.model.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;


    public void add(Account account) {
        int i = accountMapper.insert(account);
        System.out.println("新增成功：" + i + "条");
    }

    public List<Account> findAll() {
        return accountMapper.selectAll();
    }

}
