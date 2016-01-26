package com.example.httpproject;

import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by skplanet on 2016-01-26.
 */
public class WeatherXMLParser {
    private static final String TAG = "Main";

    public Weather doParser(InputStream is){
        Weather weather = null;

        SAXParserFactory factory = SAXParserFactory.newInstance(); // 여기서 만들어서 넘겨준다.
        SAXParser parser = null;
        WeaterXMLHandler handler = new WeaterXMLHandler();
        try {
            parser = factory.newSAXParser();
            parser.parse(is, handler); // 실제로 파싱은 핸들러가
            weather = handler.getWeather(); // 핸들러에서 만들어서 넘긴다.
        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
            Log.v(TAG, "error1 : " + e);
        } catch (SAXException e) {
            Log.v(TAG, "error2 : " + e);
//            e.printStackTrace();
        } catch (IOException e) {
            Log.v(TAG, "error3 : " + e);
//            e.printStackTrace();
        }

        return weather;
    };
}
