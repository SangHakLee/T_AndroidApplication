package com.example.httpproject;

/**
 * Created by skplanet on 2016-01-27.
 */
public class Melon {
    public int menuId;
    public int count;
    public int page;
    public int totalPages;
    public String  rankDay;
    public String  rankHour;
    public Songs songs;

    @Override
    public String toString() {
        return "Melon{" +
                "menuId=" + menuId +
                ", count=" + count +
                ", page=" + page +
                ", totalPages=" + totalPages +
                ", rankDay='" + rankDay + '\'' +
                ", rankHour='" + rankHour + '\'' +
                ", songs=" + songs +
                '}';
    }
}
