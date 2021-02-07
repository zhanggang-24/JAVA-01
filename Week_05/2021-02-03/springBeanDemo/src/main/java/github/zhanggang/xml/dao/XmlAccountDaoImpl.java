package github.zhanggang.xml.dao;

import java.util.List;

public class XmlAccountDaoImpl implements IXmlAccountDao {

    //set注入
    private String accountNumber;
    private List<String> phones;

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void saveAccount(String userName) {
        System.out.println(userName + "保存了账户:" + accountNumber);
        System.out.println("联系方式为：");
        for (String phone : phones) {
            System.out.println(phone);
        }
    }

}
