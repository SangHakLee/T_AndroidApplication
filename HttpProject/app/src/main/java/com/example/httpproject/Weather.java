package com.example.httpproject;

import java.util.ArrayList;

/**
 * Created by skplanet on 2016-01-26.
 *  http://www.kma.go.kr/XML/weather/sfc_web_map.xml
 */
public class Weather {

    public String year;
    public String month;
    public String day;
    public String hour;

    ArrayList<Local> list = new ArrayList<Local>();

    @Override
    public String toString() {
        return "Weather{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                ", list=" + list +
                '}';
    }
}
