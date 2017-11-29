package com.nocom.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Moha on 11/9/2017.
 */


public class Ingrideint implements Parcelable {

     int nquantity;
    String nmeasure;
    String nname;


    public Ingrideint(int quantity,String measure,String name ) {

       nquantity=quantity;
        nmeasure=measure;
        nname=name;
    }


    private Ingrideint(Parcel in) {
        nquantity = in.readInt();
        nmeasure = in.readString();
        nname= in.readString();

    }

    public String getNname() {
        return nname;
    }

    public int getNquantity() {
        return nquantity;
    }

    public String getNmeasure() {
        return nmeasure;
    }

    public static final Creator<Ingrideint> CREATOR = new Creator<Ingrideint>() {
        @Override
        public Ingrideint createFromParcel(Parcel in) {
            return new Ingrideint(in);
        }

        @Override
        public Ingrideint[] newArray(int size) {
            return new Ingrideint[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(nquantity);
        dest.writeString(nmeasure);
        dest.writeString(nname);
    }
}
