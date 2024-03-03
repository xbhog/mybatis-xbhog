package com.xbhog.binding;

import com.xbhog.mapping.MappedStatement;
import com.xbhog.mapping.SqlCommandType;
import com.xbhog.session.Configuration;
import com.xbhog.session.SqlSession;

import java.lang.reflect.Method;

/**
 * @author xbhog
 * @describe:
 * @date 2024/3/3
 */
public class MapperMethod {
    private final SqlCommand command;

    public MapperMethod(Configuration configuration, Class<?> mapperInterface, Method method) {
        this.command = new SqlCommand(configuration, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.getType()) {
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(), args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return result;
    }

    /**
     * sql指令
     */
    public static class SqlCommand {
        //方法名
        private final String name;
        //方法类型
        private final SqlCommandType sqlCommandType;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            //获取MappedStatement
            String mid = mapperInterface.getName() + "." + method.getName();
            MappedStatement mappedStatements = configuration.getMappedStatements(mid);
            this.name = mappedStatements.getId();
            this.sqlCommandType = mappedStatements.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return sqlCommandType;
        }
    }
}
