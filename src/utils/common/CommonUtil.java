package utils.common;

import com.alibaba.fastjson.JSONObject;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lenovo on 2015/12/3.
 */
public class CommonUtil<T>{
    /**
     * setPropertyName
     * @param t
     * @param propertyName
     * @param value
     * @return
     */
    public boolean updateByPropertyName(T t ,String propertyName,String value){
        try {
            PropertyDescriptor pd=new PropertyDescriptor(propertyName,t.getClass());
            Method method = pd.getWriteMethod();
            System.out.println("CommonUtil-1-"+method.getName());
            method.invoke(t,new String[]{(value)});
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }


}
