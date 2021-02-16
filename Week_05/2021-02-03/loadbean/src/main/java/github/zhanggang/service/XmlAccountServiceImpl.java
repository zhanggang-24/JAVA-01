package github.zhanggang.service;

import github.zhanggang.dao.XmlAccountDaoImpl;

public class XmlAccountServiceImpl implements IXmlAccountService {

    private XmlAccountDaoImpl accountDao;

    public void saveAccount() {
        accountDao.saveAccount();
        //System.out.println("service保存了数据");
    }

    public void setAccountDao(XmlAccountDaoImpl accountDao) {
        this.accountDao = accountDao;
    }

}
