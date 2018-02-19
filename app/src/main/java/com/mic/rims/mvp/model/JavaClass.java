package com.mic.rims.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Suman on 2/19/2018.
 */

public class JavaClass implements Parcelable{
    private String object;
    private int hello;

    protected JavaClass(Parcel in) {
        object = in.readString();
        hello = in.readInt();
    }

    public static final Creator<JavaClass> CREATOR = new Creator<JavaClass>() {
        @Override
        public JavaClass createFromParcel(Parcel in) {
            return new JavaClass(in);
        }

        @Override
        public JavaClass[] newArray(int size) {
            return new JavaClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(object);
        dest.writeInt(hello);
    }
}
