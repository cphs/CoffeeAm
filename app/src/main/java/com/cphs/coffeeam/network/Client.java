package com.cphs.coffeeam.network;

import com.cphs.coffeeam.api.API;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cesario.siringoringo on 14/06/2017.
 */

public class Client {
  private static Retrofit retrofit = null;

  private static Retrofit getClient() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder().baseUrl("http://10.177.69.56/slack-rest/public/")
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create()).build();
    }
    return retrofit;
  }

  public static API getService() {
    return getClient().create(API.class);
  }
}
