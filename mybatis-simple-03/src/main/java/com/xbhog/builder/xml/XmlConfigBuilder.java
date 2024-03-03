package com.xbhog.builder.xml;

import cn.hutool.core.io.resource.ResourceUtil;
import com.xbhog.builder.BaseBuilder;
import com.xbhog.mapping.MappedStatement;
import com.xbhog.mapping.SqlCommandType;
import com.xbhog.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 衣立君
 * @date 2024/03/01 16:16
 * @describe 建造者模式，实现XML读取解析
 **/
public class XmlConfigBuilder extends BaseBuilder {
    private final Logger log = LoggerFactory.getLogger(XmlConfigBuilder.class);
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
        try {
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void mapperElement(Element mappers) throws DocumentException, ClassNotFoundException {
        // 遍历所有“mapper”子元素
        List<Element> mapperElements = mappers.elements("mapper");
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
            //命名空间
            String namespace = mapperRootElement.attributeValue("namespace");
            // 遍历mapper文件中所有的“select”元素
            List<Element> selectNodes = mapperRootElement.elements("select");
            for (Element selectElement : selectNodes) {
                // 获取“select”元素的各个属性值
                String selectId = selectElement.attributeValue("id");
                String parameterType = selectElement.attributeValue("parameterType");
                String resultType = selectElement.attributeValue("resultType");
                String sql = selectElement.getText();

                Map<Integer, String> parameter = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for(int i = 1; matcher.find(); i++){
                    String group1 = matcher.group(1);
                    String group2 = matcher.group(2);
                    log.info("匹配出来的信息为：{}：{}", group1, group2);
                    parameter.put(i, group2);
                    sql = sql.replace(group1,"?");
                }
                String msId = namespace + "." + selectId;
                String nodeName = selectElement.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, msId,
                        sqlCommandType, parameterType, resultType, sql, parameter).build();
                configuration.addMappedStatement(mappedStatement);
            }
            // 注册Mapper映射器
            configuration.addMapper(Class.forName(namespace));
        }
    }
}