package github.zhanggang.xml.service;

import github.zhanggang.xml.dao.XmlAccountDaoImpl;

public class XmlAccountServiceImpl implements IXmlAccountService {
    //构造函数注入
    private XmlAccountDaoImpl accountDao;
    private String userName;

    public XmlAccountServiceImpl(String userName, XmlAccountDaoImpl accountDao) {
        this.userName = userName;
        this.accountDao = accountDao;
    }

    @Override
    public void saveAccount() {
        accountDao.saveAccount(this.userName);
    }

}
