package com.nice.config;

import com.nice.intercept.Interceptor;
import com.nice.intercept.MyInterceptor;
import com.nice.invoke.Invocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author ningh
 */
public class ProxyBean implements InvocationHandler {

    private Object target;

    private Interceptor interceptor;

    /**
     * 绑定代理对象
     * @param target    被代理对象
     * @param interceptor   拦截器
     * @return  代理对象
     */
    public static Object getProxyBean(Object target, Interceptor interceptor){
        ProxyBean proxyBean = new ProxyBean();
        //保存被代理对象
        proxyBean.target = target;
        //保存拦截器
        proxyBean.interceptor = interceptor;
        //生成代理对象
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),proxyBean);
        //返回代理对象
        return proxy;
    }

    /**
     * 处理代理对象方法逻辑
     * @param proxy 代理对象
     * @param method    当前方法
     * @param args  运行参数
     * @return  方法调用结果
     * @throws Throwable    异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //异常标识
        boolean exceptionFlag = false;
        Invocation invocation = new Invocation(target,method,args);
        Object retObj = null;
        try {
            if (this.interceptor.before()) {
                retObj = this.interceptor.around(invocation);
            } else {
                retObj = method.invoke(target,args);
            }
        } catch (Exception ex) {
                //产生异常
                exceptionFlag = true;
        }
        this.interceptor.after();
        if (exceptionFlag) {
            this.interceptor.afterThrowing();
        } else {
            this.interceptor.afterReturning();
            return retObj;
        }
        return null;
    }
}
