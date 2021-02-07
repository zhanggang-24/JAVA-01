package github.zhanggang.anno.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("accountDao")
public class AnnoAccountDaoImpl implements IAnnoAccountDao {

    @Value("50600000000")
    private String accountNumber;

    public void saveAccount(String userName) {
        System.out.println(userName + "保存了账户：" + accountNumber);
    }
}
