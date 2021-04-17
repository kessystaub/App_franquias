package com.example.myproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurante implements Parcelable {
    public String name;
    public String adress;
    public String city;
    public int image;

    public Restaurante(String name, String adress, String city, int image) {
        this.name = name;
        this.adress = adress;
        this.city = city;
        this.image = image;
    }

    protected Restaurante(Parcel in) {
        name = in.readString();
        adress = in.readString();
        city = in.readString();
        image = in.readInt();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        dest.writeString(adress);
        dest.writeString(city);
        dest.writeInt(image);
    }
}
