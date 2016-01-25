package com.example.fileproject;

/**
 * Created by skplanet on 2016-01-25.
 * 싱글톤
 */
public class PropertyManager {
    private static PropertyManager instance = null;
    static String name = "AAA"; // 다른곳에서 name을 불를때 AAA가 항상 있다고 보장할 수 없다. 메모리 아웃의 가능성
    // 외부에서 접근하도록
    public static PropertyManager getInstance(){
        if( instance == null){
            instance = new PropertyManager();
        }
        return instance; // instance 로 외부에서 접근하게
    };

    // private로 하면 외부에서 생성 불가
    private PropertyManager(){

    };
}
