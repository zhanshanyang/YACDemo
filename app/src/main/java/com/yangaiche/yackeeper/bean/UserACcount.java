package com.yangaiche.yackeeper.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr_yang on 16-3-21.
 */
public class UserACcount implements Parcelable {

    public String phone ;
    public String password;
    public Long user_id;
    public String token;
    public String name;
    public String gender;
    private static boolean annotationMethod;

    public UserACcount() {}

    protected UserACcount(Parcel in) {
        phone = in.readString();
        password = in.readString();
        token = in.readString();
        name = in.readString();
        gender = in.readString();
    }

    public static final Creator<UserACcount> CREATOR = new Creator<UserACcount>() {
        @Override
        public UserACcount createFromParcel(Parcel in) {
            return new UserACcount(in);
        }

        @Override
        public UserACcount[] newArray(int size) {
            return new UserACcount[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phone);
        dest.writeString(password);
        dest.writeLong(user_id);
        dest.writeString(token);
        dest.writeString(name);
        dest.writeString(gender);
    }
}
