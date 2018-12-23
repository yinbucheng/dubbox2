package cn.dubbox2.common.utils;

import cn.dubbox2.server.base.ServerAttributeHolder;
import com.alibaba.fastjson.JSON;

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

    public static Class[] resovleTypes(String[] types){
        if(types==null||types.length==0)
            return null;
        Class[] result = new Class[types.length];
        for(int i=0;i<types.length;i++){
            result[i]=ServerAttributeHolder.getClassByName(types[i]);
        }
        return result;
    }

    public static Object[] resovleValues(Class[] types,String[] values){
        if(types==null)
            return null;
        Object[] result= new Object[values.length];
        for(int i=0;i<values.length;i++){
            result[i] = JSON.parseObject(values[i],types[i]);
        }
        return result;
    }

    public static Object invokeMethod(String className,String methodName,String[] paramterTypes,String[] value){
        Class clazz = ServerAttributeHolder.getClassByName(className);
        Class[] ptyeps = resovleTypes(paramterTypes);
        Object[] values = resovleValues(ptyeps,value);
        Method method = getMethod(clazz,methodName,ptyeps);
        return invokeMethod(clazz,method,values);
    }
}
