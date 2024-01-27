package org.xbhog.session;



import org.xbhog.builder.xml.XMLConfigBuilder;
import org.xbhog.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @author xbhog
 * @description 构建SqlSessionFactory的工厂
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}