package com.xbhog.session;

import com.xbhog.binding.MapperRegistry;

/**
 * @author xbhog
 * @describe:
 * @date 2024/2/25
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
