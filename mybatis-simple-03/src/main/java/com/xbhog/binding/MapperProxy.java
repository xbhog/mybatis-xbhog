package com.xbhog.binding;

import com.xbhog.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xbhog
 * @describe: 映射器代理类
 * @date 2024/2/15
 */
public class MapperProxy<T> implements InvocationHandler,Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    private final SqlSession sqlSession;
    //代理接口
    private final Class<T> mapperInterface;

    private final Map<Method, MapperMethod> methodCache;

    private Logger logger = LoggerFactory.getLogger(MapperProxy.class);


    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        }else{
            //需要匹配的是类名+方法名
            final MapperMethod mapperMethod = cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }
    }

    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if(Objects.isNull(mapperMethod)){
            //找不到方法
            mapperMethod = new MapperMethod(sqlSession.getConfiguration(), mapperInterface, method);
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
