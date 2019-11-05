package com.nice.intercept;


import com.nice.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * @author ningh
 */
public interface Interceptor {

    /**
     * 事前方法
     * @return true or false 布尔类型
     */
    public boolean before();

    /**
     * 事后方法
     */
    public void after();

    /**
     * 取代原有事件方法
     * @param invocation    -- 回调参数 可以通过它的proceed方法，回调原有事件
     * @return  原有事件返回对象
     * @throws InvocationTargetException 反射异常
     * @throws IllegalAccessException 反射异常
     */
    public Object around(Invocation invocation) throws Throwable;

    /**
     * 是否返回方法。事件没有发生异常执行。
     */
    public void afterReturning();

    /**
     * 事后异常方法，当事件发生异常后执行。
     */
    public void afterThrowing();

    /**
     * 是否使用around方法取代原有方法
     * @return true or false
     */
    boolean useAround();

}
