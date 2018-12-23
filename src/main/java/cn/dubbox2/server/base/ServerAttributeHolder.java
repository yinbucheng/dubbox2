package cn.dubbox2.server.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author buchengyin
 * @Date 2018/12/23 21:02
 **/
public class ServerAttributeHolder {
    //用于存放name和类关系属性
    private static Map<String,Class> nameClasses = new HashMap<>();

    public static void putNameClass(String name,Class clazz){
        nameClasses.put(name,clazz);
    }

    public static Class getClassByName(String name){
        Class clazz = nameClasses.get(name);
        if(clazz==null){
            try {
                clazz = Class.forName(name);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("undfine class name");
            }
        }
        return clazz;
    }
}
