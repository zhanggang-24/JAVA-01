package github.zhanggang.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * 代理
 */
public class CglibAopProxy {

    private Object proxyObject;

    public CglibAopProxy(Object waitProxyObject) {
        //参数注释：
        //Class,用于指定被代理对象的字节码
        //Callback,实现代理（增强），使用该接口的子接口实现类MethodInterceptor
        proxyObject = (Producer) Enhancer.create(waitProxyObject.getClass(),
                new MethodInterceptor() {
                    /**
                     * 执行被代理对象的任何方法都会经过此方法
                     * @param o 代理对象的引用
                     * @param method 当前执行的方法
                     * @param objects 当前执行方法所需的参数
                     * @param methodProxy 当前执行方法的代理对象
                     * @return
                     * @throws Throwable
                     */
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        Object returnValue = null;
                        System.out.println("开始执行代理-cglib");
                        returnValue = method.invoke(waitProxyObject, objects);
                        System.out.println("执行代理结束-cglib");

                        return returnValue;
                    }
                });
    }

    public Object getProxyObject() {
        return this.proxyObject;
    }
}
