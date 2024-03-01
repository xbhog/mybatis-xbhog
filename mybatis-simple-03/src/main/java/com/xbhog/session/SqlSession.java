package com.xbhog.session;

/**
 * @author xbhog
 * @describe: 定义简单的Mapper操作方法
 * @date 2024/2/25
 */
public interface SqlSession {

    <T> T selectOne(String statement,Object parameter);

    /**
     *得到接口映射器
     * @param type 接口类型
     * @return
     */
    <T> T getMapper(Class<T> type);

    /**
     * Retrieves current configuration
     * 得到配置
     * @return Configuration
     */
    Configuration getConfiguration();
}
