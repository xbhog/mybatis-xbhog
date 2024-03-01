package com.xbhog.session;

import com.xbhog.builder.xml.XmlConfigBuilder;
import com.xbhog.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @author 衣立君
 * @date 2024/03/01 16:14
 * @describe xml统一构造器
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }
    public SqlSessionFactory build(Configuration configuration){
        return new DefaultSqlSessionFactory(configuration);
    }

}