package com.cphs.coffeeam.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cesario.siringoringo on 20/12/2017.
 */

public class Link implements Parcelable {
  public static final Creator<Link> CREATOR = new Creator<Link>() {
    @Override
    public Link createFromParcel(Parcel in) {
      return new Link(in);
    }

    @Override
    public Link[] newArray(int size) {
      return new Link[size];
    }
  };
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("service_name")
  @Expose
  private String serviceName;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("image_url")
  @Expose
  private String imageUrl;
  @SerializedName("channel_id")
  @Expose
  private String channelId;

  protected Link(Parcel in) {
    id = in.readString();
    serviceName = in.readString();
    title = in.readString();
    description = in.readString();
    url = in.readString();
    imageUrl = in.readString();
    channelId = in.readString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(id);
    parcel.writeString(serviceName);
    parcel.writeString(title);
    parcel.writeString(description);
    parcel.writeString(url);
    parcel.writeString(imageUrl);
    parcel.writeString(channelId);
  }
}
