package com.greenfox.gitinder.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message implements Parcelable{


    int id;
    String from;
    String to;

    @SerializedName("created_at")
    int createdAt;

    String message;

    public Message() {
    }

    public Message(int id, String from, String to, int createdAt, String message) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.createdAt = createdAt;
        this.message = message;
    }

    protected Message(Parcel in) {
        id = in.readInt();
        from = in.readString();
        to = in.readString();
        createdAt = in.readInt();
        message = in.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(from);
        dest.writeString(to);
        dest.writeInt(createdAt);
        dest.writeString(message);
    }
}
