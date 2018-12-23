package cn.dubbox2.client.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author buchengyin
 * @Date 2018/12/23 21:16
 **/
public class ClientAttributeHolder {
    private static Map<Class,String> clazzNames = new HashMap<>();
    public static void putClassName(Class clazz,String name){
        clazzNames.put(clazz,name);
    }

    public static String getClassName(Class clazz){
        String name = clazzNames.get(clazz);
        if(name==null){
            name = clazz.getName();
        }
        return name;
    }
}
