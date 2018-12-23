package cn.dubbox2.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author buchengyin
 * @Date 2018/12/23 21:07
 **/
public class ReflectUtils {

    public static Method getMethod(Class clazz,String methodName,Class... parameterTypes){
        try {
            return clazz.getMethod(methodName,parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object invokeMethod(Class clazz,Method method,Object... parameterValues){
        Object bean = ApplicationUtils.getBean(clazz);
        try {
            return method.invoke(bean,parameterValues);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
