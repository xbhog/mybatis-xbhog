package org.xbhog.session;

/**
 * @author xbhog
 * @description 工厂模式接口，构建SqlSession的工厂
 * @date 2024年1月27日
 */
public interface SqlSessionFactory {

    /**
     * 打开一个 session
     * @return SqlSession
     */
   SqlSession openSession();

}
