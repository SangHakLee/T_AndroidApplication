package com.example.httpproject;

import java.util.ArrayList;

/**
 * Created by skplanet on 2016-01-27.
 */
public class Songs {
    public ArrayList<Song> song = new ArrayList<Song>();

    @Override
    public String toString() {
        return "Songs{" +
                "song=" + song +
                '}';
    }
}
