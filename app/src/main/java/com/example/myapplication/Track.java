package com.example.myapplication;

import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Track implements Parcelable {
    private String id;
    private int image;
    private String artist;
    private String title;
    private String bpm;
    private String key;
    private String camelot;
    private String release;
    private String album;
    private String label;
    private String pop;
    private String sampleid;
    private String remixid;
    private String samplelabel;
    private String remixlabel;


    public Track(){

    }

    public Track(String id, int image, String artist, String title, String bpm,
                 String key, String camelot, String release, String album,
                 String label, String pop, String sampleid, String remixid,
                 String samplelabel, String remixlabel){
        this.id=id;
        this.image=image;
        this.artist=artist;
        this.title=title;
        this.bpm=bpm;
        this.key=key;
        this.camelot=camelot;
        this.release=release;
        this.album=album;
        this.label=label;
        this.pop=pop;
        this.sampleid=sampleid;
        this.remixid=remixid;
        this.samplelabel=samplelabel;
        this.remixlabel=remixlabel;
    }

    protected Track(Parcel in) {
        id = in.readString();
        image = in.readInt();
        artist = in.readString();
        title = in.readString();
        bpm = in.readString();
        key = in.readString();
        camelot = in.readString();
        release = in.readString();
        album = in.readString();
        label = in.readString();
        pop = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public String getId() { return id; }

    public int getImage() { return image; }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getBpm() {
        return bpm;
    }

    public String getKey() {
        return key;
    }

    public String getCamelot() {
        return camelot;
    }

    public String getRelease() {
        return release;
    }

    public String getAlbum() {
        return album;
    }

    public String getLabel() {
        return label;
    }

    public String getPop() {
        return pop;
    }

    public String getSampleid() { return sampleid; }

    public String getRemixid() { return remixid; }

    public String getSamplelabel() { return samplelabel; }

    public String getRemixlabel() { return remixlabel; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(image);
        dest.writeString(artist);
        dest.writeString(title);
        dest.writeString(bpm);
        dest.writeString(key);
        dest.writeString(camelot);
        dest.writeString(release);
        dest.writeString(album);
        dest.writeString(label);
        dest.writeString(pop);
    }
}
