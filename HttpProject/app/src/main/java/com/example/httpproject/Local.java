package com.example.httpproject;

/**
 * Created by skplanet on 2016-01-26.
 * 파싱 정보 담는 클래스
 *  http://www.kma.go.kr/XML/weather/sfc_web_map.xml
 */
public class Local {
    public String stn_id;
    public String icon;
    public String desc;
    public String ta;
    public String rn_hr1;
    public String locationName;


    @Override
    public String toString() {
        return "Local{" +
                "stn_id='" + stn_id + '\'' +
                ", icon='" + icon + '\'' +
                ", desc='" + desc + '\'' +
                ", ta='" + ta + '\'' +
                ", rn_hr1='" + rn_hr1 + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
