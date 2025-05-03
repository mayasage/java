package com.blacksage.vlad;

import net.ttddyy.dsproxy.listener.logging.Log4jLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof DataSource) {
                        System.out.println("Got DataSource bean");
                        ProxyFactory factory = new ProxyFactory(bean);
                        factory.setProxyTargetClass(true);
                        factory.addAdvice(new ProxyDataSourceInterceptor((DataSource) bean));
                        return factory.getProxy();
                }

                return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
        }

        private static class ProxyDataSourceInterceptor implements MethodInterceptor {

                private final DataSource dataSource;

                public ProxyDataSourceInterceptor(DataSource dataSource) {
                        super();
                        this.dataSource =
                                ProxyDataSourceBuilder
                                        .create()
                                        .dataSource(dataSource)
                                        .countQuery()
                                        .multiline()
                                        .logQueryByLog4j(Log4jLogLevel.INFO)
                                        .build();
                }

                @Override
                public Object invoke(MethodInvocation invocation) throws Throwable {
                        Method proxyMethod = ReflectionUtils.findMethod(dataSource.getClass(), invocation.getMethod().getName());
                        if (proxyMethod == null) {
//                                System.out.println("Method not found in DataSource class: " + invocation.getMethod().getName());
                                return invocation.proceed();
                        }
                        System.out.println("Method found in DataSource class: " + invocation.getMethod().getName());
                        System.out.println("Arguments: " + Arrays.toString(invocation.getArguments()));
                        return proxyMethod.invoke(dataSource, invocation.getArguments());
                }
        }
}
