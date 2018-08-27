package com.cqx.config;

import com.google.common.base.Strings;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Configuration;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * spring boot 中mybatis插件开发
 *
 * @author BG307435
 * @date 2018/8/27
 */
@Configuration
public class MybatisPluginConfig {

    /**
     * {@link MybatisAutoConfiguration} 里面有@Autowired方式注入拦截器
     *
     * @return
     */
    @Bean
    public Interceptor[] interceptors() {
        Interceptor[] interceptors = new Interceptor[1];
        interceptors[0] = new WaybillStatusInterceptor();
        return interceptors;
    }

    /**
     * 如果是WaybillInfoMapper.selectByExample这个方法， 则设置threadLocal值， 后续处理结果集的时候进行额外的处理
     */
    @Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
            RowBounds.class, ResultHandler.class})})
    public static class WaybillExecutorInterceptor implements Interceptor {
        private static final String TARGET_STATEMENT = "com.best.buc.finance.repository.gen.WaybillInfoMapper.selectByExample";
        private static ThreadLocal<Boolean> IS_WAYBILL_SELECT_STATEMENT = new ThreadLocal<>();

        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            if (TARGET_STATEMENT.equals(ms.getId())) {
                IS_WAYBILL_SELECT_STATEMENT.set(true);

            }
            return invocation.proceed();
        }

        @Override
        public Object plugin(Object target) {
            return Plugin.wrap(target, this);
        }

        @Override
        public void setProperties(Properties properties) {

        }
    }

    /**
     * mybatis自定义的插件, 用于转换waybill_info的aborted到abort
     */
    @Intercepts({
            @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
    })
    public static class WaybillStatusInterceptor implements Interceptor {
        private static final String OLD_STRING = "ABORTED";
        private static final String EXPECT_STRING = "ABORT";
        private static final String STATUS_COLUMN = "status";
        private static ThreadLocal<Boolean> IS_WAYBILL_SELECT_STATEMENT = new ThreadLocal<>();

        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            if (IS_WAYBILL_SELECT_STATEMENT.get()) {
                Object[] args = invocation.getArgs();
                // 获取到当前的Statement
                Statement stmt = (Statement) args[0];
                // 通过Statement获得当前结果集
                ResultSet resultSet = stmt.getResultSet();

                while (resultSet.next()) {
                    // status 判断是否为aborted， 是的话替换成abort
                    String status = resultSet.getString(STATUS_COLUMN);
                    if (!Strings.isNullOrEmpty(status) && OLD_STRING.equals(status)) {
                        resultSet.updateString(STATUS_COLUMN, EXPECT_STRING);
                    }
                }
            }
            return invocation.proceed();
        }

        @Override
        public Object plugin(Object target) {
            return Plugin.wrap(target, this);
        }

        @Override
        public void setProperties(Properties properties) {

        }
    }
}
