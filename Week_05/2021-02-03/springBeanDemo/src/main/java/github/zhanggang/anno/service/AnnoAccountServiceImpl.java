package github.zhanggang.anno.service;

import github.zhanggang.anno.dao.IAnnoAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("accountService")
public class AnnoAccountServiceImpl implements IAnnoAccountService {

    @Value("王五")
    private String userName;

    @Autowired
    private IAnnoAccountDao accountDao;

    public void saveAccount() {
        accountDao.saveAccount(this.userName);
    }

}
