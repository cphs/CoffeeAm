package com.cphs.coffeeam.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cesario.siringoringo on 20/12/2017.
 */

public class Channel implements Parcelable {
  public static final Creator<Channel> CREATOR = new Creator<Channel>() {
    @Override
    public Channel createFromParcel(Parcel in) {
      return new Channel(in);
    }

    @Override
    public Channel[] newArray(int size) {
      return new Channel[size];
    }
  };
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("name")
  @Expose
  private String name;

  protected Channel(Parcel in) {
    id = in.readString();
    name = in.readString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(id);
    parcel.writeString(name);
  }
}
