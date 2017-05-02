package com.example.jinhwan.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CheckBox;

import java.util.Date;

/**
 * Created by Jinhwan on 2017-04-06.
 */

public class Information implements Parcelable{
    String name;
    String call;
    String menu1;
    String menu2;
    String menu3;
    String homepage;
    String date;
    int category;
    CheckBox checkBox;
    public Information(String name,String call, String menu1,String menu2,String menu3, String homepage,String date,int category){
        this.name = name;
        this.call = call;
        this.menu1 = menu1;
        this.menu2 = menu2;
        this.menu3 = menu3;
        this.homepage = homepage;
        this.date = date;
        this.category = category;
    }

    protected Information(Parcel in) {
        name = in.readString();
        call = in.readString();
        menu1 = in.readString();
        menu2 = in.readString();
        menu3 = in.readString();
        homepage = in.readString();
        date = in.readString();
        category = in.readInt();
    }

    public static final Creator<Information> CREATOR = new Creator<Information>() {
        @Override
        public Information createFromParcel(Parcel in) {
            return new Information(in);
        }

        @Override
        public Information[] newArray(int size) {
            return new Information[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(call);
        dest.writeString(menu1);
        dest.writeString(menu2);
        dest.writeString(menu3);
        dest.writeString(homepage);
        dest.writeString(date);
        dest.writeInt(category);
    }

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getCall() {
        return call;
    }

    public String getMenu1() {
        return menu1;
    }

    public String getMenu2() {
        return menu2;
    }

    public String getMenu3() {
        return menu3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public void setMenu1(String menu1) {
        this.menu1 = menu1;
    }

    public void setMenu2(String menu2) {
        this.menu2 = menu2;
    }

    public void setMenu3(String menu3) {
        this.menu1 = menu3;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
