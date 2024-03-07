package com.xbhog.session.defaults;


import com.xbhog.session.Configuration;
import com.xbhog.session.SqlSession;
import com.xbhog.session.SqlSessionFactory;

/**
 * @author xbhog
 * @describe:
 * @date 2024/2/25
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
