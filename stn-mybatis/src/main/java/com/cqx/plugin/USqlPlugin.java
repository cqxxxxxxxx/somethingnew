package com.cqx.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "query",
                args = {Statement.class, ResultHandler.class}
        )
})
public class USqlPlugin implements Interceptor {
    private Properties properties = new Properties();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("ccccqx");
        // implement pre processing if need
        Object returnObject = invocation.proceed();
        // implement post processing if need
        System.out.println("ccccqx");
        return returnObject;
    }

    /**
     * 生成一个代理对象，即用该拦截器包装过的代理对象
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
