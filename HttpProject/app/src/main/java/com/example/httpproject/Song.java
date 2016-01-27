package com.example.httpproject;

/**
 * Created by skplanet on 2016-01-27.
 *
 * 멜론 파싱 곡 정보 class
 */
public class Song {

    public int songId;
    public String songName;
    public Artists artists;
    public int albumId;
    public String albumName;
    public int currentRank;
    public int pastRank;
    public int playTime;
    public String issueDate;
    public boolean isTitleSong;
    public boolean isHitSong;
    public boolean isAdult;
    public boolean isFree;


    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", artists=" + artists +
                ", albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", currentRank=" + currentRank +
                ", pastRank=" + pastRank +
                ", playTime=" + playTime +
                ", issueDate='" + issueDate + '\'' +
                ", isTitleSong=" + isTitleSong +
                ", isHitSong=" + isHitSong +
                ", isAdult=" + isAdult +
                ", isFree=" + isFree +
                '}';
    }
}
