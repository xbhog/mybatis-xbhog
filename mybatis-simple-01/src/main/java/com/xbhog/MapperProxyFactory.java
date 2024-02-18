package com.xbhog;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author xbhog
 * @describe: 映射器代理工厂
 * @date 2024/2/15
 */
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        MapperProxy mapperProxy = new MapperProxy(sqlSession, mapperInterface);
        //进行代理操作
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface}, mapperProxy);
    }

}
