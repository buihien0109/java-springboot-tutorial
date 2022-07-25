package com.example.sessioncookie.utils;

import com.google.gson.Gson;

public class Utils {
    public static String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
