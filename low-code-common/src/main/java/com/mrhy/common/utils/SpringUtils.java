package com.mrhy.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description beanUtils todo
 * @author cooper
 * @date 2021/9/4 3:38 下午
 */
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
    }
    /**
     * @description  获取applicationContext
     * @author cooper
     * @date 2021/9/4 3:37 下午
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @description 根据name获取bean
     * @author cooper
     * @date 2021/9/4 3:37 下午
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * @description 通过class获取Bean.
     * @author cooper
     * @date 2021/9/4 3:37 下午
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
    /**
     * @description     通过name,以及Clazz返回指定的Bean
     * @author cooper
     * @date 2021/9/4 3:37 下午
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
