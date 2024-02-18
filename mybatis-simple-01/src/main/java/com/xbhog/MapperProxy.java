package com.xbhog;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xbhog
 * @describe: 映射器代理类
 * @date 2024/2/15
 */
public class MapperProxy<T> implements InvocationHandler,Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    private final Map<String,String> sqlSession;
    //代理接口
    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        }else{
            //需要匹配的是类名+方法名
            return "您已经被代理了,代理方法："+sqlSession.get(mapperInterface.getName()+"."+method.getName());
        }
    }
}