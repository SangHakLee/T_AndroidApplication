package com.example.httpproject;

import java.util.ArrayList;

/**
 * Created by skplanet on 2016-01-27.
 * json 데이터 배열 관리
 */
public class DataResult {

    // 정의된 데이터 순서대로 json 데이터가 간다.
    public int count;
    public String status;
    public ArrayList<Data> list;
}
