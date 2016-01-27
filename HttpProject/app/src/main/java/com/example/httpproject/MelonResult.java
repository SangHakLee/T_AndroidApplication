package com.example.httpproject;

/**
 * Created by skplanet on 2016-01-27.
 * 멜론 정보 최상단에 있는 객체 {}
 *
 * {
 "      melon":{
 "      menuId":54020101,
 *
 *  이런 데이터에서 최상위
 *  {}  를 위한 객체임
 */
public class MelonResult {
    public Melon melon;


    @Override
    public String toString() {
        return "MelonResult{" +
                "melon=" + melon +
                '}';
    }
}
