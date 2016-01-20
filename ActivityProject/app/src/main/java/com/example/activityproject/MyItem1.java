package com.example.activityproject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by skplanet on 2016-01-20.
 */
public class MyItem1 implements Parcelable {
    public String name;
    public int cnt;

    public MyItem1(Parcel in){ // Parcelable 생성자 만들기. 자동완성 안됨
        name = in.readString();
        cnt = in.readInt();
    }

    public MyItem1(String name, int cnt) {
        this.name = name;
        this.cnt = cnt;
    }

    // 만들어야하는 것
    public static final Parcelable.Creator<MyItem1> CREATOR = new Parcelable.Creator<MyItem1>(){

        @Override
        public MyItem1 createFromParcel(Parcel source) {
            return new MyItem1(source);
        }

        @Override
       public MyItem1[] newArray(int size) {
            return new MyItem1[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) { // MyItem1()에 초기화하는 멤버 필드순서랑 write랑 순서가 같아야한다.
        dest.writeString(name);
        dest.writeInt(cnt);
    }
}
