package com.example.goodsleep;

import android.os.Parcel;
import android.os.Parcelable;

public class SoundItem implements Parcelable {

    private int imageSrc;
    private String name;

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageSrc);
        dest.writeString(name);
    }

    public SoundItem(int imageSrc, String name) {
        this.imageSrc = imageSrc;
        this.name = name;
    }

    public  static  final Creator<SoundItem> CREATOR = new Creator<SoundItem>() {
        @Override
        public SoundItem createFromParcel(Parcel in) {
            return  new SoundItem(in);
        }

        @Override
        public SoundItem[] newArray(int size) {
            return new SoundItem[size];
        }
    };

    protected SoundItem(Parcel in) {
        imageSrc = in.readInt();
        name = in.readString();
    }


}
