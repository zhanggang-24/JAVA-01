package github.zhanggang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP代理
 */
public class AopProxy {

    private Object proxyObject;

    /**
     * @param waitProxyObject 入参为要代理的对象
     */
    public AopProxy(final Object waitProxyObject) {
        //参数注释：
        //Classloader,用于加载代理对象的字节码，和被代理对象使用相同的类加载器
        //Class[],用于让代理对象和被代理对象有相同的方法
        //InvocationHandler,实现代理（增强）
        this.proxyObject = Proxy.newProxyInstance(waitProxyObject.getClass().getClassLoader(),
                waitProxyObject.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     *
                     * @param proxy 代理对象的引用
                     * @param method 当前执行的方法
                     * @param args 当前执行方法所需的参数
                     * @return 和被代理对象方法有相同的返回值
                     * @throws Throwable
                     */

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValue;
                        System.out.println("开始执行代理");

                        returnValue = method.invoke(waitProxyObject, args);

                        System.out.println("执行代理结束");
                        return returnValue;
                    }
                });
    }

    public Object getProxyObject() {
        return this.proxyObject;
    }
}
