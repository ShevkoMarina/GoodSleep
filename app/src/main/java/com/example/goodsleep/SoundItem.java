package com.example.goodsleep;

import android.os.Parcel;
import android.os.Parcelable;

public class SoundItem implements Parcelable {

    private int imageSrc;
    private String name;
    private int[] SoundTracks;

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

    public int[] getSoundTracks() {
        return SoundTracks;
    }

    public void setSoundTracks(int[] soundTracks) {
        SoundTracks = soundTracks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageSrc);
        dest.writeString(name);
        dest.writeIntArray(SoundTracks);
    }

    public SoundItem(int imageSrc, String name, int[] soundTracks) {
        this.imageSrc = imageSrc;
        this.name = name;
        SoundTracks = soundTracks;
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
        SoundTracks = in.createIntArray();
    }
}
