package github.zhanggang.cglib;


/**
 * 基于子类的动态代理
 * 需要引入第三方cglib库
 */
public class Client {
    public static void main(String[] args) {

        float money = 1000f;
        Producer producer = new Producer();
        producer.sealProduct(money);

        System.out.println("--------------------------------------");
        CglibAopProxy cglibAopProxy = new CglibAopProxy(producer);
        Producer proxyProducer = (Producer) cglibAopProxy.getProxyObject();
        proxyProducer.sealProduct(money);
    }
}
