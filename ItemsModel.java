package com.example.myproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemsModel implements Parcelable {
    private String name;
    private String desc;
    private int image;
    public Restaurante test;

    public List<Restaurante> restaurantes = new ArrayList();


    public ItemsModel(String name, String desc, int image, Restaurante restaurante) {
        this.name = name;
        this.desc = desc;
        this.image = image;
        //this.restaurantes = restaurantes;
        this.test=new Restaurante(restaurante.name,restaurante.desc,restaurante.image);
        this.restaurantes.add(test);
        this.test=new Restaurante(restaurante.name,restaurante.desc,restaurante.image);
        this.restaurantes.add(test);
        this.test=new Restaurante(restaurante.name,restaurante.desc,restaurante.image);
        this.restaurantes.add(test);
        this.test=new Restaurante(restaurante.name,restaurante.desc,restaurante.image);
        this.restaurantes.add(test);
    }

    protected ItemsModel(Parcel in) {
        name = in.readString();
        desc = in.readString();
        image = in.readInt();
        in.readTypedList(restaurantes, Restaurante.CREATOR);
    }

    public static final Creator<ItemsModel> CREATOR = new Creator<ItemsModel>() {
        @Override
        public ItemsModel createFromParcel(Parcel in) {
            return new ItemsModel(in);
        }

        @Override
        public ItemsModel[] newArray(int size) {
            return new ItemsModel[size];
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
