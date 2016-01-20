package com.example.recyclerproject;

/**
 * Created by skplanet on 2016-01-20.
 */
public class MyItem {
    public int imgId;
    public String title;
    public String down;
    public int price;

    public MyItem( String down, int imgId, int price, String title ) {
        this.imgId = imgId;
        this.title = title;
        this.down = down;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MyItem{" +
                "imgId=" + imgId +
                ", title='" + title + '\'' +
                ", down='" + down + '\'' +
                ", price=" + price +
                '}';
    }
}
