package com.cphs.coffeeam.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cesario.siringoringo on 20/12/2017.
 */

public class Page implements Parcelable {
  public static final Creator<Page> CREATOR = new Creator<Page>() {
    @Override
    public Page createFromParcel(Parcel in) {
      return new Page(in);
    }

    @Override
    public Page[] newArray(int size) {
      return new Page[size];
    }
  };
  private Channel mChannel;
  private ArrayList<Link> mLinks;

  public Page(Parcel in) {
    mChannel = in.readParcelable(Channel.class.getClassLoader());
  }

  public Page(Channel channel, ArrayList<Link> links) {
    mChannel = channel;
    mLinks = new ArrayList<>();
    mLinks.addAll(links);
  }

  public Channel getChannel() {
    return mChannel;
  }

  public void setChannel(Channel channel) {
    mChannel = channel;
  }

  public ArrayList<Link> getLinks() {
    return mLinks;
  }

  public void setLinks(ArrayList<Link> links) {
    mLinks = links;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeParcelable(mChannel, i);
  }
}
