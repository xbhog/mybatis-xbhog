package com.xbhog;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        MapperProxyFactory<IUserDao> proxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String,String> sqlSession = new HashMap<>();
        sqlSession.put("com.xbhog.IUserDao.getUserName","模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        IUserDao userDao = proxyFactory.newInstance(sqlSession);
        String userName = userDao.getUserName("100001");
        System.out.println(userName);
    }
}
