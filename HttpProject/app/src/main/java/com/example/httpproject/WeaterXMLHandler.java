package com.example.httpproject;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by skplanet on 2016-01-26.
 */
public class WeaterXMLHandler extends DefaultHandler {

    Weather weather = null;
    Local local = null;

    String tag ;


    public Weather getWeather(){ // 파싱하는 부분 밑에 3가지 오버라이딘된 메소드가 실행됨
        return weather;
    }

    // 아래 5가지 메소드 재정의 하기

    @Override // 엘리먼트 시작 <>
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("weather".equals(localName)){ // 태그가 <weater 로 시작하는거 잡기     <weather year="2016" month="01" day="26" hour="16">
            weather = new Weather();
            weather.year = attributes.getValue("year"); // 각각 weater 객체에 넣어준다,
            weather.month = attributes.getValue("month");
            weather.day = attributes.getValue("day");
            weather.hour = attributes.getValue("hour");
        }else if(localName.equals("local")){ // 태그 <local       <local stn_id="95" icon="" desc="-" ta="-0.7" rn_hr1="0.0">철원</local>
            local = new Local();
            local.stn_id = attributes.getValue("stn_id");
            local.icon = attributes.getValue("icon");
            local.desc = attributes.getValue("desc");
            local.ta = attributes.getValue("ta");
            local.rn_hr1 = attributes.getValue("rn_hr1");
        }

        // <a>123</a>
        // <b>456</b>
        // 한 줄에 한 작업 하기 위해서
        tag = localName; // 꼭 해줘야한다.
    }

    @Override // 엘리먼트 끝 </>
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        if(tag.equals("local")){
        if("local".equals(tag)){
            weather.list.add(local); // 마직막 태그를 읽으면 ArrayList에 넣는다.
        }

        tag = null; // </> 태그 읽고 나서 뒤에 줄바꿈도 처리하는데 이것을 막기위해서. 꼭 해준다. 이렇게 하면 characters() 에서 if 문에서 안걸려서 작업 안한다.
    }

    @Override // <> 여기 데이터 가저올때 호출 </>
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length); // <local stn_id="95" icon="" desc="-" ta="-0.7" rn_hr1="0.0">철원</local> 에서 철원 이라는 글자 가져오기
//        if(tag.equals("local")){
        if("local".equals("tag")){
            local.locationName = s;
        }
    }





    @Override // 파싱 시작될 때
    public void startDocument() throws SAXException {
    }
    @Override // 파싱 끝나고 호출
    public void endDocument() throws SAXException {
    }
}
