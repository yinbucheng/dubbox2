package cn.dubbox2.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author buchengyin
 * @Date 2018/12/23 21:12
 **/
public class ApplicationUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationUtils.context = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return context.getBean(name,clazz);
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }
}
