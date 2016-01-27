package com.example.httpproject;

/**
 * Created by skplanet on 2016-01-27.
 * 정보관리
 */
public class Data {
    public String name;
    public int age;
    public String addres;
    public boolean sex;

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addres='" + addres + '\'' +
                ", sex=" + sex +
                '}';
    }

    // JSONObject 방법
//    @Override
//    public String toString() {
//        return "{" +
//                "name:\"" + name + "\"" +
//                ", age:" + age +
//                ", addres:\"" + addres + "\"" +
//                ", sex:" + sex +
//                '}';
//    }
}
