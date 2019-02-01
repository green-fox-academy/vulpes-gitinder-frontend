package com.greenfox.gitinder.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MatchWrapper implements Parcelable {
    protected MatchWrapper(Parcel in) {
    }

    public static final Creator<MatchWrapper> CREATOR = new Creator<MatchWrapper>() {
        @Override
        public MatchWrapper createFromParcel(Parcel in) {
            return new MatchWrapper(in);
        }

        @Override
        public MatchWrapper[] newArray(int size) {
            return new MatchWrapper[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
