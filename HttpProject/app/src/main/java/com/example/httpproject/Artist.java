package com.example.httpproject;

/**
 * Created by skplanet on 2016-01-27.
 *
 * 멜론 음원 파싱 가수정보
 */
public class Artist {
    public int artistId;
    public String artistName;

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
