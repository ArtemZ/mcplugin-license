package ru.multicabinet.module.server;

import ru.multicabinet.module.DataHolder;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: artemz
 * Date: 7/23/14
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerModuleData extends DataHolder {
    private HashMap<String, String> accountAccessData = new HashMap<String, String>();
    private HashMap<String, String> serverAccessData = new HashMap<String, String>();
    private HashMap<String, String> accountData = new HashMap<String, String>();

    /* Options defained by admin */
    private HashMap<String, String> optionsData = new HashMap<String, String>();

    /* Fields defined in customUserFields map in servermodule
        Persisted by ModulefieldsValues domain class
     */
    private HashMap<String, String> customData = new HashMap<String, String>();

    public void setAccountAccessData(HashMap<String, String> accountAccessData) {
        this.accountAccessData = accountAccessData;
    }

    public void setServerAccessData(HashMap<String, String> serverAccessData) {
        this.serverAccessData = serverAccessData;
    }

    public void setAccountData(HashMap<String, String> accountData) {
        this.accountData = accountData;
    }

    public void setOptionsData(HashMap<String, String> optionsData) {
        this.optionsData = optionsData;
    }

    public void setCustomData(HashMap<String, String> customData) {
        this.customData = customData;
    }


    public void setAccountAccessValue(String key, String value){
        accountAccessData.put(key,value);
    }
    public String getAccountAccessValue(String key){
        return accountAccessData.get(key);
    }

    public void setServerAccessValue(String key, String value) {
        serverAccessData.put(key,value);
    }
    public String getServerAccessValue(String key) {
        return serverAccessData.get(key);
    }

    public void setAccountField(String key, String value) {
        accountData.put(key,value);
    }
    public String getAccountField(String key) {
        return accountData.get(key);
    }

    public void setOptionsField(String key, String value) {
        optionsData.put(key, value);
    }
    public String getOptionsField(String key) {
        return optionsData.get(key);
    }

    public void setCustomField(String key, String value) {
        customData.put(key,value);
    }
    public String getCustomField(String key) {
        return customData.get(key);
    }
}
