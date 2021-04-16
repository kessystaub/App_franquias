package com.example.myproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurante implements Parcelable {
    public String name;
    public String desc;
    public int image;

    public Restaurante(String name, String desc, int image) {
        this.name = name;
        this.desc = desc;
        this.image = image;
    }

    protected Restaurante(Parcel in) {
        name = in.readString();
        desc = in.readString();
        image = in.readInt();
    }

    public static final Creator<Restaurante> CREATOR = new Creator<Restaurante>() {
        @Override
        public Restaurante createFromParcel(Parcel in) {
            return new Restaurante(in);
        }

        @Override
        public Restaurante[] newArray(int size) {
            return new Restaurante[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeInt(image);
    }
}
