package com.latihan.reky.myuianduxsubmission;


import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private int id;
    private String titleName;
    private String descName;
    private String imgTv;

    public TvShow() {

    }

   public TvShow(String titleName, String descName, String imgTv) {
        this.titleName = titleName;
        this.descName = descName;
        this.imgTv = imgTv;

    }

    protected TvShow(Parcel in) {
        id = in.readInt();
        titleName = in.readString();
        descName = in.readString();
        imgTv = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getDescName() {
        return descName;
    }

    public void setDescName(String descName) {
        this.descName = descName;
    }

    public String getImgTv() {
        return imgTv;
    }

    public void setImgTv(String imgTv) {
        this.imgTv = imgTv;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titleName);
        dest.writeString(descName);
        dest.writeString(imgTv);
    }
}
