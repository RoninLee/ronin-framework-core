package com.ronin.base.listener;

import com.ronin.basemodel.vo.common.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lizelong
 * @date Created on 2020/8/12 11:21
 * @description 强制Controller的方法必须加上注解说明方法功能
 */
@Component
@Slf4j
public class ApiDocCheckListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 需要检查的controller的包前缀
     */
    public static final String PACKAGE_PREFIX_TO_CHECK = "com";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        event.getApplicationContext().getBeansWithAnnotation(Controller.class).values().stream().map(Object::getClass)
                .filter(c -> c.getName().startsWith(PACKAGE_PREFIX_TO_CHECK)).map(Class::getMethods)
                .flatMap(Arrays::stream).filter(m -> m.getAnnotation(ApiOperation.class) == null
                && m.getReturnType().isAssignableFrom(Result.class))
                .findAny().ifPresent(this::report);

    }

    private void report(Method m) {
        String errMsg = m.getDeclaringClass().getSimpleName() + "." + m.getName() + "必须使用@ApiOperation注解说明接口功能";
        log.warn(errMsg);
    }
}
