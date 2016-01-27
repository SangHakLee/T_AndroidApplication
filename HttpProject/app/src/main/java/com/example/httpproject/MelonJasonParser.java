package com.example.httpproject;

import com.google.gson.Gson;

/**
 * Created by skplanet on 2016-01-27.
 * 멜론 데이터 파서
 */
public class MelonJasonParser {
    public static  MelonResult doJsonParser(String jsonString){
        MelonResult result = null;

        Gson gson = new Gson();
        result = gson.fromJson(jsonString, MelonResult.class);

        return result;
    }
}
