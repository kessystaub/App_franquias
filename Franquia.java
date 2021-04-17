package com.example.myproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Franquia implements Parcelable {
    private String name;
    private String desc;
    private int image;
    public Restaurante test;

    public List<Restaurante> restaurantes = new ArrayList();


    public Franquia(String name, String desc, int image, List<Restaurante> restaurantes) {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.restaurantes = restaurantes;
    }

    protected Franquia(Parcel in) {
        name = in.readString();
        desc = in.readString();
        image = in.readInt();
        in.readTypedList(restaurantes, Restaurante.CREATOR);
    }

    public static final Creator<Franquia> CREATOR = new Creator<Franquia>() {
        @Override
        public Franquia createFromParcel(Parcel in) {
            return new Franquia(in);
        }

        @Override
        public Franquia[] newArray(int size) {
            return new Franquia[size];
        }
    };


    public Restaurante getRestaurante(int position){
        return this.restaurantes.get(position);
    }

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
        dest.writeTypedList(restaurantes);
    }
}

