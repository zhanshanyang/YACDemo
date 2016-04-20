package com.yangaiche.yackeeper.utils;

import com.google.gson.Gson;

/**
 * Created by ui on 16/4/20.
 */
public class MyGson {

    private static MyGson myGson;
    private Gson gson;
    private MyGson(){
        gson = new Gson();
    }

    public static MyGson getInstance(){
        if(myGson == null) {
            synchronized (MyGson.class){
                if(myGson == null){
                    myGson = new MyGson();
                }
            }
        }
        return myGson;
    }


    public <T> T fromJson(String str, Class<T> tClass) {
        return gson.fromJson(str, tClass);
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }
}
