package com.cphs.coffeeam.api;

import java.util.ArrayList;

import com.cphs.coffeeam.model.Channel;
import com.cphs.coffeeam.model.Link;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Cesario on 7/27/2017.
 */
public interface API {
  @GET("links/{start}/{end}/{channelId}")
  Observable<ArrayList<Link>> getLinks(@Path("start") long start, @Path("end") long end,
      @Path("channelId") String channelId);

  @GET("channels")
  Observable<ArrayList<Channel>> getChannels();
}

