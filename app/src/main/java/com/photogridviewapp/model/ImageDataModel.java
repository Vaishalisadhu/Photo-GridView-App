package com.photogridviewapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class ImageDataModel extends ArrayList<Parcelable> implements Parcelable {

    private String imageUrl;
    private String title;
    private String discription;
    private String date;



    public ImageDataModel() {

    }

    protected ImageDataModel(Parcel in) {
        imageUrl = in.readString();
        title = in.readString();
        discription = in.readString();
        date = in.readString();
    }

    public static final Creator<ImageDataModel> CREATOR = new Creator<ImageDataModel>() {
        @Override
        public ImageDataModel createFromParcel(Parcel in) {
            return new ImageDataModel(in);
        }

        @Override
        public ImageDataModel[] newArray(int size) {
            return new ImageDataModel[size];
        }
    };


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(title);
        parcel.writeString(discription);
        parcel.writeString(date);
    }
}
