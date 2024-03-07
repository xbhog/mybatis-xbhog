package com.xbhog;

import cn.hutool.core.io.resource.ResourceUtil;
import com.xbhog.session.SqlSession;
import com.xbhog.session.SqlSessionFactory;
import com.xbhog.session.SqlSessionFactoryBuilder;
import junit.framework.TestCase;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.List;

/**
 * Unit test for simple App.
 */

public class AppTest extends TestCase {

    private Logger logger = LoggerFactory.getLogger(AppTest.class);
    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = ResourceUtil.getUtf8Reader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        String res = userDao.queryUserInfoById("10001");
        logger.info("测试结果：{}", res);
    }
}
