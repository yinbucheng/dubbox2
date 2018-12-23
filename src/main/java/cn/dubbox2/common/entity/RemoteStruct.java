package cn.dubbox2.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author buchengyin
 * @Date 2018/12/23 20:58
 **/
public class RemoteStruct implements Serializable {
    //类的全路径名称
    private String classFullName;
    //类的方法名称
    private String methodFullName;
    //参数类型名称
    private String[] parameterFullTypes;
    //参数结果
    private Object[] parameterValue;

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public String getMethodFullName() {
        return methodFullName;
    }

    public void setMethodFullName(String methodFullName) {
        this.methodFullName = methodFullName;
    }

    public String[] getParameterFullTypes() {
        return parameterFullTypes;
    }

    public void setParameterFullTypes(String[] parameterFullTypes) {
        this.parameterFullTypes = parameterFullTypes;
    }

    public Object[] getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(Object[] parameterValue) {
        this.parameterValue = parameterValue;
    }
}
