package com.xbhog.mapping;

import com.xbhog.session.Configuration;

import java.util.Map;

/**
 * @author xbhog
 * @describe: 用于封装MyBatis中映射SQL语句的相关信息，包括配置信息、SQL类型、参数类型、结果类型、SQL语句以及动态参数映射等。
 * @date 2024/3/2
 */
public class MappedStatement {

    /**
     * 配置对象，包含MyBatis运行所需的环境、数据库映射等全局配置信息。
     */
    private Configuration configuration;

    /**
     * 映射ID，唯一标识一个MappedStatement，通常对应XML文件中的<mappedStatement>标签的id属性。
     */
    private String id;

    /**
     * SQL命令类型，如INSERT、UPDATE、SELECT或DELETE等。
     */
    private SqlCommandType sqlCommandType;

    /**
     * 参数类型，对应于传入SQL语句的参数类的全限定名。
     */
    private String parameterType;

    /**
     * 结果类型，对应于SQL查询结果映射到的Java类型的全限定名。
     */
    private String resultType;

    /**
     * SQL语句，可能是预编译的静态SQL或带有占位符的动态SQL。
     */
    private String sql;

    /**
     * 动态参数映射集合，键为参数的位置（从0开始计数），值为参数的名称。
     */
    private Map<Integer, String> parameter;

    /**
     * 空构造器，主要用于反射创建实例。
     */
    public MappedStatement() {}

    /**
     * 内部静态嵌套类Builder，遵循建造者设计模式，用于构建MappedStatement实例。
     */
    public static class Builder {

        /**
         * 储存待构建的MappedStatement对象引用。
         */
        private MappedStatement mappedStatement = new MappedStatement();

        /**
         * 初始化Builder对象，并设置MappedStatement的所有必要属性。
         *
         * @param configuration MyBatis的全局配置对象
         * @param id 映射ID
         * @param sqlCommandType SQL命令类型
         * @param parameterType 参数类型全限定名
         * @param resultType 结果类型全限定名
         * @param sql SQL语句
         * @param parameter 动态参数映射集合
         */
        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, String parameterType, String resultType, String sql, Map<Integer, String> parameter) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.parameterType = parameterType;
            mappedStatement.resultType = resultType;
            mappedStatement.sql = sql;
            mappedStatement.parameter = parameter;
        }

        /**
         * 完成构建并返回MappedStatement实例，同时进行必要的非空断言检查。
         *
         * @return 已经填充好所有必要属性的MappedStatement实例
         */
        public MappedStatement build() {
            assert mappedStatement.configuration != null : "Configuration must not be null!";
            assert mappedStatement.id != null : "ID must not be null!";
            return mappedStatement;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<Integer, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<Integer, String> parameter) {
        this.parameter = parameter;
    }
}
