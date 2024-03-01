package com.xbhog.session.defaults;

import com.xbhog.session.Configuration;
import com.xbhog.session.SqlSession;

/**
 * @author xbhog
 * @describe:
 * @date 2024/2/25
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T  selectOne(String statement,Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type,this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
