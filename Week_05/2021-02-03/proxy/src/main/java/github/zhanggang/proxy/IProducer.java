package github.zhanggang.proxy;

/**
 * 生产者接口
 */
public interface IProducer {
    /**
     * 卖产品的方法
     *
     * @param money 产品金额
     */
    void sealProduct(float money);

}
