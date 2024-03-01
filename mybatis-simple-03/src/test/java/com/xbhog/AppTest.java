package com.xbhog;

import cn.hutool.core.io.resource.ResourceUtil;
import com.xbhog.binding.MapperProxyFactory;
import com.xbhog.binding.MapperRegistry;
import com.xbhog.session.DefaultSqlSession;
import com.xbhog.session.DefaultSqlSessionFactory;
import com.xbhog.session.SqlSession;
import com.xbhog.session.SqlSessionFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */

public class AppTest extends TestCase {
    /**
     * Rigourous Test :-)
     */
    public void testApp() throws DocumentException {
        // xml文件内容转换为字符串流
        Reader reader = ResourceUtil.getUtf8Reader("mybatis-config-datasource.xml");

        // 从字符串流中读取并创建XML Document对象
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new InputSource(reader));

        // 获取XML文档的根元素
        Element rootElement = document.getRootElement();

        // 获取根元素下的“mappers”子元素
        Element mappersElement = rootElement.element("mappers");

        // 遍历所有“mapper”子元素
        List<Element> mapperElements = mappersElement.elements("mapper");
        for (Element mapperElement : mapperElements) {
            // 获取当前“mapper”元素的“resource”属性值
            String mapperResource = mapperElement.attributeValue("resource");
            System.out.println("正在查看资源：" + mapperResource);

            // 依据“resource”属性加载对应的XML文件内容为字符串流
            Reader mapperReader = ResourceUtil.getUtf8Reader(mapperResource);

            // 创建新的SAXReader实例以读取mapper文件中的XML内容
            SAXReader saxReaderForMapper = new SAXReader();

            // 从mapper的字符串流中创建新的Document对象
            Document mapperDocument = saxReaderForMapper.read(new InputSource(mapperReader));

            // 获取mapper文件的根元素
            Element mapperRootElement = mapperDocument.getRootElement();

            // 遍历mapper文件中所有的“select”元素
            List<Element> selectNodes = mapperRootElement.elements("select");
            for (Element selectElement : selectNodes) {
                // 获取“select”元素的各个属性值
                String selectId = selectElement.attributeValue("id");
                String parameterType = selectElement.attributeValue("parameterType");
                String resultType = selectElement.attributeValue("resultType");
                String sqlStatement = selectElement.getText();

                // 输出“select”元素的属性及SQL语句
                System.out.println("相关SQL映射信息：ID=" + selectId + "；参数类型=" + parameterType +
                        "；结果类型=" + resultType + "；SQL语句=" + sqlStatement);
            }
        }


        /*MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMapper("com.xbhog");
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao user = sqlSession.getMapper(IUserDao.class);
        System.out.println("输出的信息："+user);*/
    }
}
