package com.yangaiche.yackeeper.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mr_yang on 16-3-21.
 */
public class UserAccount implements Parcelable {

    public String phone ;
    public String password;
    public Long user_id;
    public String token;
    public String name;
    public String gender;
    private static boolean annotationMethod;

    public UserAccount() {}

    protected UserAccount(Parcel in) {
        phone = in.readString();
        password = in.readString();
        token = in.readString();
        name = in.readString();
        gender = in.readString();
    }

    public static final Creator<UserAccount> CREATOR = new Creator<UserAccount>() {
        @Override
        public UserAccount createFromParcel(Parcel in) {
            return new UserAccount(in);
        }

        @Override
        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
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
