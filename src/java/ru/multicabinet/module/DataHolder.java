package ru.multicabinet.module;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: artemz
 * Date: 7/23/14
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DataHolder {
    private HashMap<String, String> userData = new HashMap<String, String>();
    private HashMap<String, String> orderData = new HashMap<String, String>();
    public void setUserData(Map<String, String> data){
        this.userData = (HashMap)data;
    }
    public void setUserDataValue(String key, String value){
        userData.put(key,value);
    }
    public String getUserDataValue(String key){
        return userData.get(key);
    }
    public void setOrderData(HashMap<String,String> data){
        this.orderData = (HashMap)data;
    }
    public void setOrderDataValue(String key, String value){
        orderData.put(key,value);
    }
    public String getOrderDataValue(String key){
        return orderData.get(key);
    }
}
