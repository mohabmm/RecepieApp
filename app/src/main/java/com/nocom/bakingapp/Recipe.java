package com.nocom.bakingapp;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipe  implements Parcelable {
    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }
        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    private static Bitmap bitmap_transfer;
    private String poster1;// image for the recipe
   // String thumbnailURL; // the image of the meal
    private String name;// name of the recipe
    ArrayList ningriedients;
    ArrayList nsteps;
    int nid;
    private String steps;
    private String descripstion;// full descripstion of teh meal
    private String shortddescription ;// short describtion for the steps
    private String measure;//  mesure here using one cup or something related to ingredients
    private String ingredientS ;// the ingriedient component of the meal
    private int quantity ;// quantity of the substance used related to ingriedients
    private String vidiourl ;// the url whitch will be used in


    Recipe(Parcel in) {

        poster1 = in.readString();
        name = in.readString();
        //thumbnailURL=in.readString();
        descripstion = in.readString();
        shortddescription = in.readString();
        measure = in.readString();// measure
        ingredientS = in.readString();
        steps = in.readString();
        quantity = in.readInt();
        vidiourl=in.readString();
    }

    public Recipe(int quantity, String measure, String name) {
        super();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster1);
        dest.writeString(name);
        dest.writeString(descripstion);
        dest.writeString(shortddescription);
        dest.writeString(measure);
        dest.writeString(ingredientS);
        dest.writeString(steps);
        dest.writeInt(quantity);
        dest.writeString(vidiourl);
    }


    public Recipe(String image, int id, String nname, ArrayList<Ingrideint> ingr, ArrayList<Steps> asteps) {

        poster1=image;
        nid=id;
        name=nname;
        ningriedients=ingr;
        nsteps=asteps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getVidiourl() {
        return vidiourl;
    }

    public ArrayList getNingriedients() {
        return ningriedients;
    }

    public ArrayList getNsteps() {
        return nsteps;
    }

    public int getNid() {
        return nid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescripstion() {
        return descripstion;
    }

    public String getIngredientS() {
        return ingredientS;
    }

    public String getMeasure() {
        return measure;
    }

    public  String getPoster1() {
        return poster1;
    }

    public String getName() {
        return name;
    }

    public String getShortddescription() {
        return shortddescription;
    }

    public void setDescripstion(String descripstion) {
        this.descripstion = descripstion;
    }

    public void setIngredientS(String ingredientS) {
        this.ingredientS = ingredientS;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setNsteps(ArrayList nsteps) {
        this.nsteps = nsteps;
    }

    public void setNingriedients(ArrayList ningriedients) {
        this.ningriedients = ningriedients;
    }

    public void setPoster1(String poster1) {
        this.poster1 = poster1;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setVidiourl(String vidiourl) {
        this.vidiourl = vidiourl;
    }

    public void setShortddescription(String shortddescription) {
        this.shortddescription = shortddescription;
    }


}