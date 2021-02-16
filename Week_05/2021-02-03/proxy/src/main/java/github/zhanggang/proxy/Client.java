package github.zhanggang.proxy;

/**
 * 基于接口的动态代理
 * 被代理类最少实现一个接口，如果没有则不能使用
 */
public class Client {
    public static void main(String[] args) {

        float money = 1000f;
        Producer producer = new Producer();
        producer.sealProduct(money);

        System.out.println("--------------------------------------");

        AopProxy aopProxy = new AopProxy(producer);
        IProducer proxyProducer =(IProducer) aopProxy.getProxyObject();
        proxyProducer.sealProduct(money);
    }
}
