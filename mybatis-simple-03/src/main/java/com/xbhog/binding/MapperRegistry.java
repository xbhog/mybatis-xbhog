package com.xbhog.binding;

import cn.hutool.core.lang.ClassScanner;
import com.xbhog.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author xbhog
 * @describe: 接口注册器
 * @date 2024/2/25
 */
public class MapperRegistry {

    private final Map<Class<?>,MapperProxyFactory<?>> interfaceMaps = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) interfaceMaps.get(type);
        if(Objects.isNull(mapperProxyFactory)){
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        return (T)mapperProxyFactory.newInstance(sqlSession);
    }

    public void addMapper(String packageName){
        Set<Class<?>> scanPackage = ClassScanner.scanPackage(packageName);
        scanPackage.forEach(clazz -> {
            addMappers(clazz);
        });
    }

    private void addMappers(Class<?> clazz) {
        if(clazz.isInterface()){
            //判断是否重复添加
            if(haveInterface(clazz)){
                throw new RuntimeException("Type " + clazz + " is already known to the MapperRegistry.");
            }
        }
        // 注册映射器代理工厂
        interfaceMaps.put(clazz, new MapperProxyFactory<>(clazz));
    }

    private boolean haveInterface(Class<?> clazz) {
        return interfaceMaps.containsKey(clazz);
    }

}
