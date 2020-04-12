package com.latihan.reky.myuianduxsubmission;


import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int id;
    private String imgPhoto;
    private String txtTitle;
    private String txtDesc;

    public Movie () {
    }

    public Movie( String imgPhoto, String txtTitle, String txtDesc) {
        this.imgPhoto = imgPhoto;
        this.txtTitle = txtTitle;
        this.txtDesc = txtDesc;
    }


    protected Movie(Parcel in) {
        id = in.readInt();
        imgPhoto = in.readString();
        txtTitle = in.readString();
        txtDesc = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   public String getImgPhoto() {
        return imgPhoto;
    }

    public void setImgPhoto(String imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtDesc() {
        return txtDesc;
    }

    public void setTxtDesc(String txtDesc) {
        this.txtDesc = txtDesc;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(imgPhoto);
        dest.writeString(txtTitle);
        dest.writeString(txtDesc);
    }
}
