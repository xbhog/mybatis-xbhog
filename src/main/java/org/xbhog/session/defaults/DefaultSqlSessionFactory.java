package org.xbhog.session.defaults;


import org.xbhog.session.Configuration;
import org.xbhog.session.SqlSession;
import org.xbhog.session.SqlSessionFactory;

/**
 * @author xbhog
 * @description 默认的 DefaultSqlSessionFactory
 * @date 2024年1月27日
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
