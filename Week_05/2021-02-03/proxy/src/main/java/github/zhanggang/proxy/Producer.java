package github.zhanggang.proxy;

public class Producer implements IProducer {
    
    public void sealProduct(float money) {
        System.out.println("生产商销售产品，金额为：" + money);
    }
}
