package com.test.directions.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Json {

    public static String toJson(Object json){
        return gson().toJson(json);
    }

    public static JsonObject parseToJsonObject(String json){
        return gson().fromJson(json, JsonObject.class);
    }

    private static Gson gson() {
        return new GsonBuilder().create();
    }
}
