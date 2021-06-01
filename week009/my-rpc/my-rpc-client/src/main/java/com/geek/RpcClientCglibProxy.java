package com.geek;

import net.sf.cglib.proxy.Enhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * cglib代理生成类
 *
 */
@Component
public class RpcClientCglibProxy {

    @Autowired
    private ServiceFacade serviceFacade;

    private static final Map<String, Object> proxyBeanMap = new ConcurrentHashMap<>(10);

    public <T> T createProxy(Class<T> serviceClass) {
        Object proxy = proxyBeanMap.get(serviceClass.getName());
        if (proxy == null) {
            proxy = Enhancer.create(serviceClass, serviceFacade);
            proxyBeanMap.putIfAbsent(serviceClass.getName(), proxy);
        }

        return (T) proxy;
    }
}
