package com.example.compoundproject;

/**
 * Created by skplanet on 2016-01-19.
 */
public class ImageTextData {
    // 안드로이드에서는 public으로 해서 하는게 권장
    // 자바에서는 private로 해서 getter, setter
    public int img;
    public String text;

    public ImageTextData() {
    }

    public ImageTextData(int img, String text) {
        this.img = img;
        this.text = text;
    }


}
