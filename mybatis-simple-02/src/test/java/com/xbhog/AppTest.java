package com.xbhog;

import com.xbhog.binding.MapperProxyFactory;
import com.xbhog.binding.MapperRegistry;
import com.xbhog.session.DefaultSqlSession;
import com.xbhog.session.DefaultSqlSessionFactory;
import com.xbhog.session.SqlSession;
import com.xbhog.session.SqlSessionFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMapper("com.xbhog");
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao user = sqlSession.getMapper(IUserDao.class);
        String userName = user.getUserName("xbhog");
        System.out.println("输出的信息："+userName);
    }
}
