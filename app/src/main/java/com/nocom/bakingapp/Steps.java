package com.nocom.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Moha on 11/9/2017.
 */
public class Steps implements Parcelable{
    String nshortDescription;
    String ndescripsion;
    String nurl;
    int number ;
    public Steps(String shortDescription, String descripsion, String url, int nid) {
        nshortDescription=shortDescription;
        ndescripsion=descripsion;
        nurl=url;
        number = nid;
    }

    protected Steps(Parcel in) {
        nshortDescription = in.readString();
        ndescripsion = in.readString();
        nurl = in.readString();
        number = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nshortDescription);
        dest.writeString(ndescripsion);
        dest.writeString(nurl);
        dest.writeInt(number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    public String getNdescripsion() {
        return ndescripsion;
    }

    public void setNshortDescription(String nshortDescription) {
        this.nshortDescription = nshortDescription;
    }

    public String getNshortDescription() {
        return nshortDescription;
    }

    public String getNurl() {
        return nurl;
    }

    public int getNumber() {
        return number;
    }
}
