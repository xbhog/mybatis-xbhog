package org.xbhog.test;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xbhog.io.Resources;
import org.xbhog.session.SqlSession;
import org.xbhog.session.SqlSessionFactory;
import org.xbhog.session.SqlSessionFactoryBuilder;
import org.xbhog.test.dao.IUserDao;

import java.io.IOException;
import java.io.Reader;

/**
 * @author xbhog
 * @description 单元测试
 * @date 2024年1月27日
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        String res = userDao.queryUserInfoById("10001");
        logger.info("测试结果：{}", res);
    }

}
