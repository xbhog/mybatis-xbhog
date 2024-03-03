package com.xbhog.session;

import com.xbhog.binding.MapperRegistry;
import com.xbhog.mapping.MappedStatement;
import com.xbhog.session.defaults.DefaultSqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 衣立君
 * @date 2024/03/01 16:19
 **/
public class Configuration {

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    public <T> T getMapper(Class<T> type, DefaultSqlSession defaultSqlSession) {
        return mapperRegistry.getMapper(type,defaultSqlSession);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }
    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public MappedStatement getMappedStatements(String id) {
        return mappedStatements.get(id);
    }
}