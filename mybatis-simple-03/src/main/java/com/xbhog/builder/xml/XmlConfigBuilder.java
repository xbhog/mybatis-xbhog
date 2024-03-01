package com.xbhog.builder.xml;

import com.xbhog.builder.BaseBuilder;
import com.xbhog.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;

/**
 * @author 衣立君
 * @date 2024/03/01 16:16
 * @describe 建造者模式，实现XML读取解析
 **/
public class XmlConfigBuilder extends BaseBuilder {
    private Element root;

    public XmlConfigBuilder(Reader reader) {
        //todo 解析XML
        super(new Configuration());
        // 2. dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Configuration parse() {
        //todo 解析XML字符串中的节点信息保存到MappedStatement映射
        return configuration;
    }
}